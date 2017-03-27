package com.devchao.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class DevChaoMap<K, V> implements Map<K, V> {

	private transient int size = 0;
	private transient int defaultLengthForEntry = 16;
	private transient DevChaoEntry<?, ?>[] entrys = null;
	
	
	public DevChaoMap() {
		entrys = new DevChaoEntry<?, ?>[defaultLengthForEntry];
	} 
	
	@Override
	public void clear() {
		this.size = 0;
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V put(K key, V value) {
		Map.Entry<K, V> entry = new DevChaoEntry<K, V>(key, value);
		entrys[size++] = (DevChaoEntry<?, ?>) entry; 
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public V remove(Object key) {
		this.size--;
		return null;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

}
