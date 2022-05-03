package com.doublesymmetry.collections

import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized

actual class ConcurrentHashMap<K, V> actual constructor(initialCapacity: Int): MutableMap<K, V>, SynchronizedObject() {
    private val inner = LinkedHashMap<K, V>(initialCapacity)

    actual fun computeIfAbsent(key: K, block: (K) -> V): V = synchronized(this) {
        if (inner.containsKey(key)) return inner[key]!!
        val value = block(key)
        inner[key] = value
        return value
    }

    override val size: Int
        get() = inner.size

    override fun containsKey(key: K): Boolean = synchronized(this) {
        return inner.containsKey(key)
    }

    override fun containsValue(value: V): Boolean = synchronized(this) {
        return inner.containsValue(value)
    }

    override fun get(key: K): V? = synchronized(this) {
        return inner[key]
    }

    override fun isEmpty(): Boolean = synchronized(this) {
        return inner.isEmpty()
    }

    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() = synchronized(this) { inner.entries }

    override val keys: MutableSet<K>
        get() = synchronized(this) { inner.keys }

    override val values: MutableCollection<V>
        get() = synchronized(this) { inner.values }

    override fun clear() = synchronized(this) {
        inner.clear()
    }

    override fun put(key: K, value: V): V? = synchronized(this) {
        return inner.put(key, value)
    }

    override fun putAll(from: Map<out K, V>) = synchronized(this) {
        inner.putAll(from)
    }

    override fun remove(key: K): V? = synchronized(this) {
        return inner.remove(key)
    }

    override fun hashCode(): Int = synchronized(this) {
        return inner.hashCode()
    }

    override fun equals(other: Any?): Boolean = synchronized(this) {
        if (other !is Map<*, *>) return false
        return other == inner
    }
}
