package com.relicum.scb.configs;

/**
 * Bukkit-SCB
 *
 * @author Relicum
 * @version 0.1
 */
public class ConfigManager {

/*	static ConfigManager instance = new ConfigManager();

	public static ConfigManager getInstance() {
		return instance;
	}

	public void newCreate() {*/

/*
        if (!SCB.getInstance().signFile.exists()) {
			try {
				if (SCB.getInstance().signFile.createNewFile()) {
					SCB.getInstance().Signs = YamlConfiguration.loadConfiguration(SCB.getInstance().getResource("signs.yml"));
				}
			} catch (IOException e) {
				System.out.println(e.getStackTrace().toString());
			} catch (Exception e) {
				System.out.println(e.getStackTrace().toString());
			}
		}


	}

	public void loadFiles() {
		try {
			SCB.getInstance().Signs.load(SCB.getInstance().signFile);

			//SCB.getInstance().groupSpawn.load(SCB.getInstance().groupSpawnFile);
		} catch (FileNotFoundException e) {
			System.out.println(e.getStackTrace().toString());
		} catch (IOException e) {
			System.out.println(e.getStackTrace().toString());
		} catch (InvalidConfigurationException e) {
			System.out.println(e.getStackTrace().toString());
		} catch (Exception e) {
			System.out.println(Arrays.toString(e.getStackTrace()));
		}
	}

*/

/*	public void loadgroupSpawnFile() {
        try {
			SCB.getInstance().groupSpawn.load(SCB.getInstance().groupSpawnFile);
		} catch (FileNotFoundException e) {
			System.out.println("Error " + e.getStackTrace().toString());
		} catch (IOException | InvalidConfigurationException e) {
			System.out.println(e.getStackTrace().toString());
		} catch (Exception e) {
			System.out.println(Arrays.toString(e.getStackTrace()));
		}

	}*/
/*
    public void saveFiles() {

		this.saveSignFile();

		//this.saveGroupSpawnFile();
	}*/


/*	public void saveSignFile() {

		try {
			SCB.getInstance().Signs.save(SCB.getInstance().signFile);
		} catch (IOException e) {
			System.out.println(Arrays.toString(e.getStackTrace()));
		}
	}*/

/*	public void saveGroupSpawnFile() {

		try {
			SCB.getInstance().groupSpawn.save(SCB.getInstance().groupSpawnFile);
		} catch (IOException e) {
			System.out.println(Arrays.toString(e.getStackTrace()));
		}
	}*/

/*	public void loadFile(String file) {
		File t = new File(SCB.getInstance().getDataFolder(), file);
		System.out.println("Writing new file: " + t.getAbsolutePath());
		try {
			t.createNewFile();
			FileWriter out = new FileWriter(t);
			System.out.println(file);
			InputStream is = getClass().getResourceAsStream(file);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				out.write(line + "\n");
				System.out.println(line);
			}
			out.flush();
			is.close();
			isr.close();
			br.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
}

