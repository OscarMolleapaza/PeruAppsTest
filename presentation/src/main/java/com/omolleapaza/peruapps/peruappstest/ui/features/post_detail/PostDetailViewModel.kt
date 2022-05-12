package com.omolleapaza.peruapps.peruappstest.ui.features.post_detail

import androidx.lifecycle.MutableLiveData
import com.omolleapaza.peruapps.peruappstest.ui.base.BaseViewModel


class PostDetailViewModel:BaseViewModel(){

    val state = MutableLiveData<PostDetailViewState>(PostDetailViewState.Loading)


    val urlArgs = MutableLiveData<String>()

    fun setupArguments(url:String){
        urlArgs.value = url
    }

    lateinit var reloadSwipe:()->Unit
}