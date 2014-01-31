package com.relicum.scb.configs;

/**
 * Bukkit-SCB
 * 
 * @author Relicum
 * @version 0.1
 */
public class ConfigManagerold {

    /*
     * static ConfigManagerold instance = new ConfigManagerold();
     * 
     * public static ConfigManagerold getInstance() { return instance; }
     * 
     * public void newCreate() {
     */

    /*
     * if (!SkyApi.getSCB().signFile.exists()) { try { if
     * (SkyApi.getSCB().signFile.createNewFile()) { SkyApi.getSCB().Signs =
     * YamlConfiguration
     * .loadConfiguration(SkyApi.getSCB().getResource("signs.yml")); } } catch
     * (IOException e) { System.out.println(e.getStackTrace().toString()); }
     * catch (Exception e) { System.out.println(e.getStackTrace().toString()); }
     * }
     * 
     * 
     * }
     * 
     * public void loadFiles() { try {
     * SkyApi.getSCB().Signs.load(SkyApi.getSCB().signFile);
     * 
     * //SkyApi.getSCB().groupSpawn.load(SkyApi.getSCB().groupSpawnFile); }
     * catch (FileNotFoundException e) {
     * System.out.println(e.getStackTrace().toString()); } catch (IOException e)
     * { System.out.println(e.getStackTrace().toString()); } catch
     * (InvalidConfigurationException e) {
     * System.out.println(e.getStackTrace().toString()); } catch (Exception e) {
     * System.out.println(Arrays.toString(e.getStackTrace())); } }
     */

    /*
     * public void loadgroupSpawnFile() { try {
     * SkyApi.getSCB().groupSpawn.load(SkyApi.getSCB().groupSpawnFile); } catch
     * (FileNotFoundException e) { System.out.println("Error " +
     * e.getStackTrace().toString()); } catch (IOException |
     * InvalidConfigurationException e) {
     * System.out.println(e.getStackTrace().toString()); } catch (Exception e) {
     * System.out.println(Arrays.toString(e.getStackTrace())); }
     * 
     * }
     */
    /*
     * public void saveFiles() {
     * 
     * this.saveSignFile();
     * 
     * //this.saveGroupSpawnFile(); }
     */

    /*
     * public void saveSignFile() {
     * 
     * try { SkyApi.getSCB().Signs.save(SkyApi.getSCB().signFile); } catch
     * (IOException e) { System.out.println(Arrays.toString(e.getStackTrace()));
     * } }
     */

    /*
     * public void saveGroupSpawnFile() {
     * 
     * try { SkyApi.getSCB().groupSpawn.save(SkyApi.getSCB().groupSpawnFile); }
     * catch (IOException e) {
     * System.out.println(Arrays.toString(e.getStackTrace())); } }
     */

    /*
     * public void loadFile(String file) { File t = new
     * File(SkyApi.getSCB().getDataFolder(), file);
     * System.out.println("Writing new file: " + t.getAbsolutePath()); try {
     * t.createNewFile(); FileWriter out = new FileWriter(t);
     * System.out.println(file); InputStream is =
     * getClass().getResourceAsStream(file); InputStreamReader isr = new
     * InputStreamReader(is); BufferedReader br = new BufferedReader(isr);
     * String line; while ((line = br.readLine()) != null) { out.write(line +
     * "\n"); System.out.println(line); } out.flush(); is.close(); isr.close();
     * br.close(); out.close(); } catch (IOException e) { e.printStackTrace(); }
     * }
     */
}
