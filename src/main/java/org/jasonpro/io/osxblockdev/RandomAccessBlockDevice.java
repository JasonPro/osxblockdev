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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author Jason Protheroe
 *
 */
public class RandomAccessBlockDevice extends RandomAccessFile {
	private long blocks = 0;
	private int blockSize = 512;
	private long size = 0;

	protected RandomAccessBlockDevice(File file, String mode, long blocks, int blockSize, long size)
			throws FileNotFoundException {
		super(file, mode);
		this.blocks = blocks;
		this.blockSize = blockSize;
		this.size = size;
	}

	@Override
	public long length() throws IOException {
		return size;
	}

	@Override
	public void setLength(long newLength) throws IOException {
		throw new IOException("Unable to set length of block devices");
	}

	public long getBlocks() {
		return blocks;
	}

	public int getBlockSize() {
		return blockSize;
	}

}
