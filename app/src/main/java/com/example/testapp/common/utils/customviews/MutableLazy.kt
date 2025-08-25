package com.example.testapp.common.utils.customviews

/**
 * @author Andika Bratadirja
 * @date 24/08/2025
 */
class MutableLazy<T>(private val initializer: () -> T) : Lazy<T> {
    private var cached: T? = null

    override val value: T
        get() {
            if (cached == null) {
                cached = initializer()
            }
            @Suppress("UNCHECKED_CAST")
            return cached as T
        }

    override fun isInitialized(): Boolean = cached != null

    fun reset() {
        cached = null
    }

    companion object {
        fun <T> resettableLazy(value: () -> T) = MutableLazy(value)
    }
}