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

public class stat extends Structure {
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

	public stat() {
		super();
		setAlignType(ALIGN_NONE);
	}

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList("st_dev", "st_mode", "st_nlink", "st_ino", "st_uid", "st_gid", "st_rdev", "st_atimespec",
				"st_mtimespec", "st_ctimespec", "st_birthtimespec", "st_size", "st_blocks", "st_blksize", "st_flags",
				"st_gen", "st_lspare", "st_qspare");
	}

	public stat(Pointer peer) {
		super(peer);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((st_atimespec == null) ? 0 : st_atimespec.hashCode());
		result = prime * result + ((st_birthtimespec == null) ? 0 : st_birthtimespec.hashCode());
		result = prime * result + st_blksize;
		result = prime * result + (int) (st_blocks ^ (st_blocks >>> 32));
		result = prime * result + ((st_ctimespec == null) ? 0 : st_ctimespec.hashCode());
		result = prime * result + st_dev;
		result = prime * result + st_flags;
		result = prime * result + st_gen;
		result = prime * result + st_gid;
		result = prime * result + (int) (st_ino ^ (st_ino >>> 32));
		result = prime * result + st_lspare;
		result = prime * result + st_mode;
		result = prime * result + ((st_mtimespec == null) ? 0 : st_mtimespec.hashCode());
		result = prime * result + st_nlink;
		result = prime * result + Arrays.hashCode(st_qspare);
		result = prime * result + st_rdev;
		result = prime * result + (int) (st_size ^ (st_size >>> 32));
		result = prime * result + st_uid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		stat other = (stat) obj;
		if (st_atimespec == null) {
			if (other.st_atimespec != null)
				return false;
		} else if (!st_atimespec.equals(other.st_atimespec))
			return false;
		if (st_birthtimespec == null) {
			if (other.st_birthtimespec != null)
				return false;
		} else if (!st_birthtimespec.equals(other.st_birthtimespec))
			return false;
		if (st_blksize != other.st_blksize)
			return false;
		if (st_blocks != other.st_blocks)
			return false;
		if (st_ctimespec == null) {
			if (other.st_ctimespec != null)
				return false;
		} else if (!st_ctimespec.equals(other.st_ctimespec))
			return false;
		if (st_dev != other.st_dev)
			return false;
		if (st_flags != other.st_flags)
			return false;
		if (st_gen != other.st_gen)
			return false;
		if (st_gid != other.st_gid)
			return false;
		if (st_ino != other.st_ino)
			return false;
		if (st_lspare != other.st_lspare)
			return false;
		if (st_mode != other.st_mode)
			return false;
		if (st_mtimespec == null) {
			if (other.st_mtimespec != null)
				return false;
		} else if (!st_mtimespec.equals(other.st_mtimespec))
			return false;
		if (st_nlink != other.st_nlink)
			return false;
		if (!Arrays.equals(st_qspare, other.st_qspare))
			return false;
		if (st_rdev != other.st_rdev)
			return false;
		if (st_size != other.st_size)
			return false;
		if (st_uid != other.st_uid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "stat [st_dev=" + st_dev + ", st_mode=" + st_mode + ", st_nlink=" + st_nlink + ", st_ino=" + st_ino
				+ ", st_uid=" + st_uid + ", st_gid=" + st_gid + ", st_rdev=" + st_rdev + ", st_atimespec="
				+ st_atimespec + ", st_mtimespec=" + st_mtimespec + ", st_ctimespec=" + st_ctimespec
				+ ", st_birthtimespec=" + st_birthtimespec + ", st_size=" + st_size + ", st_blocks=" + st_blocks
				+ ", st_blksize=" + st_blksize + ", st_flags=" + st_flags + ", st_gen=" + st_gen + ", st_lspare="
				+ st_lspare + ", st_qspare=" + Arrays.toString(st_qspare) + "]";
	}

	public static class ByReference extends stat implements Structure.ByReference {

	};

	public static class ByValue extends stat implements Structure.ByValue {

	};

	public boolean isBlockDevice() {
		int result = st_mode & CLib.S_IFMT;
		if (result == CLib.S_IFBLK) {
			return true;
		}
		return false;
	}
}
