package com.relicum.scb.objects.world;

/**
 * SuperSkyBros First Created 03/10/13 Used to read from XStream serialized files
 *
 * @author Relicum
 * @version 0.1
 */
public class XStreamReader {
    /*
     * XStreamReader() {
     * 
     * }
     * 
     * 
     * public static FileInputStream getFi(String filePath) {
     * preChecks(filePath); return getFile(filePath); }
     * 
     * 
     * public static worldSettings load(String filePath) { XStream x = new
     * XStream(); FileInputStream fileInputStream =
     * XStreamReader.getFi(filePath); worldSettings ws = null; try {
     * ObjectInputStream in = x.createObjectInputStream(fileInputStream); ws =
     * (worldSettings) in.readObject(); in.close(); fileInputStream.close(); x =
     * null; } catch (IOException e) { e.printStackTrace(); } catch
     * (ClassNotFoundException e) { e.printStackTrace(); }
     * 
     * return ws; }
     * 
     * 
     * private static boolean preChecks(String filePath) {
     * 
     * File f = new File(filePath);
     * 
     * if (!f.exists()) {
     * System.out.println("Reader is creating a new file with path " +
     * filePath); try { if (!f.createNewFile()) {
     * System.out.println("Error creating file " + filePath); return false; } if
     * (!f.canWrite()) { System.out.println("Error file is not writable" +
     * filePath); return false; } } catch (IOException e) {
     * 
     * e.printStackTrace(); return false; } }
     * 
     * return true; }
     * 
     * 
     * private static FileInputStream getFile(String filePath) {
     * 
     * FileInputStream fi = null; try { fi = new FileInputStream(filePath);
     * 
     * } catch (FileNotFoundException e) { e.printStackTrace();
     * 
     * } return fi; }
     */
}
