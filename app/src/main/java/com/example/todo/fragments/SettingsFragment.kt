package com.example.todo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todo.MainActivity
import com.example.todo.R
import com.example.todo.databinding.FragmentSettingsBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class SettingsFragment : Fragment() {
    private lateinit var settingsFragmentBind: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        settingsFragmentBind = FragmentSettingsBinding.inflate(layoutInflater, container, false)

        settingsFragmentBind.apply {
            logoutButton.setOnClickListener {

                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build()

                val googleAuth = GoogleSignIn.getClient(requireActivity(), gso)

                googleAuth.signOut().addOnCompleteListener {
                    FirebaseAuth.getInstance().signOut()
                    val action = SettingsFragmentDirections.actionSettingsFragmentToLoginFragment2()
                    findNavController().navigate(action)
                }

            }
        }

        return settingsFragmentBind.root
    }

    //To override the action bar title
    override fun onResume() {
        super.onResume()
        (activity as MainActivity)
            .setActionBarTitle("Settings")
    }
}