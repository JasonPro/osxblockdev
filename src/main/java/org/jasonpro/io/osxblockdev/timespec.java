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

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class timespec extends Structure {
	/** C type : __darwin_time_t */
	public NativeLong tv_sec;
	public NativeLong tv_nsec;

	public timespec() {
		super();
	}

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList("tv_sec", "tv_nsec");
	}

	/**
	 * @param tv_sec
	 *            C type : __darwin_time_t
	 */
	public timespec(NativeLong tv_sec, NativeLong tv_nsec) {
		super();
		this.tv_sec = tv_sec;
		this.tv_nsec = tv_nsec;
	}

	public timespec(Pointer peer) {
		super(peer);
	}

	public static class ByReference extends timespec implements Structure.ByReference {

	};

	public static class ByValue extends timespec implements Structure.ByValue {

	};
}
