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

    override fun initView() {
        super.initView()
    }

    override fun initObserve() {
        super.initObserve()

        viewModel.isLogin.observe(this) {
            if (!it)
                navController.navigate(R.id.loginFragment)
        }
    }

    override fun onReturnToPreviousScreen() {
        requireActivity().finish()
    }
}