package com.sammy.miniecommerce.ui.fragments.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sammy.miniecommerce.R
import com.sammy.miniecommerce.adapters.CheckoutItemsAdapter
import com.sammy.miniecommerce.adapters.NewArrivalAdapter
import com.sammy.miniecommerce.databinding.CheckoutFragmentBinding
import com.sammy.miniecommerce.ui.activities.MainActivity
import com.sammy.miniecommerce.ui.fragments.home.HomeFragmentDirections
import com.sammy.miniecommerce.ui.fragments.home.HomeViewModel
import kotlinx.coroutines.launch

class CheckoutFragment : Fragment() {

    companion object {
        fun newInstance() = CheckoutFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private var _binding: CheckoutFragmentBinding? = null
    private val binding get() = _binding!!
    private var paymentType = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CheckoutFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.lytCard.visibility = View.GONE
        paymentType = 1

        radioButtonListener()
        loadCheckoutItems()

        binding.imgArrow.setOnClickListener{
            toggleArrow(it)
        }

        binding.btnPlaceOrder.setOnClickListener {
            findNavController().navigate(CheckoutFragmentDirections.actionCheckoutFragmentToOrderSuccessFragment())
        }

    }

    private fun toggleCardUI() {
        if (paymentType == 1) {
            binding.lytCard.visibility = View.GONE
            binding.btnPlaceOrder.setText(R.string.place_order)
        } else {
            binding.lytCard.visibility = View.VISIBLE
            binding.btnPlaceOrder.setText(R.string.checkout)
        }
    }

    private fun radioButtonListener() {
        binding.radioCash.isChecked = true
        binding.radioCash.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.radioOnline.isChecked = false
                binding.radioCash.isChecked = true
                paymentType = 1
                toggleCardUI()
            }
        }
        binding.radioOnline.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.radioCash.isChecked = false
                binding.radioOnline.isChecked = true
                paymentType = 2
                toggleCardUI()
            }
        }
    }

    private fun loadCheckoutItems() {

        lifecycleScope.launch {
            viewModel.getProducts().observe(viewLifecycleOwner, {   list ->
                val checkoutItemsAdapter = CheckoutItemsAdapter(requireContext(), list.take(3))
                binding.rvOrdersItems.adapter = checkoutItemsAdapter

            })
        }
    }

    private fun toggleArrow(view: View): Boolean {
        return if (view.rotation == 0f) {
            view.animate().setDuration(200).rotation(180f)
            binding.rvOrdersItems.visibility = View.VISIBLE
            true
        } else {
            view.animate().setDuration(200).rotation(0f)
            binding.rvOrdersItems.visibility = View.GONE
            false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}