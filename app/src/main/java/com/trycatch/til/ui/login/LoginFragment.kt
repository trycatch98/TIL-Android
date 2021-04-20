package com.trycatch.til.ui.login

import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import com.trycatch.til.BuildConfig
import com.trycatch.til.R
import com.trycatch.til.databinding.FragmentMainBinding
import com.trycatch.til.ui.base.BaseFragment
import com.trycatch.til.vo.AuthStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel, FragmentMainBinding>(
    R.layout.fragment_login
) {
    override val viewModel by viewModels<LoginViewModel>()

    override fun initView() {
        super.initView()
    }

    override fun initObserve() {
        super.initObserve()

        viewModel.loginEvent.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                when (it) {
                    AuthStatus.PendingAuth -> TODO()
                    AuthStatus.SignIn -> {
                        val provider = OAuthProvider.newBuilder("github.com")
                        provider.addCustomParameter("client_id", BuildConfig.GITHUB_CLIENT_ID)
                        FirebaseAuth.getInstance()
                            .startActivityForSignInWithProvider(activity, provider.build())
                            .addOnSuccessListener {
                                navController.navigate(R.id.action_loginFragment_to_mainFragment)
                            }
                            .addOnFailureListener {
                            }
                    }
                    AuthStatus.FailedAuth -> TODO()
                }
            }
        }
    }
}