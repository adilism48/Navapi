package com.example.tourguideapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tourguideapp.data.model.User
import com.example.tourguideapp.databinding.FragmentRegistrationBinding
import com.example.tourguideapp.viewmodel.AuthViewModel

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.bSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val name = binding.etName.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                authViewModel.signUp(email, password).observe(viewLifecycleOwner) {
                    if (it.isSuccess) {
                        val currentUserEmail = authViewModel.currentUserEmail()
                        authViewModel.addUser(User(name, currentUserEmail!!, "other"))
                        Toast.makeText(
                            context,
                            "Account created",
                            Toast.LENGTH_SHORT,
                        ).show()
                        this.findNavController().popBackStack()
                    } else if (it.isFailure) {
                        Toast.makeText(
                            context,
                            "Already been registered",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
            }
        }

        binding.tvSignIn.setOnClickListener {
            this.findNavController().popBackStack()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}