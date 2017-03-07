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

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class stat64 extends Structure {
	/** C type : dev_t */
	public int st_dev;
	/** C type : mode_t */
	public short st_mode;
	/** C type : nlink_t */
	public short st_nlink;
	/** C type : __darwin_ino64_t */
	public long st_ino;
	/** C type : uid_t */
	public int st_uid;
	/** C type : gid_t */
	public int st_gid;
	/** C type : dev_t */
	public int st_rdev;
	/** C type : timespec */
	public timespec st_atimespec;
	/** C type : timespec */
	public timespec st_mtimespec;
	/** C type : timespec */
	public timespec st_ctimespec;
	/** C type : timespec */
	public timespec st_birthtimespec;
	/** C type : off_t */
	public long st_size;
	/** C type : blkcnt_t */
	public long st_blocks;
	/** C type : blksize_t */
	public int st_blksize;
	public int st_flags;
	public int st_gen;
	public int st_lspare;
	/** C type : __int64_t[2] */
	public long[] st_qspare = new long[2];

	public stat64() {
		super();
	}

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList("st_dev", "st_mode", "st_nlink", "st_ino", "st_uid", "st_gid", "st_rdev", "st_atimespec",
				"st_mtimespec", "st_ctimespec", "st_birthtimespec", "st_size", "st_blocks", "st_blksize", "st_flags",
				"st_gen", "st_lspare", "st_qspare");
	}

	public stat64(Pointer peer) {
		super(peer);
	}

	public static class ByReference extends stat64 implements Structure.ByReference {

	};

	public static class ByValue extends stat64 implements Structure.ByValue {

	};

	public boolean isBlockDevice() {
		int result = st_mode & CLib.S_IFMT;
		if (result == CLib.S_IFBLK) {
			return true;
		}
		return false;
	}
}
