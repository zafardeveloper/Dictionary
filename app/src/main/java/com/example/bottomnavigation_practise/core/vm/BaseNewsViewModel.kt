package com.alif.newsapplication.core.vm

import com.alif.core.vm.BaseViewModel

abstract class BaseNewsViewModel<T : Any> : BaseViewModel<T, String>(BaseErrorMapper())
