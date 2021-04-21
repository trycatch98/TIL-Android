package com.trycatch.til.ui.main

import androidx.fragment.app.viewModels
import com.trycatch.til.R
import com.trycatch.til.databinding.FragmentMainBinding
import com.trycatch.til.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>(
    R.layout.fragment_main
) {
    override val viewModel by viewModels<MainViewModel>()

    private val postAdapter: PostAdapter = PostAdapter()

    override fun initView() {
        super.initView()
        binding.postList.adapter = postAdapter
    }

    override fun initObserve() {
        super.initObserve()

        viewModel.isLogin.observe(this) {
            if (!it)
                navController.navigate(R.id.loginFragment)
        }

        viewModel.posts.observe(this) {
            postAdapter.submitList(it)
        }
    }

    override fun onReturnToPreviousScreen() {
        requireActivity().finish()
    }
}