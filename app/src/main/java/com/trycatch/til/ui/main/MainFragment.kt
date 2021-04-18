package com.trycatch.til.ui.main

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.trycatch.til.R
import com.trycatch.til.databinding.FragmentMainBinding
import com.trycatch.til.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

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
    }
}