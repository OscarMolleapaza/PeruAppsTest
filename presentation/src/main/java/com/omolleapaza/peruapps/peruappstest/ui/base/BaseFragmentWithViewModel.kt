package com.omolleapaza.peruapps.peruappstest.ui.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.omolleapaza.peruapps.peruappstest.R
import kotlinx.coroutines.CoroutineExceptionHandler
import org.koin.androidx.viewmodel.compat.ViewModelCompat.viewModel
import kotlin.reflect.KClass


abstract class BaseFragmentWithViewModel<ViewDataBindingClass : ViewDataBinding, out ViewModelType : BaseViewModel>(
    clazz: KClass<ViewModelType>
) : BaseFragment() {

    lateinit var viewDataBinding: ViewDataBindingClass
    val myViewModel: ViewModelType by viewModel(this, clazz.java)

    /**
     * This function associate the xml file to the class.
     * In order to pass it to onCreateView and bind it.
     * @return layout id
     */
    abstract val getLayoutId: Int

    /**
     * Return the binding variable of the XML
     * associated to this class.
     * @return binding variable ID
     */
    abstract val getBindingVariable: Int

    val exceptionHandler = CoroutineExceptionHandler { _, t ->
        t.printStackTrace()
        showToast(getString(R.string.error_failure_none))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Binding the viewModel
        setHasOptionsMenu(true)
        onSetupInitData()
        if (savedInstanceState == null) {
            onSetUpExecuteInitFunctions()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId, container, false)
        val rootView = viewDataBinding.root
        viewDataBinding.setVariable(getBindingVariable, myViewModel)
        onSetSetupViews()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        onSetupObserves()
        onFragmentViewReady(view)
        onSetupListeners()
        viewDataBinding.executePendingBindings()
    }

    open fun onSetSetupViews() {

    }


    /**
     * We can override this function instead of [onViewCreated].
     * also this will be always listening to forceLogOut liveData.
     */
    open fun onFragmentViewReady(view: View) {

    }

    open fun onSetupInitData() {

    }

    /**
     * override this for set up navigator
     */
    open fun onSetUpExecuteInitFunctions() {

    }

    /**
     * override this for set up Live data observer
     */
    open fun onSetupObserves() {

    }

    /**
     * override this for set up Listeners
     */
    open fun onSetupListeners() {

    }

    /**
     * this function for navigate between fragments
     */
    protected fun navigateTo(action: NavDirections) {
        findNavController().navigate(action)
    }

    protected fun goBack() {
        findNavController().popBackStack()
    }

    protected fun goBackTo(destination: Int, inclusive: Boolean) {
        findNavController().popBackStack(destination, inclusive)
    }
}