package com.trycatch.til.ui.post

import androidx.fragment.app.viewModels
import com.trycatch.til.R
import com.trycatch.til.databinding.FragmentPostBinding
import com.trycatch.til.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : BaseFragment<PostViewModel, FragmentPostBinding>(
    R.layout.fragment_post
) {
    override val viewModel by viewModels<PostViewModel>()

    override fun initView() {
        super.initView()
    }

    override fun initObserve() {
        super.initObserve()

        viewModel.writeEvent.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                navController.popBackStack()
            }
        }
    }
}