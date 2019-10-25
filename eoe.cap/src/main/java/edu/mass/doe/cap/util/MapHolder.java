package edu.mass.doe.cap.util;

import java.util.List;

/**
 * The Class MapHolder.
 *
 * @param <T> the generic type
 * @param <V> the value type
 */
public class MapHolder<T,V> {

	private List<MapValue<T,V>> holder;
	
	

	/**
	 * Gets the holder.
	 *
	 * @return the holder
	 */
	public List<MapValue<T,V>> getHolder() {
		return holder;
	}

	/**
	 * Sets the holder.
	 *
	 * @param holder the holder
	 */
	public void setHolder(List<MapValue<T,V>> holder) {
		this.holder = holder;
	}

}
