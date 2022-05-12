package com.omolleapaza.peruapps.peruappstest.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.omolleapaza.peruapps.peruappstest.R
import org.koin.androidx.viewmodel.compat.ViewModelCompat.viewModel
import kotlin.reflect.KClass


abstract class BaseActivity<ViewDataBindingClass : ViewDataBinding, out ViewModel : BaseViewModel>(
    clazz: KClass<ViewModel>
) : AppCompatActivity() {


    lateinit var viewDataBinding: ViewDataBindingClass

    val myViewModel : ViewModel by viewModel(this ,clazz.java)



    abstract val getLayoutId : Int

    abstract val getBindingVariable : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId)
        viewDataBinding.setVariable(getBindingVariable, myViewModel)
        /**
         * use Fragment.viewLifecycleOwner for fragments
         */
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.executePendingBindings()

        onActivityReady()
    }

    open fun onActivityReady() {

    }




    protected fun showToast(message: Any?) {
        val textToShow = when (message) {
            is Int -> getString(message)
            is String -> message
            else -> {
                ""
            }
        }
        Toast.makeText(this, textToShow, Toast.LENGTH_LONG).show()
    }
    //endregion

}