package com.trycatch.til.ui.main

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.trycatch.til.R
import com.trycatch.til.databinding.FragmentMainBinding
import com.trycatch.til.ui.base.BaseFragment
import com.trycatch.til.vo.Post
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>(
    R.layout.fragment_main
) {
    override val viewModel by viewModels<MainViewModel>()

    private val postAdapter: PostAdapter by lazy {
        PostAdapter(viewModel.userProfileImage).apply {
            itemClickListener = object : PostAdapter.ItemClickListener {
                override fun onClickItem(post: Post) {
                    navController.navigate(R.id.action_mainFragment_to_mainBottomSheetDialog)
                }
            }
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        binding.postList.apply {
            addItemDecoration(PostDecoration())
        }
    }

    override fun initObserve(savedInstanceState: Bundle?) {
        super.initObserve(savedInstanceState)

        viewModel.isLogin.observe(this) { isLogin ->
            if (isLogin) {
                binding.postList.adapter = postAdapter
                viewModel.posts.observe(this) { posts ->
                    postAdapter.submitList(posts)
                }
            }
        }

        viewModel.writeEvent.observe(this) {
            it.getContentIfNotHandled()?.let {
                navController.navigate(R.id.action_mainFragment_to_postFragment)
            }
        }

    }

    override fun onReturnToPreviousScreen() {
        requireActivity().finish()
    }
}