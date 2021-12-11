package com.example.todo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todo.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var loginBind: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginBind = FragmentLoginBinding.inflate(layoutInflater, container, false)

        return loginBind.root
    }

}