package com.alif.core.common

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

inline fun <reified T> type(): Type = object : TypeToken<T>() {}.type