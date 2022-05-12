package com.omolleapaza.peruapps.peruappstest.ui.base


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.omolleapaza.peruapps.domain.utils.Failure
import com.omolleapaza.peruapps.peruappstest.R
import com.omolleapaza.peruapps.peruappstest.utils_class.LiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext


abstract class BaseViewModel : ViewModel() {


    private val mapperExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        setErrorFromMapperPresentation(throwable.message)
    }

    val presentationMapperContext: CoroutineContext = Dispatchers.IO + mapperExceptionHandler

    //New Navigator
    val navigateToNextView = LiveEvent<Any?>()
    val navigateToNextViewWhitDirection = LiveEvent<NavDirections>()

    // Shows or hide progress loading bar if the have it.
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    //Shows, hide, error message view.
    val showErrorCause = LiveEvent<Boolean>()

    // The resource default value of the error or any error(Exception, server side, etc).
    val errorCause = LiveEvent<Any>()

    // Fired when a failure is of type AppNotAvailable
    val forceLogOut = LiveEvent<Boolean>()

    protected fun logError(errorMessage: Any?) {
        Log.e(this.javaClass.simpleName, "ERROR: $errorMessage")
    }

    protected fun logDebug(debugMessage: Any?) {
        Log.e(this.javaClass.simpleName, "DEBUG: $debugMessage")
    }

    protected fun logInfo(infoMessage: Any?) {
        Log.i(this.javaClass.simpleName, "INFO: $infoMessage")
    }



    protected fun showErrorCause(show: Boolean?) {
        showErrorCause.value = show?:false
    }

    protected fun showForceLogOutDialog() {
        forceLogOut.value = true
    }


    protected fun setNavigateTo(any: Any?) {
        navigateToNextView.value = any
    }

    protected fun setNavigateWhitDirection(action: NavDirections) {
        navigateToNextViewWhitDirection.value = action
    }

    protected fun handleUseCaseFailureFromBase(failure: Failure) {
        logError("handleUseCaseFailureFromBase: $failure")
        when (failure) {

            is Failure.AppNotAvailable -> showForceLogOutDialog()
            is Failure.None -> setError(R.string.error_failure_none)
            is Failure.NetworkConnectionLostSuddenly -> setError(R.string.error_failure_network_connection_lost_suddenly)
            is Failure.NoNetworkDetected -> setError(R.string.error_failure_no_network_detected)
            is Failure.SSLError -> setError(R.string.error_failure_ssl)
            is Failure.TimeOut -> setError(R.string.error_failure_time_out)
            is Failure.ServerBodyError -> setError(failure.message)
            is Failure.DataToDomainMapperFailure -> setError(R.string.error_general)
            is Failure.DomainToPresentationMapperFailure -> setError(
                failure.mapperException ?: R.string.error_general
            )
            is Failure.ServiceUncaughtFailure -> setError(R.string.error_failure_uncaught)
        }

    }

    /**
     * This function should to the following on LifeCycleOwner
     * 1.- Stop loading & refreshing.
     * 2.- Show "Error View"
     */
    protected fun setError(cause: Any?) {
        if (cause is String) {
            logError(cause)
        }
        errorCause.value = cause?:"Null"
        showErrorCause.value = true
    }

    private fun setErrorFromMapperPresentation(exceptionMessage: String?) {
        //We are using post value because the mappers are performed on a background "thread" (Dispatchers.IO)
        _isLoading.postValue(false)
        errorCause.postValue("Mapper presentation exception: $exceptionMessage")
        logError(exceptionMessage)
    }

}