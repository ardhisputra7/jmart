package com.ArdhiJmartBO.dbjson;
import java.util.HashMap;

/**
 * Class untuk di implementasikan pada class lain untuk Serializable
 * @author Ardhi Putra
 */
public class Serializable implements Comparable<Serializable>
{
	private static HashMap<Class<?>, Integer> mapCounter = new HashMap<>();
	public final int id;

	/**
	 * constructor class
	 */
	protected Serializable()
	{
		Integer counter = mapCounter.get(getClass());
		counter = counter == null ? 0 : counter + 1;
		mapCounter.put(getClass(), counter);
		this.id = counter;
	}

	/**
	 * method untuk melakukan set pada id
	 * @param clazz objek yang ingin diproses
	 * @param id id yang ingin diproses
	 * @param <T> tipe class yang di input
	 * @return melakukan return hashmap dari clazz dan id
	 */
	public static <T extends Serializable> Integer setClosingId(Class<T> clazz, int id)
	{
		return mapCounter.put(clazz, id);
	}

	/**
	 * method untuk melakukan get pada id
	 * @param clazz objek yang ingin diproses
	 * @param <T> tipe class yang di input
	 * @return melakukan return hashmap dari clazz
	 */
	public static <T extends Serializable> Integer getClosingId(Class<T> clazz)
	{
		return mapCounter.get(clazz);
	}

	/**
	 * Method menentukan object sesuai atau tidak
	 * @param other object yang ingin dibandingkan
	 * @return mengembalikan true jika other merupakan instance serializable
	 */
	public boolean equals(Object other)
	{
		return other instanceof Serializable && ((Serializable) other).id == id;
	}

	/**
	 *  Method menentukan object sesuai atau tidak
	 * @param other object yang ingin dibandingkan
	 * @return mengembalikan true jika id dari objek sesuai dengan id
	 */
	public boolean equals(Serializable other)
	{
		return other.id == id;
	}

	/**
	 * method untuk membandingkan Seriazable
	 * @param other object yang ingin dibandingkan
	 * @return hasil integer dari compare
	 */
	@Override
	public int compareTo(Serializable other)
	{
		return Integer.compare(this.id, other.id);
	}
}

