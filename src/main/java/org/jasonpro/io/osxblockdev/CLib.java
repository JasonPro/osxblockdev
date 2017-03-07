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

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Platform;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

/**
 * This interface exposes the necessary functions for supporting block devices
 * from the libc library.
 * 
 * @author jasonprotheroe
 *
 */
public interface CLib extends Library {
	CLib INSTANCE = Native.loadLibrary((Platform.isWindows() ? "msvcrt" : "c"), CLib.class);

	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_IFMT = 0170000;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_IFIFO = 010000;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_IFCHR = 020000;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_IFDIR = 040000;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_IFBLK = 060000;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_IFREG = 0100000;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_IFLNK = 0120000;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_IFSOCK = 0140000;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_IFWHT = 0160000;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_IRWXU = 000700;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_IRUSR = 000400;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_IWUSR = 000200;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_IXUSR = 000100;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_IRWXG = 000070;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_IRGRP = 000040;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_IWGRP = 000020;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_IXGRP = 000010;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_IRWXO = 000007;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_IROTH = 000004;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_IWOTH = 000002;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_IXOTH = 000001;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_ISUID = 004000;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_ISGID = 002000;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_ISVTX = 001000;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_ISTXT = 001000;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_IREAD = 000400;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_IWRITE = 000200;
	/** <i>native declaration : /usr/include/sys/_types/_s_ifmt.h</i> */
	public static final int S_IEXEC = 000100;
	public static final int ACCESSPERMS = 000700 | 000070 | 000007;
	public static final int ALLPERMS = 004000 | 002000 | 001000 | 000700 | 000070 | 000007;
	public static final int DEFFILEMODE = 000400 | 000200 | 000040 | 000020 | 000004 | 000002;
	public static final int S_BLKSIZE = 512;

	int stat64(String charPtr1, stat.ByReference statPtr1);

	int fstat64(int filedes, stat.ByReference statPtr1);

	int open(String path, int flags, int perm);

	int close(int fd);

	int ioctl(int filedes, int request, long[] arg);

	int ioctl(int filedes, int request, int[] arg);

	int ioctl(int filedes, int request, NativeLong arg);

	int ioctl(int filedes, int request, IntByReference arg);

	int ioctl(int filedes, int request, LongByReference arg);

	final int DKIOCGETBLOCKSIZE = 1074029592;
	final int DKIOCGETBLOCKCOUNT = 1074291737;

	final int O_RDONLY = 0;
	final int O_RDWR = 2;
	final int O_CREAT = 512;
	final int O_RDWR_OR_O_CREAT = 514;

}