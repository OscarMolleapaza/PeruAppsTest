package com.omolleapaza.peruapps.peruappstest.ui.features.post_detail

import android.annotation.SuppressLint
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.navigation.fragment.navArgs
import com.omolleapaza.peruapps.data.utils.ConnectionUtils
import com.omolleapaza.peruapps.peruappstest.BR
import com.omolleapaza.peruapps.peruappstest.R
import com.omolleapaza.peruapps.peruappstest.databinding.FragmentPostDetailBinding
import com.omolleapaza.peruapps.peruappstest.ui.base.BaseFragmentWithViewModel
import org.koin.android.ext.android.inject


@SuppressLint("SetJavaScriptEnabled")
class PostDetailFragment :
    BaseFragmentWithViewModel<FragmentPostDetailBinding, PostDetailViewModel>(
        PostDetailViewModel::class
    ) {

    private val conectionUtils: ConnectionUtils by inject()
    private val args: PostDetailFragmentArgs by navArgs()

    override val getLayoutId: Int
        get() = R.layout.fragment_post_detail
    override val getBindingVariable: Int
        get() = BR.detailVM


    override fun onFragmentViewReady(view: View) {
        super.onFragmentViewReady(view)

        myViewModel.reloadSwipe = {
            myViewModel.state.value = PostDetailViewState.Refreshing
            configWebView()
        }
        configWebView()


    }

    private fun configWebView() {
        if (conectionUtils.isNetworkAvailable()) {
            with(viewDataBinding) {
                webView.webChromeClient = MyWebChromeClient()
                webView.settings.loadWithOverviewMode = true
                webView.settings.setSupportZoom(true)
                webView.settings.javaScriptEnabled = true
                myViewModel.setupArguments(args.url)
            }
        } else {
            myViewModel.state.value = PostDetailViewState.Error
        }

    }



    inner class MyWebChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            myViewModel.state.value = PostDetailViewState.Loading
            if (newProgress > 70) {
                myViewModel.state.value = PostDetailViewState.Loaded
            }
        }
    }


}