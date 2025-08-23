package com.example.testapp.core.base.mapper

/**
 * @author Andika Bratadirja
 * @date 23/08/2025
 */
abstract class BaseMapper<in T, out R> {
    abstract fun map(value: T): R
}
