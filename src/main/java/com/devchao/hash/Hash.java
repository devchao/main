package com.devchao.hash;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

public class Hash {

	public static void main(String[] args) {

		int real = 8, virtual = 100;// 8 true node, each node has 100 virtual nodes
		int[] hit = new int[real];
		for (int i = 0; i < real; i++) {
			hit[i] = 0;
		}

		SortedMap<Long, Integer> virtualMap = new TreeMap<>();
		for (int i = 0; i < real; i++) {
			for (int j = 0; j < virtual; j++) {
				virtualMap.put(Math.abs(hash("real" + i + "virtual" + j)), i);
			}
		}

		for (int i = 0; i < 100000; i++) {
			SortedMap<Long, Integer> tailMap = virtualMap
					.tailMap(Math.abs(hash("uid-" + new Random().nextInt(10000000))));
			if (tailMap.isEmpty()) {
				hit[virtualMap.get(virtualMap.firstKey())]++;
			} else {
				hit[tailMap.get(tailMap.firstKey())]++;
			}
		}

		for (int i = 0; i < real; i++) {
			System.out.println("redis-" + i + ":" + hit[i]);
		}
	}

	/**
	 * MurMurHash算法
	 */
	private static Long hash(String key) {

		ByteBuffer buf = ByteBuffer.wrap(key.getBytes());
		int seed = 0x1234ABCD;

		ByteOrder byteOrder = buf.order();
		buf.order(ByteOrder.LITTLE_ENDIAN);

		long m = 0xc6a4a7935bd1e995L;
		int r = 47;

		long h = seed ^ (buf.remaining() * m);

		long k;
		while (buf.remaining() >= 8) {
			k = buf.getLong();

			k *= m;
			k ^= k >>> r;
			k *= m;

			h ^= k;
			h *= m;
		}

		if (buf.remaining() > 0) {
			ByteBuffer finish = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
			finish.put(buf).rewind();
			h ^= finish.getLong();
			h *= m;
		}

		h ^= h >>> r;
		h *= m;
		h ^= h >>> r;

		buf.order(byteOrder);
		return h;
	}
}