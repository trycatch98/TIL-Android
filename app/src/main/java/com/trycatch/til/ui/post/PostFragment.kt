package com.trycatch.til.ui.post

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.trycatch.til.R
import com.trycatch.til.databinding.FragmentPostBinding
import com.trycatch.til.ui.base.BaseFragment
import com.trycatch.til.ui.login.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : BaseFragment<PostViewModel, FragmentPostBinding>(
    R.layout.fragment_post
) {
    override val viewModel by viewModels<PostViewModel>()

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
    }

    override fun initObserve(savedInstanceState: Bundle?) {
        super.initObserve(savedInstanceState)

        viewModel.isLogin.observe(this) {
            if (!it)
                navController.navigate(R.id.loginFragment)
        }

        viewModel.writeEvent.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                navController.popBackStack()
            }
        }

        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<Boolean>(LoginFragment.LOGIN_SUCCESSFUL)?.observe(this) {
            if (!it) {
                navController.popBackStack()
            }
        }
    }
}