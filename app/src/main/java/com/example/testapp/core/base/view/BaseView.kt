package com.example.testapp.core.base.view

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
interface BaseView : LifecycleOwner {
    fun <T> observeData(data: LiveData<T>, observer: Observer<T>) {
        data.observe(this, observer)
    }

    fun <T> observeData(data: LiveData<T>, onChanged: (T?) -> Unit) {
        observeData(data, Observer { onChanged(it) })
    }

    fun <T> removeObserver(data: LiveData<T>) {
        data.removeObservers(this)
    }
}