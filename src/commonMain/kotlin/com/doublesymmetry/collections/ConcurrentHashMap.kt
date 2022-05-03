package com.doublesymmetry.collections

internal const val INITIAL_CAPACITY = 32

expect class ConcurrentHashMap<K, V>(initialCapacity: Int = INITIAL_CAPACITY): MutableMap<K, V> {
    fun computeIfAbsent(key: K, block: (K) -> V): V
}