package com.alif.newsapplication.core.view

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import com.alif.core.view.BaseVMActivity
import com.alif.core.vm.BaseViewModel

abstract class BaseNewsVMActivity<R : Any, VM : BaseViewModel<R, String>>(
    @LayoutRes layout: Int,
    clazz: Class<VM>
) : BaseVMActivity<R, String, VM>(layout, clazz)

abstract class BaseNewsNetworkVMActivity<R : Any, VM : BaseViewModel<R, String>>(
    @LayoutRes layout: Int,
    clazz: Class<VM>
) : BaseVMActivity<R, String, VM>(layout, clazz) {

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.exceptionLiveDate.observe {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}