package com.trycatch.til.ui.login

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
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
    companion object {
        const val LOGIN_SUCCESSFUL: String = "LOGIN_SUCCESSFUL"
    }

    override val viewModel by viewModels<LoginViewModel>()
    private lateinit var savedStateHandle: SavedStateHandle

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
    }

    override fun initObserve(savedInstanceState: Bundle?) {
        super.initObserve(savedInstanceState)

        savedStateHandle = navController.previousBackStackEntry!!.savedStateHandle

        viewModel.loginEvent.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                val firebaseAuth = FirebaseAuth.getInstance()
                val pendingResultTask: Task<AuthResult>? = firebaseAuth.pendingAuthResult

                if (pendingResultTask != null) {
                    pendingResultTask
                        .addOnSuccessListener {
                            savedStateHandle.set(LOGIN_SUCCESSFUL, true)
                            navController.popBackStack()
                        }.addOnFailureListener {
                            TODO()
                        }
                } else {
                    val provider = OAuthProvider.newBuilder("github.com")
                    provider.addCustomParameter("client_id", BuildConfig.GITHUB_CLIENT_ID)
                    FirebaseAuth.getInstance()
                        .startActivityForSignInWithProvider(requireActivity(), provider.build())
                        .addOnSuccessListener {
                            savedStateHandle.set(LOGIN_SUCCESSFUL, true)
                            navController.popBackStack()
                        }
                        .addOnFailureListener {
                            TODO()
                        }
                }
            }
        }
    }

    override fun onBackPressed() {
        savedStateHandle.set(LOGIN_SUCCESSFUL, false)
        super.onBackPressed()
    }
}