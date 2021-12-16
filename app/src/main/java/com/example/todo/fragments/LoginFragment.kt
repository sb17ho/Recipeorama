package com.example.todo.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginFragment : Fragment() {
    companion object {
        private const val RC_SIGN_IN = 100
    }

    private lateinit var loginBind: FragmentLoginBinding

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var mAuth: FirebaseAuth

//    private val registerForResult =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//            // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//            if (it.resultCode == RESULT_OK) {
//                val task =
//                    GoogleSignIn.getSignedInAccountFromIntent(
//                        it.data
//                    )
//                val exception: Exception? = task.exception
//                if (task.isSuccessful) {
//                    try {
//                        // Google Sign In was successful, authenticate with Firebase
//                        val account = task.getResult(ApiException::class.java)!!
//                        firebaseAuthWithGoogle(account.idToken!!)
//                    } catch (e: ApiException) {
//                        // Google Sign In failed, update UI appropriately
//                        Log.w("SignInActivity", "Google sign in failed", e)
//                    }
//                } else {
//                    Log.w("SignInActivity", exception.toString())
//                }
//            }
//        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginBind = FragmentLoginBinding.inflate(layoutInflater, container, false)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        mAuth = FirebaseAuth.getInstance()

//        if (mAuth.currentUser != null) {
//            findNavController().navigate(LoginFragmentDirections.actionLoginFragment2ToListItems())
//        }

        loginBind.googleSignInButton.setOnClickListener {
            signIn()
        }

        return loginBind.root
    }

    private fun signIn() {
        startActivityForResult(googleSignInClient.signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            if (task.isSuccessful) {
                try {
                    firebaseAuthWithGoogle(task.getResult(ApiException::class.java)!!.idToken!!)
                } catch (e: ApiException) {
                    e.printStackTrace()
                }
            }
        }
    }


    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val navigateTo = LoginFragmentDirections.actionLoginFragment2ToRecipeListFragment2()
                    findNavController().navigate(navigateTo)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("SignInActivity", "signInWithCredential:failure", task.exception)
                }
            }
    }
}