package com.example.bottomnavigation_practise.core.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import com.alif.core.view.BaseVMFragment
import com.alif.core.vm.BaseViewModel

abstract class BaseNewsVMFragment<R : Any, VM : BaseViewModel<R, String>>(
    @LayoutRes layout: Int,
    clazz: Class<VM>
) : BaseVMFragment<R, String, VM>(layout, clazz)

abstract class BaseNewsNetworkVMFragment<R : Any, VM : BaseViewModel<R, String>>(
    @LayoutRes layout: Int,
    clazz: Class<VM>
) : BaseVMFragment<R, String, VM>(layout, clazz) {

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.exceptionLiveDate.observe {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
}