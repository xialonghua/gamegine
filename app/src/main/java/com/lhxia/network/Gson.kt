package com.lhxia.network

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder

@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD)
annotation class Exclude

val responesGson = Gson()

val requestGson = GsonBuilder().apply {
    setExclusionStrategies(object : ExclusionStrategy {
        override fun shouldSkipClass(clazz: Class<*>?): Boolean {
            clazz?.apply {
                return !(
                        clazz.isPrimitive
                                || GsonSerializable::class.java.isAssignableFrom(clazz)
                                || String::class.java.isAssignableFrom(clazz)
                                || CharSequence::class.java.isAssignableFrom(clazz)
                                || Number::class.java.isAssignableFrom(clazz)
                                || Boolean::class.java.isAssignableFrom(clazz)
                                || Enum::class.java.isAssignableFrom(clazz)
                        )
            }
            clazz?.apply {
                return clazz.getAnnotation(Exclude::class.java) != null
            }
            return true
        }

        override fun shouldSkipField(f: FieldAttributes?): Boolean {
            f?.apply {
                return !(
                        declaringClass.isPrimitive
                                || GsonSerializable::class.java.isAssignableFrom(declaringClass)
                                || GsonSerializable::class.java.isAssignableFrom(declaringClass)
                                || String::class.java.isAssignableFrom(declaringClass)
                                || CharSequence::class.java.isAssignableFrom(declaringClass)
                                || Number::class.java.isAssignableFrom(declaringClass)
                                || Boolean::class.java.isAssignableFrom(declaringClass)
                                || Enum::class.java.isAssignableFrom(declaringClass)
                        )
            }
            f?.apply {
                return getAnnotation(Exclude::class.java) != null
            }
            return true
        }
    })
}.create()