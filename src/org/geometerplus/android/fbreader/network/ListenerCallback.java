/*
 * Copyright (C) 2010-2011 Geometer Plus <contact@geometerplus.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */

package org.geometerplus.android.fbreader.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.geometerplus.fbreader.network.INetworkLink;
import org.geometerplus.fbreader.network.NetworkLibrary;

public class ListenerCallback extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		final NetworkLibrary library = NetworkLibrary.Instance();

		if ("android.fbreader.action.network.SIGNIN".equals(intent.getAction())) {
			final String url = intent.getStringExtra(UserRegistrationConstants.CATALOG_URL);
			final INetworkLink link = library.getLinkByUrl(url);
			if (link != null) {
				Util.processSignup(link, android.app.Activity.RESULT_OK, intent);
			}
			library.fireModelChangedEvent(NetworkLibrary.ChangeListener.Code.SignedIn);
		} else {
			library.fireModelChangedEvent(NetworkLibrary.ChangeListener.Code.SomeCode);
		}
	}
}