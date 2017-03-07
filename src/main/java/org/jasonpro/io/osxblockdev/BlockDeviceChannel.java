/**
 *     
	OSXBlockDev - Enables use of block devices under osx with existing 
				  file semantics
    Copyright (C) 2017  Jason Protheroe (jason@jasonpro.org)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */
package org.jasonpro.io.osxblockdev;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.spi.FileSystemProvider;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

/**
 * @author Jason Protheroe
 *
 */
public class BlockDeviceChannel extends FileChannel {
	private FileChannel fileChannel;
	private long blocks = 0;
	private int blockSize = 512;
	private long size = 0;

	private static final FileAttribute<?>[] NO_ATTRIBUTES = new FileAttribute[0];

	protected BlockDeviceChannel() {
	}

	protected BlockDeviceChannel(FileChannel fileChannel, long blocks, int blockSize, long size) {
		this.fileChannel = fileChannel;
		this.blocks = blocks;
		this.blockSize = blockSize;
		this.size = size;
	}

	public static FileChannel open(Path path, Set<? extends OpenOption> options, FileAttribute<?>... attrs)
			throws IOException {
		int fileDescriptor;
		long size = -1;
		LongByReference blocks = new LongByReference(-1);
		IntByReference blockSize = new IntByReference(-1);

		final stat.ByReference thestat = new stat.ByReference();
		fileDescriptor = CLib.INSTANCE.open(path.toString(), CLib.O_RDONLY, 0);
		CLib.INSTANCE.fstat64(fileDescriptor, thestat);
		System.out.println("Is block device: " + thestat.isBlockDevice());
		if (thestat.isBlockDevice()) {
			System.out.println(CLib.INSTANCE.ioctl(fileDescriptor, CLib.DKIOCGETBLOCKSIZE, blockSize));
			System.out.println("Block size: " + blockSize.getValue());
			System.out.println(CLib.INSTANCE.ioctl(fileDescriptor, CLib.DKIOCGETBLOCKCOUNT, blocks));
			System.out.println("Blocks: " + blocks.getValue());
			size = blockSize.getValue() * blocks.getValue();
			System.out.println("Total size: " + size);
			CLib.INSTANCE.close(fileDescriptor);
			FileSystemProvider provider = path.getFileSystem().provider();
			return new BlockDeviceChannel(provider.newFileChannel(path, options, attrs), blocks.getValue(),
					blockSize.getValue(), size);
		} else {
			CLib.INSTANCE.close(fileDescriptor);
			return FileChannel.open(path, options, attrs);
		}
	}

	public static FileChannel open(Path path, OpenOption... options) throws IOException {
		Set<OpenOption> set = new HashSet<OpenOption>(options.length);
		Collections.addAll(set, options);
		return open(path, set, NO_ATTRIBUTES);
	}

	@Override
	public int read(ByteBuffer dst) throws IOException {
		return fileChannel.read(dst);
	}

	@Override
	public long read(ByteBuffer[] dsts, int offset, int length) throws IOException {
		return fileChannel.read(dsts, offset, length);
	}

	@Override
	public int write(ByteBuffer src) throws IOException {
		return fileChannel.write(src);
	}

	@Override
	public long write(ByteBuffer[] srcs, int offset, int length) throws IOException {
		return fileChannel.write(srcs, offset, length);
	}

	@Override
	public long position() throws IOException {
		return fileChannel.position();
	}

	@Override
	public FileChannel position(long newPosition) throws IOException {
		return fileChannel.position(newPosition);
	}

	@Override
	public long size() throws IOException {
		return size;
	}

	@Override
	public FileChannel truncate(long size) throws IOException {
		throw new IOException("Unable to resize block devices.");
	}

	@Override
	public void force(boolean metaData) throws IOException {
		fileChannel.force(metaData);
	}

	@Override
	public long transferTo(long position, long count, WritableByteChannel target) throws IOException {
		return fileChannel.transferTo(position, count, target);
	}

	@Override
	public long transferFrom(ReadableByteChannel src, long position, long count) throws IOException {
		return fileChannel.transferFrom(src, position, count);
	}

	@Override
	public int read(ByteBuffer dst, long position) throws IOException {
		return fileChannel.read(dst, position);
	}

	@Override
	public int write(ByteBuffer src, long position) throws IOException {
		return fileChannel.write(src, position);
	}

	@Override
	public MappedByteBuffer map(MapMode mode, long position, long size) throws IOException {
		return fileChannel.map(mode, position, size);
	}

	@Override
	public FileLock lock(long position, long size, boolean shared) throws IOException {
		return fileChannel.lock(position, size, shared);
	}

	@Override
	public FileLock tryLock(long position, long size, boolean shared) throws IOException {
		return fileChannel.tryLock(position, size, shared);
	}

	@Override
	protected void implCloseChannel() throws IOException {
		fileChannel.close();
	}

	public long getBlocks() {
		return blocks;
	}

	public int getBlockSize() {
		return blockSize;
	}

}
