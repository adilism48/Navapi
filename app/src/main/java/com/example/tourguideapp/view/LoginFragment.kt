package com.example.tourguideapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tourguideapp.R
import com.example.tourguideapp.databinding.FragmentLoginBinding
import com.example.tourguideapp.viewmodel.AuthViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var authViewModel: AuthViewModel

    override fun onStart() {
        (activity as AppCompatActivity).supportActionBar!!.hide()
        (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottom_nav_view).isVisible = false
        super.onStart()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root



        binding.bSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                authViewModel.signIn(email, password).observe(viewLifecycleOwner) {
                    if (it.isSuccess) {
                        Toast.makeText(
                            context,
                            "Signed In.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        checkAuthState()
                    } else if (it.isFailure) {
                        Toast.makeText(
                            context,
                            "Incorrect Email or Password.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
            }
        }

        binding.tvSignUp.setOnClickListener {
            this.findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        checkAuthState()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkAuthState() {
        if (authViewModel.currentUser() != null) {
            this.findNavController().navigate(R.id.action_loginFragment_to_tourFragment)
        }
    }
}