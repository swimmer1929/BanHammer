/*******************************************************************************
 * Copyright (c) 2012 James Richardson.
 * 
 * BanHammerPlayerBannedEvent.java is part of BanHammer.
 * 
 * BanHammer is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * BanHammer is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * BanHammer. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package name.richardson.james.bukkit.banhammer.ban.event;

import org.bukkit.event.HandlerList;

import name.richardson.james.bukkit.banhammer.ban.BanRecord;

/**
 * This event is fired every time a player is banned using BanHammer.
 */
public class BanHammerPlayerBannedEvent extends BanHammerPlayerEvent {

	/** The Constant handlers. */
	private static final HandlerList handlers = new HandlerList();

	public static HandlerList getHandlerList() {
		return BanHammerPlayerBannedEvent.handlers;
	}

	public BanHammerPlayerBannedEvent(final BanRecord record, final boolean silent) {
		super(record, silent);
	}

	@Override
	public HandlerList getHandlers() {
		return BanHammerPlayerBannedEvent.handlers;
	}

}
