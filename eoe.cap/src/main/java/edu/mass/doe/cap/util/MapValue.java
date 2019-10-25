package edu.mass.doe.cap.util;

/**
 * The Class MapValue.
 *
 * @param <T> the generic type
 * @param <V> the value type
 */
public class MapValue<T,V>{
	
	T key;
	 V value;
	
	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public T getKey() {
		return key;
	}
	
	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKey(T key) {
		this.key = key;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public V getValue() {
		return value;
	}
	
	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(V value) {
		this.value = value;
	}
	
	/**
	 * Instantiates a new map value.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public MapValue(T key,V value) {
		this.key = key;
		this.value = value;
	}
	
	 @Override
		public String toString() {
			return "MapValue [key=" + key + ", value=" + value + "]";
		}
	
	
}
