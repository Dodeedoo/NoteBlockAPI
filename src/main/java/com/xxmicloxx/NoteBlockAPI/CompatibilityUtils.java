package com.xxmicloxx.NoteBlockAPI;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * @deprecated {@link com.xxmicloxx.NoteBlockAPI.utils.CompatibilityUtils}
 */
@Deprecated
public class CompatibilityUtils {

	public static final String OBC_DIR = Bukkit.getServer().getClass().getPackage().getName();
	public static final String NMS_DIR = OBC_DIR.replaceFirst("org.bukkit.craftbukkit", "net.minecraft.server");

	/**
	 * Gets NMS class from given name
	 * @param name of class (w/ package)
	 * @return Class of given name
	 */
	public static Class<?> getMinecraftClass(String name) {
		try {
			return Class.forName(NMS_DIR + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Gets CraftBukkit class from given name
	 * @param name of class (w/ package)
	 * @return Class of given name
	 */
	public static Class<?> getCraftBukkitClass(String name) {
		try {
			return Class.forName(OBC_DIR + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Returns whether the version of Bukkit is or is after 1.12
	 * @return version is after 1.12
	 */
	public static boolean isPost1_12() {
		if (!isSoundCategoryCompatible() || Bukkit.getVersion().contains("1.11")) {
			return false;
		}
		return true;
	}

	/**
	 * Returns if SoundCategory is able to be used
	 * @see org.bukkit.SoundCategory
	 * @see SoundCategory
	 * @return can use SoundCategory
	 */
	protected static boolean isSoundCategoryCompatible() {
		if (Bukkit.getVersion().contains("1.7") 
				|| Bukkit.getVersion().contains("1.8") 
				|| Bukkit.getVersion().contains("1.9") 
				|| Bukkit.getVersion().contains("1.10")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Plays a sound using NMS and reflection
	 * @param player
	 * @param location
	 * @param sound
	 * @param category
	 * @param volume
	 * @param pitch
	 */
	public static void playSound(Player player, Location location, String sound, 
			SoundCategory category, float volume, float pitch) {
		try {
			if (isSoundCategoryCompatible()) {
				Method method = Player.class.getMethod("playSound", Location.class, String.class, 
						Class.forName("org.bukkit.SoundCategory"), float.class, float.class);
				Class<? extends Enum> soundCategory = 
						(Class<? extends Enum>) Class.forName("org.bukkit.SoundCategory");
				Enum<?> soundCategoryEnum = Enum.valueOf(soundCategory, category.name());
				method.invoke(player, location, sound, soundCategoryEnum, volume, pitch);
			} else {
				Method method = Player.class.getMethod("playSound", Location.class, 
						String.class, float.class, float.class);
				method.invoke(player, location, sound, volume, pitch);
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Plays a sound using NMS and reflection
	 * @param player
	 * @param location
	 * @param sound
	 * @param category
	 * @param volume
	 * @param pitch
	 */
	public static void playSound(Player player, Location location, Sound sound, 
			SoundCategory category, float volume, float pitch) {
		try {
			if (isSoundCategoryCompatible()) {
				Method method = Player.class.getMethod("playSound", Location.class, Sound.class, 
						Class.forName("org.bukkit.SoundCategory"), float.class, float.class);
				Class<? extends Enum> soundCategory = 
						(Class<? extends Enum>) Class.forName("org.bukkit.SoundCategory");
				Enum<?> soundCategoryEnum = Enum.valueOf(soundCategory, category.name());
				method.invoke(player, location, sound, soundCategoryEnum, volume, pitch);
			} else {
				Method method = Player.class.getMethod("playSound", Location.class, 
						Sound.class, float.class, float.class);
				method.invoke(player, location, sound, volume, pitch);
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets instruments which were added post-1.12
	 * @return ArrayList of instruments
	 * @deprecated Use {@link #getVersionCustomInstruments(float)}
	 */
	public static ArrayList<CustomInstrument> get1_12Instruments(){
		return getVersionCustomInstruments(0.0112f);
	}

	/**
	 * Return list of instuments which were added in specified version
	 * @param serverVersion 1.12 = 0.0112f, 1.14 = 0.0114f,...
	 * @return list of custom instruments, if no instuments were added in specified version returns empty list
	 */
	public static ArrayList<CustomInstrument> getVersionCustomInstruments(float serverVersion){
		ArrayList<CustomInstrument> instruments = new ArrayList<>();
		if (serverVersion == 0.0112f){
			instruments.add(new CustomInstrument((byte) 0, "Guitar", "guitar.ogg"));
			instruments.add(new CustomInstrument((byte) 0, "Flute", "flute.ogg"));
			instruments.add(new CustomInstrument((byte) 0, "Bell", "bell.ogg"));
			instruments.add(new CustomInstrument((byte) 0, "Chime", "icechime.ogg"));
			instruments.add(new CustomInstrument((byte) 0, "Xylophone", "xylobone.ogg"));
			return instruments;
		}

		if (serverVersion == 0.0114f){
			instruments.add(new CustomInstrument((byte) 0, "Iron Xylophone", "iron_xylophone.ogg"));
			instruments.add(new CustomInstrument((byte) 0, "Cow Bell", "cow_bell.ogg"));
			instruments.add(new CustomInstrument((byte) 0, "Didgeridoo", "didgeridoo.ogg"));
			instruments.add(new CustomInstrument((byte) 0, "Bit", "bit.ogg"));
			instruments.add(new CustomInstrument((byte) 0, "Banjo", "banjo.ogg"));
			instruments.add(new CustomInstrument((byte) 0, "Pling", "pling.ogg"));
			return instruments;
		}
		return instruments;
	}

	/**
	 * Return list of custom instruments based on song first custom instrument index and server version
	 * @param firstCustomInstrumentIndex
	 * @return
	 */
	public static ArrayList<CustomInstrument> getVersionCustomInstrumentsForSong(int firstCustomInstrumentIndex){
		ArrayList<CustomInstrument> instruments = new ArrayList<>();

		if (com.xxmicloxx.NoteBlockAPI.utils.CompatibilityUtils.getServerVersion() < 0.0112f){
			if (firstCustomInstrumentIndex == 10) {
				instruments.addAll(getVersionCustomInstruments(0.0112f));
			} else if (firstCustomInstrumentIndex == 16){
				instruments.addAll(getVersionCustomInstruments(0.0112f));
				instruments.addAll(getVersionCustomInstruments(0.0114f));
			}
		} else if (com.xxmicloxx.NoteBlockAPI.utils.CompatibilityUtils.getServerVersion() < 0.0114f){
			if (firstCustomInstrumentIndex == 16){
				instruments.addAll(getVersionCustomInstruments(0.0114f));
			}
		}

		return instruments;
	}
	
}
