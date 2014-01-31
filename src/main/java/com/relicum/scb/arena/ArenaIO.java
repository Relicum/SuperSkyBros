package com.relicum.scb.arena;

import java.util.*;
import com.relicum.scb.configs.ArenaConfig;
import com.relicum.scb.objects.ArenaLobby;
import com.relicum.scb.objects.spawns.ArenaGroupSpawn;
import com.relicum.scb.types.SkyApi;
import com.relicum.scb.utils.SerializedLocation;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.util.Vector;

/**
 * The type ArenaIO.
 */
public class ArenaIO {

    private String min;

    private String max;

    private String top;

    private String world;

    private boolean enable;

    private String map;

    private String Perm;

    private Integer arenaId;

    private String arenaPath;

    private boolean doubleJump;

    private ArenaGroupSpawn AGP;

    private String uniqueMap;

    private ArenaStatus status;

    private ArenaLobby arenaLobby;

    private Vector lobbyMax;

    private Vector lobbyMin;

    private Vector lobbySpawn;

    private Integer lobbyId;

    private String lobbyWorld;

    /**
     * The Min players.
     */
    private int minPlayers;

    /**
     * The Max players.
     */
    private int maxPlayers;

    private Long maxTime;

    private Vector vMins;

    private Vector vMaxs;

    private List<Vector> chunk = new ArrayList<>();

    private ConfigurationSection defaults;

    private Vector vTops;

    /**
     * Stores Arena Config Object
     */
    private ArenaConfig config;

    public ArenaIO() {
        this.config = SkyApi.getSm().getArenaConfig();
        if (!this.config.getConfig().contains("ARENADEFAULTS")) {
            this.config.getConfig().createSection("ARENADEFAULTS");
            this.config.getConfig().getConfigurationSection("ARENADEFAULTS");
            this.defaults.set("minPlayers", 4);
            this.defaults.set("maxPlayers", 8);
            this.defaults.set("isEnabled", false);
            this.defaults.set("maxTime", 480000);
            this.defaults.set("doubleJump", true);
            this.defaults.set("arena.arenas", "arena.arenas");
            this.config.saveConfig();
            this.config.reloadConfig();
            System.out.println("There was a problem with the defaults in create arena had to re apply them");
        }

        this.defaults = config.getConfig().getConfigurationSection("ARENADEFAULTS");

    }

    public ArenaConfig getConfig() {
        return this.config;
    }

