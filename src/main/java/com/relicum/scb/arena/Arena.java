package com.relicum.scb.arena;

import com.relicum.scb.SCB;
import com.relicum.scb.objects.spawns.ArenaGroupSpawn;
import com.relicum.scb.objects.spawns.ArenaSpawn;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.List;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class Arena implements com.relicum.scb.arena.IArena, Cloneable {

	private String worldName;

	/**
	 * The Max time.
	 */
	private Long maxTime;

	/**
	 * The Min players.
	 */
	private int minPlayers;
	/**
	 * The Max players.
	 */
	private int maxPlayers;
	/**
	 * The Perm.
	 */
	private String perm;
	/**
	 * The Spawns.
	 */
	private ArenaGroupSpawn spawns = null;

	/**
	 * Arena Name
	 */
	private String aname;
	/**
	 * The Id.
	 */
	private Integer id;

	/**
	 * The Status.
	 */
	private ArenaStatus status;

	/**
	 * Is Arena Enabled
	 */
	private boolean enable;

	private Vector adminSpawn;

	/**
	 * The Unique map.
	 */
	private String uniqueMap;

	/**
	 * The Chunks.
	 */
	private List<Vector> chunk;

	private ArenaRegion areg;

	public Arena(Integer id) {
		this.id = id;

	}

	public Arena(String na) {
		setArenaName(na);

	}

	@Override
	public void loadArena() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void saveArena() {
        SCB.getInstance().ARM.saveArenaById(getArenaId());
    }

	/**
	 * Sets arena name.
	 *
	 * @param String
	 */
	public void setArenaName(String n) {
		if (n != null) this.aname = n;
	}


	/**
	 * Gets arena name.
	 *
	 * @return String
	 */
	@Override
	public String getArenaName() {
		if (this.aname != null)
			return this.aname;

		return "";
	}

	/**
	 * Set arena id.
	 *
	 * @param i Integer
	 */
	public void setArenaId(Integer i) {
		if (i != null) this.id = i;
	}

	/**
	 * Gets arena id.
	 *
	 * @return Integer
	 */
	@Override
	public Integer getArenaId() {
		if (this.id != null)
			return this.id;

		return 0;
	}

    /**
     * Needs to be a valid ArenaStatus option
     *
     * @param String
     */
    public void setStatus(String em) {
/*        if(em.equalsIgnoreCase("DISABLED")){
            this.enable = false;
            status = ArenaStatus.valueOf(em);
            this.saveArena();
            return;
        }*/
		status = ArenaStatus.valueOf(em);
	}

	/**
	 * Gets arena status.
	 *
	 * @return the ArenaStatus
	 */
	@Override
	public ArenaStatus getArenaStatus() {

		return this.status;
	}

	/**
	 * Set enable.
	 *
	 * @param boolean
	 */
	public void setEnable(boolean bo) {
		if (bo) this.enable = true;
		else this.enable = false;
	}

	/**
	 * Set arena spawn group. If Arena wasn't created using ID
	 *
	 * @param Integer the ars
	 */
	public void setArenaSpawnGroup(Integer id) {

		this.spawns = new ArenaGroupSpawn(id);
	}

	public void setArenaSpawnGroup(ArenaGroupSpawn ag) {

		this.spawns = ag;
	}

	/**
	 * Is enabled.
	 *
	 * @return boolean
	 */
	@Override
	public boolean isEnabled() {
		return this.enable;
	}

	/**
	 * Add spawn location.
	 *
	 * @param ArenaSpawn
	 */
	public void addSpawnLocation(ArenaSpawn arenaspawn) {
		this.spawns.addSpawn(arenaspawn);
	}

	/**
	 * Gets spawn group.
	 *
	 * @return ArenaGroupSpawn
	 */
	@Override
	public ArenaGroupSpawn getSpawnGroup() {
		return this.spawns;
	}

	public boolean hasGroupSpawn() {
		if (spawns == null) {
			return false;
		}
		return true;
	}

	/**
	 * Set max players.
	 *
	 *
	 * @param int
	 */
	public void setMaxPlayers(int i) {
		this.maxPlayers = i;
	}

	/**
	 * Gets max players.
	 *
	 * @return int
	 */
	@Override
	public int getMaxPlayers() {
		return this.maxPlayers;

	}

	/**
	 * Set min players.
	 *
	 * @param int
	 */
	public void setMinPlayers(int i) {
		this.minPlayers = i;
	}

	/**
	 * Gets min players.
	 *
	 * @return int
	 */
	@Override
	public int getMinPlayers() {
		return this.minPlayers;
	}

	/**
	 * Set perm.
	 *
	 * @param String
	 */
	public void setPerm(String s) {
		this.perm = s;
	}

	/**
	 * Gets permission.
	 *
	 * @return String
	 */
	@Override
	public String getPermmission() {
		return this.perm;
	}

	/**
	 * Set max time.
	 *
	 * @param Long
	 */
	public void setMaxTime(Long l) {
		this.maxTime = l;
	}

	/**
	 * Gets max game time.
	 *
	 * @return Long
	 */
	@Override
	public Long getMaxGameTime() {
		return this.maxTime;
	}

	/**
	 * Set world name as string
	 *
	 * @param String
	 */
	public void setWorldName(String s) {
		this.worldName = s;
	}

	@Override
	public Vector getAdminSpawn() {
		return this.adminSpawn;
	}

	public void setAdminSpawn(Vector v) {
		this.adminSpawn = v;
	}

	/**
	 * Gets arena world. As Bukkit world
	 *
	 * @return World
	 */
	@Override
	public World getArenaWorld() {
		return Bukkit.getWorld(this.worldName);

	}

	/**
	 * Returns The region object
	 *
	 * @return ArenaRegion
	 */
	public ArenaRegion getAreg() {
		return areg;
	}

	/**
	 * Set the region object
	 *
	 * @param ArenaRegion
	 */
	public void setAreg(ArenaRegion areg) {
		this.areg = areg;
	}

	/**
	 * Returns a Status message
	 *
	 * @return String
	 */
	public String getArenaListMessage() {
		//String AL = SCB.getMessageManager().getAdminMessage("command.message.listArenasMessage");
		String en;
		if (isEnabled())
			en = "True";
		else
			en = "False";

		return ChatColor.GREEN + "Arena " + ChatColor.WHITE + getArenaId() + ChatColor.GREEN + " in world " + ChatColor.WHITE + getArenaWorld().getName() + ChatColor.GREEN + ". Enabled = " + ChatColor.WHITE + en + ChatColor.GREEN + " status = " + ChatColor.WHITE + getArenaStatus().name() + ChatColor.RESET;
	}

	/**
	 * Gets chunks.
	 *
	 * @return List<Vector> chunk
	 */
	public List<Vector> getChunk() {
		return chunk;
	}

	/**
	 * Sets chunk.
	 *
	 * @param List<Vector>the chunk
	 */
	public void setChunk(List<Vector> chunk) {
		this.chunk = chunk;
	}

	/**
	 * Gets unique map.
	 *
	 * @return String the unique map
	 */
	public String getUniqueMap() {
		return uniqueMap;
	}

	/**
	 * Sets unique map.
	 *
	 * @param String the unique map
	 */
	public void setUniqueMap(String uniqueMap) {
		this.uniqueMap = uniqueMap;
	}
}
