package com.doublesymmetry.collections

actual class ConcurrentHashMap<K, V> actual constructor(initialCapacity: Int): MutableMap<K, V> {
    private val inner = java.util.concurrent.ConcurrentHashMap<K, V>(initialCapacity)

    actual fun computeIfAbsent(key: K, block: (K) -> V): V {
        return inner.computeIfAbsent(key, block)
    }

    override val size: Int
        get() = inner.size

    override fun containsKey(key: K): Boolean {
        return inner.containsKey(key)
    }

    override fun containsValue(value: V): Boolean {
        return inner.containsValue(value)
    }

    override fun get(key: K): V? {
        return inner[key]
    }

    override fun isEmpty(): Boolean {
        return inner.isEmpty()
    }

    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() = inner.entries

    override val keys: MutableSet<K>
        get() = inner.keys

    override val values: MutableCollection<V>
        get() = inner.values

    override fun clear() {
        inner.clear()
    }

    override fun put(key: K, value: V): V? {
        return inner.put(key, value)
    }

    override fun putAll(from: Map<out K, V>) {
        inner.putAll(from)
    }

    override fun remove(key: K): V? {
        return inner.remove(key)
    }

    override fun hashCode(): Int {
        return inner.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Map<*, *>) return false
        return other == inner
    }
}
