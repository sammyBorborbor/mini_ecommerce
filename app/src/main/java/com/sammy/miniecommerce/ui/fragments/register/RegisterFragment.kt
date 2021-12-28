package com.sammy.miniecommerce.ui.fragments.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sammy.miniecommerce.R
import com.sammy.miniecommerce.databinding.RegisterFragmentBinding
import com.sammy.miniecommerce.ui.activities.MainActivity

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel
    private var _binding: RegisterFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RegisterFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        binding.btSignup.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.sign_up_successful), Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        binding.tvSignin.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }

}