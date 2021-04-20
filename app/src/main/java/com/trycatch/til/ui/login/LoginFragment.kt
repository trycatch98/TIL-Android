package com.trycatch.til.ui.login

import androidx.fragment.app.viewModels
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import com.trycatch.til.BuildConfig
import com.trycatch.til.R
import com.trycatch.til.databinding.FragmentMainBinding
import com.trycatch.til.ui.base.BaseFragment
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
                val firebaseAuth = FirebaseAuth.getInstance()
                val pendingResultTask: Task<AuthResult>? = firebaseAuth.pendingAuthResult

                if (pendingResultTask != null) {
                    pendingResultTask
                        .addOnSuccessListener {
                            navController.navigate(R.id.action_loginFragment_to_mainFragment)
                        }.addOnFailureListener {
                            TODO()
                        }
                } else {
                    val provider = OAuthProvider.newBuilder("github.com")
                    provider.addCustomParameter("client_id", BuildConfig.GITHUB_CLIENT_ID)
                    FirebaseAuth.getInstance()
                        .startActivityForSignInWithProvider(requireActivity(), provider.build())
                        .addOnSuccessListener {
                            navController.navigate(R.id.action_loginFragment_to_mainFragment)
                        }
                        .addOnFailureListener {
                            TODO()
                        }
                }
            }
        }
    }

    override fun onReturnToPreviousScreen() {
        requireActivity().finish()
    }
}