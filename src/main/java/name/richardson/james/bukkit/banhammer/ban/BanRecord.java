/*******************************************************************************
 * Copyright (c) 2012 James Richardson.
 *
 * BanRecord.java is part of BanHammer.
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
package name.richardson.james.bukkit.banhammer.ban;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.MessageFormat;

import com.avaje.ebean.annotation.CacheStrategy;
import com.avaje.ebean.validation.NotNull;

@Entity()
@CacheStrategy(readOnly = false, useBeanCache = true, warmingQuery ="order by id")
@Table(name = "banhammer_bans")
public class BanRecord {

	/**
	 * The created at.
	 */
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp createdAt;
	/**
	 * The creator.
	 */
	@ManyToOne(targetEntity = PlayerRecord.class, fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
	@PrimaryKeyJoinColumn(name = "creatorId", referencedColumnName = "id")
	private PlayerRecord creator;
	/**
	 * The expires at.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp expiresAt;
	/**
	 * The id.
	 */
	@Id
	private int id;
	/**
	 * The player.
	 */
	@ManyToOne(targetEntity = PlayerRecord.class, fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
	@PrimaryKeyJoinColumn(name = "playerId", referencedColumnName = "id")
	private PlayerRecord player;
	/**
	 * The reason.
	 */
	@NotNull
	private String reason;
	/**
	 * The state.
	 */
	@NotNull
	private State state;

	/**
	 * Gets the created at.
	 *
	 * @return the created at
	 */
	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	/**
	 * Sets the created at.
	 *
	 * @param time the new created at
	 */
	public void setCreatedAt(final Timestamp time) {
		this.createdAt = time;
	}

	/**
	 * Gets the creator.
	 *
	 * @return the creator
	 */
	@ManyToOne(targetEntity = PlayerRecord.class)
	public PlayerRecord getCreator() {
		return this.creator;
	}

	/**
	 * Sets the creator.
	 *
	 * @param creator the new creator
	 */
	public void setCreator(final PlayerRecord creator) {
		this.creator = creator;
	}

	/**
	 * Gets the expires at.
	 *
	 * @return the expires at
	 */
	public Timestamp getExpiresAt() {
		return this.expiresAt;
	}

	/**
	 * Sets the expires at.
	 *
	 * @param expiresAt the new expires at
	 */
	public void setExpiresAt(final Timestamp expiresAt) {
		this.expiresAt = expiresAt;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public PlayerRecord getPlayer() {
		return this.player;
	}

	/**
	 * Sets the player.
	 *
	 * @param player the new player
	 */
	public void setPlayer(final PlayerRecord player) {
		this.player = player;
	}

	/**
	 * Gets the reason.
	 *
	 * @return the reason
	 */
	public String getReason() {
		return this.reason;
	}

	/**
	 * Sets the reason.
	 *
	 * @param reason the new reason
	 */
	public void setReason(final String reason) {
		this.reason = reason;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public State getState() {
		if (this.state == State.NORMAL && this.hasExpired()) return State.EXPIRED;
		return this.state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(final State state) {
		this.state = state;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public BanRecord.Type getType() {
		return (this.expiresAt == null) ? BanRecord.Type.PERMANENT : BanRecord.Type.TEMPORARY;
	}

	private boolean hasExpired() {
		if (this.getType() == Type.TEMPORARY) {
			return ((this.expiresAt.getTime() - System.currentTimeMillis()) < 0);
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "BanRecord{" +
		"createdAt=" + createdAt +
		", creator=" + creator +
		", expiresAt=" + expiresAt +
		", id=" + id +
		", player=" + player +
		", reason='" + reason + '\'' +
		", state=" + state +
		"} ";
	}

	/**
	 * The valid states of a BanRecord
	 */
	public enum State {

		/**
		 * If a ban is currently active.
		 */
		NORMAL,

		/**
		 * If a ban has expired.
		 */
		EXPIRED,

		/**
		 * If a ban has been pardoned.
		 */
		PARDONED
	}

	/**
	 * The valid types of a BanRecord
	 */
	public enum Type {

		/**
		 * A ban which will never expire.
		 */
		PERMANENT,

		/**
		 * A ban which will expire after a period of time.
		 */
		TEMPORARY
	}

}
