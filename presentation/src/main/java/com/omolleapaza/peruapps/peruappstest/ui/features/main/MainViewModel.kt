package com.omolleapaza.peruapps.peruappstest.ui.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.omolleapaza.peruapps.peruappstest.enums.ConnectionStatusEnum
import com.omolleapaza.peruapps.peruappstest.ui.base.BaseViewModel



class MainViewModel: BaseViewModel() {
    private val _showErrorCard = MutableLiveData(false)
    val showErrorCard: LiveData<Boolean> = _showErrorCard


    private val _connectionStatus = MutableLiveData<ConnectionStatusEnum>()
    val connectionStatus: LiveData<ConnectionStatusEnum> = _connectionStatus

    fun setConnectionStatus(connectionStatus: Boolean) {
        _showErrorCard.value = false
        if (connectionStatus) {
            _connectionStatus.value = ConnectionStatusEnum.NONE
        } else {
            _connectionStatus.value = ConnectionStatusEnum.FAILED
        }
    }
}