    /**
     * New create.
     * 
     * @param ar the ar
     * @param ch the ch
     * @return the boolean
     */
    public boolean newCreate(ArenaRegion ar, List<Vector> ch) {

        Integer last = config.getConfig().getInt("arena.lastId");
        last += 1;
        Integer to = config.getConfig().getInt("arena.total");
        to += 1;

        setWorld(ar.getWorld().getName());
        setMap(ar.getMn());
        // setEnable(this.defaults.getBoolean("isEnabled"));
        setMinPlayers(this.defaults.getInt("minPlayers"));
        setMaxPlayers(this.defaults.getInt("maxPlayers"));
        setMaxTime(this.defaults.getLong("maxTime"));
        setDoubleJump(this.defaults.getBoolean("doubleJump"));
        setStatus(ArenaStatus.DISABLED);
        setArenaId(last);
        setPerm("ssb.player.arena." + last.toString());
        setvMin(ar.getMinVector());
        setvMax(ar.getMaxVector());
        setvTop(ar.getAdminSpawnVector());
        setUniqueMap(ar.getUmname());
        setChunks(ch);
        setLobbyId(last);
        setLobbyMax(new Vector(0, 0, 0));
        setLobbyMin(new Vector(0, 0, 0));
        setLobbySpawn(new Vector(0, 0, 0));
        setLobbyWorld("world");

        String path = getArenaPath();

        try {
            config.getConfig().set(path, getArenaHash());
            config.getConfig().set("arena.lastId", last);
            config.getConfig().set("arena.total", to);
            config.saveConfig();
            config.reloadConfig();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        System.out.println(path);
        ConfigurationSection cs = config.getConfig().getConfigurationSection(path);
        Arena arena = this.makeArena(last, cs);
        if (SkyApi.getArenaManager().addNewArena(arena)) {
            System.out.println("Arena successfully added to Arena Manager");
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getArenaHash() {

        Map<String, Object> arena = new HashMap<String, Object>();
        arena.put("region.min", getvMin());
        arena.put("region.max", getvMax());
        arena.put("region.top", getvTop());
        arena.put("lobby.min", getLobbyMin());
        arena.put("lobby.max", getLobbyMax());
        arena.put("lobby.spawn", getLobbySpawn());
        arena.put("lobby.id", getLobbyId());
        arena.put("lobby.world", getLobbyWorld());
        arena.put("world", getWorld());
        arena.put("enabled", isEnable());
        arena.put("map", getMap());
        arena.put("umap", getUniqueMap());
        arena.put("permission", getPerm());
        arena.put("settings.minPlayers", getMinPlayers());
        arena.put("settings.maxPlayers", getMaxPlayers());
        arena.put("settings.maxTime", getMaxTime());
        arena.put("settings.doubleJump", getDoubleJump());
        arena.put("status", getStatus().name());
        arena.put("chunks", getChunks());

        return arena;
    }

    @SuppressWarnings("unchecked")
    public Map<Integer, Arena> loadArenas() {

        Set<String> f = getArenaKeys();
        Map<Integer, Arena> ar = new HashMap<>(f.size());
        SpawnIO SIO = new SpawnIO();
        for (String k : f) {
            String path = "arena.arenas." + k;
            String spath = "group.groups." + k;
            ConfigurationSection spn = SkyApi.getSm().getSpawnConfig().getConfig().getConfigurationSection(spath);
            ConfigurationSection st = config.getConfig().getConfigurationSection(path);
            Arena na = makeArena(Integer.parseInt(k), st);
            ar.put(Integer.parseInt(k), na);
            SkyApi.getSCB().getLogger().info("Arena " + k + " has been loaded");

            if (na.hasGroupSpawn()) {
                ArenaGroupSpawn ags = SIO.makeGroupSpawn(Integer.parseInt(k), spn);
                na.setArenaSpawnGroup(ags);
                SkyApi.getSCB().getLogger().info("ArenaSpawnGroup for arena " + k + " has been loaded and applied");
            }
            if (spn != null) {
                ArenaGroupSpawn ags = SIO.makeGroupSpawn(Integer.parseInt(k), spn);
                na.setArenaSpawnGroup(ags);
                SkyApi.getSCB().getLogger().info("ArenaSpawnGroup for arena " + k + " has been loaded and applied");

            }
            /*
             * if (config.getConfig().getInt("arena.total") > 0 ||
             * na.hasGroupSpawn()) {
             * 
             * ArenaGroupSpawn ags = SIO.makeGroupSpawn(Integer.parseInt(k),
             * spn); na.setArenaSpawnGroup(ags);
             * SkyApi.getSCB().getLogger().info("ArenaSpawnGroup for arena " + k
             * + " has been loaded and applied");
             * 
             * }
             */

        }
        return ar;
    }

    public Set<String> getArenaKeys() {
        return config.getConfig().getConfigurationSection("arena.arenas").getKeys(false);
    }

    /**
     * Get a String representing Vector Containing min
     * 
     * @return String
     * @throws IllegalArgumentException if the new value is not acceptable.
     */
    public String getMin() {
        return min;
    }

    /**
     * Get a Vector Containing max
     * 
     * @return String
     * @throws IllegalArgumentException if the new value is not acceptable.
     */
    public String getMax() {
        return max;
    }

    /**
     * Set Vector for min
     * 
     * @param pos1 String
     * @throws IllegalArgumentException if the new value is not acceptable.
     */
    public void setMin(String pos1) {
        this.min = pos1;
    }

    /**
     * Set Vector for max
     * 
     * @param pos2 String
     * @throws IllegalArgumentException
     */
    public void setMax(String pos2) {
        this.max = pos2;
    }

    /**
     * Get the world name as a string
     * 
     * @return String
     */
    public String getWorld() {
        return world;
    }

    /**
     * Set the world name as a string
     * 
     * @param world String
     */
    public void setWorld(String world) {
        this.world = world;
    }

    /**
     * Returns if the arena is enabled
     * 
     * @return boolean
     */
    public boolean isEnable() {
        return enable;
    }

    /**
     * If to set this arena to enable or not
     * 
     * @param enable boolean
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    /**
     * Returns the map name of the arena
     * 
     * @return String
     */
    public String getMap() {
        return map;
    }

    /**
     * Sets the map name of the arena
     * 
     * @param map String
     */
    public void setMap(String map) {
        this.map = map;
    }

    /**
     * Get the permission of the arena
     * 
     * @return String
     */
    public String getPerm() {
        return Perm;
    }

    /**
     * Set the permission of the arena
     * 
     * @param perm String
     */
    public void setPerm(String perm) {
        Perm = perm;
    }

    /**
     * Get the arena id
     * 
     * @return Integer
     */
    public Integer getArenaId() {
        return arenaId;
    }

    /**
     * Set the arena id
     * 
     * @param arenaId Integer
     */
    public void setArenaId(Integer arenaId) {
        this.arenaId = arenaId;
        this.setArenaPath();
    }

    /**
     * Set the arena path for storage
     */
    public void setArenaPath() {

        this.arenaPath = (this.defaults.getString("arenaPath") + "." + getArenaId());
    }

    /**
     * Get the arena path for to use for looking up from storage
     * 
     * @return String
     */
    public String getArenaPath() {
        return arenaPath;
    }

    /**
     * Returns Max Points as a Vector
     * 
     * @return Vector
     */
    public Vector getvMax() {
        return vMaxs;
    }

    /**
     * Set Vector for the max points
     * 
     * @param vMax Vector
     */
    public void setvMax(Vector vMax) {
        this.vMaxs = vMax;
    }

    /**
     * Returns Min Points as a Vector
     * 
     * @return Vector
     */
    public Vector getvMin() {
        return vMins;
    }

    /**
     * Set Vector for the min points
     * 
     * @param vMin Vector
     */
    public void setvMin(Vector vMins) {
        this.vMins = vMins;
    }

    /**
     * AdminSpawn Vector
     * 
     * @return Vector
     */
    public Vector getvTop() {
        return vTops;
    }

    /**
     * Set Vector for admin spawn
     * 
     * @param vTop Vector
     */
    public void setvTop(Vector vTop) {
        this.vTops = vTop;
    }

    /**
     * Set Admin Spawn as string
     * 
     * @param st String
     */
    public void setTop(String st) {

        this.top = st;
    }

    /**
     * Get Admin Spawn as a String representing a Vector
     * 
     * @return String
     */
    public String getTop() {

        return this.top;
    }

    /**
     * Get the max time of game
     * 
     * @return Long
     */
    public Long getMaxTime() {
        return maxTime;
    }

    /**
     * Set the max time of game
     * 
     * @param Long
     */
    public void setMaxTime(Long maxTime) {
        this.maxTime = maxTime;
    }

    /**
     * Get the minimum number player for game
     * 
     * @return int
     */
    public int getMinPlayers() {
        return minPlayers;
    }

    /**
     * Set the minimum number players for game
     * 
     * @param int
     */
    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    /**
     * Get the maximum number players for game
     * 
     * @return int
     */
    public int getMaxPlayers() {
        return maxPlayers;
    }

    /**
     * Set the maximum number players for game
     * 
     * @param int
     */
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public boolean getDoubleJump() {
        return doubleJump;
    }

    public void setDoubleJump(boolean doubleJump) {
        this.doubleJump = doubleJump;
    }

    public ArenaLobby getArenaLobby() {
        return arenaLobby;
    }

    public void setArenaLobby(ArenaLobby arenaLobby) {
        this.arenaLobby = arenaLobby;
    }

    /**
     * Get the ArenaStatus
     * 
     * @return ArenaStatus
     */
    public ArenaStatus getStatus() {
        return status;
    }

    /**
     * Set the ArenaStatus
     * 
     * @param ArenaStatus
     */
    public void setStatus(ArenaStatus status) {
        this.status = status;
        if (status.name().equalsIgnoreCase("DISABLED")) {
            this.enable = false;
        }
    }

    /**
     * Set chunks list
     * 
     * @param List<Vector> the chunks
     */
    public void setChunks(List<Vector> chunks) {

        this.chunk = new ArrayList<>(chunks.size());

        for (Vector s : chunks)
            this.chunk.add(s);
    }

    /**
     * Get chunks list
     * 
     * @return List<Vector> the list
     */
    public List<Vector> getChunks() {
        return this.chunk;
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
     * @param String
     */
    public void setUniqueMap(String m) {
        this.uniqueMap = m;
    }

    @SuppressWarnings("unchecked")
    public Arena makeArena(Integer id, ConfigurationSection st) {

        Arena arena = new Arena(id);
        arena.setArenaName(st.getString("map"));
        arena.setEnable(st.getBoolean("enabled"));
        arena.setMinPlayers(st.getInt("settings.minPlayers"));
        arena.setMaxPlayers(st.getInt("settings.maxPlayers"));
        arena.setMaxTime(st.getLong("settings.maxTime"));
        arena.setDoubleJump(st.getBoolean("settings.doubleJump"));
        arena.setAdminSpawn(st.getVector("region.top"));
        arena.setPerm(st.getString("permission"));
        arena.setStatus(st.getString("status"));
        arena.setUniqueMap(st.getString("umap"));
        ArenaRegion ag = new ArenaRegion(st.getVector("region.min"), st.getVector("region.max"), st.getVector("region.top"), id, st.getString("world"), st.getString("map"),
                                         (SerializedLocation) st.get("lmax"), (SerializedLocation) st.get("lmin"), (SerializedLocation) st.get("lad"));
        arena.setChunk((List<Vector>) st.get("chunks"));
        arena.setAreg(ag);
        arena.setWorldName(st.getString("world"));
        ArenaLobby al = new ArenaLobby(st.getVector("lobby.max"), st.getVector("lobby.mix"), st.getString("lobby.world"), st.getVector("lobby.spawn"), id);
        arena.setArenaLobby(al);

        return arena;

    }

    /**
     * Gets lobby max.
     * 
     * @return the lobby max
     */
    public Vector getLobbyMax() {
        return lobbyMax;
    }

    /**
     * Sets lobby max.
     * 
     * @param lobbyMax the lobby max
     */
    public void setLobbyMax(Vector lobbyMax) {
        this.lobbyMax = lobbyMax;
    }

    /**
     * Gets lobby min.
     * 
     * @return the lobby min
     */
    public Vector getLobbyMin() {
        return lobbyMin;
    }

    /**
     * Sets lobby min.
     * 
     * @param lobbyMin the lobby min
     */
    public void setLobbyMin(Vector lobbyMin) {
        this.lobbyMin = lobbyMin;
    }

    /**
     * Gets lobby spawn.
     * 
     * @return the lobby spawn
     */
    public Vector getLobbySpawn() {
        return lobbySpawn;
    }

    /**
     * Sets lobby spawn.
     * 
     * @param lobbySpawn the lobby spawn
     */
    public void setLobbySpawn(Vector lobbySpawn) {
        this.lobbySpawn = lobbySpawn;
    }

    /**
     * Gets lobby id.
     * 
     * @return the lobby id
     */
    public Integer getLobbyId() {
        return lobbyId;
    }

    /**
     * Sets lobby id.
     * 
     * @param lobbyId the lobby id
     */
    public void setLobbyId(Integer lobbyId) {
        this.lobbyId = lobbyId;
    }

    /**
     * Gets lobby world.
     * 
     * @return the lobby world
     */
    public String getLobbyWorld() {
        return lobbyWorld;
    }

    /**
     * Sets lobby world.
     * 
     * @param lobbyWorld the lobby world
     */
    public void setLobbyWorld(String lobbyWorld) {
        this.lobbyWorld = lobbyWorld;
    }

    /**
     * Gets Arena Group Spawn
     * 
     * @return ArenaGroupSpawn
     */
    public ArenaGroupSpawn getAGP() {
        return AGP;
    }

    /**
     * Sets Arena Group Spawn
     * 
     * @param ArenaGroupSpawn the aGP
     */
    public void setAGP(ArenaGroupSpawn a) {
        this.AGP = a;
    }

    public boolean saveArena(Arena arena) {

        try {
            ConfigurationSection cs = config.getConfig().getConfigurationSection("arena.arenas." + arena.getArenaId());
            cs.set("chunks", arena.getChunk());
            cs.set("enabled", arena.isEnabled());
            cs.set("settings.maxTime", arena.getMaxGameTime());
            cs.set("settings.maxPlayers", arena.getMaxPlayers());
            cs.set("settings.minPlayers", arena.getMinPlayers());
            cs.set("settings.doubleJump", arena.getDoubleJump());
            cs.set("status", arena.getArenaStatus().name());
            cs.set("umap", arena.getUniqueMap());
            cs.set("permission", arena.getPermmission());
            cs.set("world", arena.getArenaWorld().getName());
            config.saveConfig();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
