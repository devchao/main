package com.devchao.map;

import java.util.Map;

public class DevChaoEntry<K, V> implements Map.Entry<K, V> {

	private K key;
	private V value;
	
	public DevChaoEntry(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	@Override
	public K getKey() {
		return this.key;
	}

	@Override
	public V getValue() {
		return this.value;
	}

	@Override
	public V setValue(V value) {
		return this.value = value;
	}
	
}
