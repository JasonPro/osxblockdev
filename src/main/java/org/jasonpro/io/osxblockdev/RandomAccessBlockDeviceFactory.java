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
import java.io.RandomAccessFile;

import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

/**
 * @author Jason Protheroe
 *
 */
public class RandomAccessBlockDeviceFactory {
	public RandomAccessBlockDeviceFactory() {
		// TODO: check platform
	}

	public RandomAccessFile open(File file, String mode) throws FileNotFoundException {
		int fileDescriptor;
		long size = -1;
		LongByReference blocks = new LongByReference(-1);
		IntByReference blockSize = new IntByReference(-1);

		final stat.ByReference thestat = new stat.ByReference();
		fileDescriptor = CLib.INSTANCE.open(file.getPath().toString(), CLib.O_RDONLY, 0);
		if (fileDescriptor < 0) {
			throw new FileNotFoundException("Unable to open file");
		}
		CLib.INSTANCE.fstat64(fileDescriptor, thestat);
		if (thestat.isBlockDevice()) {
			CLib.INSTANCE.ioctl(fileDescriptor, CLib.DKIOCGETBLOCKSIZE, blockSize);
			CLib.INSTANCE.ioctl(fileDescriptor, CLib.DKIOCGETBLOCKCOUNT, blocks);
			size = blockSize.getValue() * blocks.getValue();
			CLib.INSTANCE.close(fileDescriptor);
			return new RandomAccessBlockDevice(file, mode, blocks.getValue(), blockSize.getValue(), size);
		} else {
			CLib.INSTANCE.close(fileDescriptor);
			return new RandomAccessFile(file, mode);
		}
	}

}
