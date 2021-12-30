package com.sammy.miniecommerce.ui.fragments.orders

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sammy.miniecommerce.adapters.CartAdapter
import com.sammy.miniecommerce.adapters.OrdersAdapter
import com.sammy.miniecommerce.databinding.OrdersFragmentBinding
import com.sammy.miniecommerce.ui.activities.MainActivity
import com.sammy.miniecommerce.ui.fragments.home.HomeViewModel
import com.sammy.miniecommerce.utils.LineItemDecoration
import com.sammy.miniecommerce.utils.SpacingItemDecoration
import kotlinx.coroutines.launch

class OrdersFragment : Fragment() {

    companion object {
        fun newInstance() = OrdersFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private var _binding: OrdersFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var ordersAdapter: OrdersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = OrdersFragmentBinding.inflate(layoutInflater, container, false)
        setUpRecyclerView()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        lifecycleScope.launch {
            viewModel.getOrders().observe(viewLifecycleOwner, { list ->
                ordersAdapter = OrdersAdapter(requireContext(), list)
                binding.recyclerView.adapter = ordersAdapter

                ordersAdapter.setOnItemClickListener { _, order, _ ->

                }

                ordersAdapter.setOnTrackListener { _, order, _ ->
                    val message = "Kindly update me with the status of my order" + order.orderNumber + " placed on " +
                            order.orderDate

                    onWhatsAppShareClicked(requireContext(), "233547576916", message)
                }
            })
        }

    }

    private fun setUpRecyclerView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.addItemDecoration(SpacingItemDecoration(1, 20, false))
        binding.recyclerView.addItemDecoration(LineItemDecoration(requireContext(), 1))
        binding.recyclerView.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)!!.showBottomNavigationView()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun onWhatsAppShareClicked(context: Context, mobileNumber: String, message: String) {

        try {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, message)
                putExtra("jid", "${mobileNumber}@s.whatsapp.net")
                type = "text/plain"
                setPackage("com.whatsapp")
            }
            startActivity(sendIntent)
        }catch (e: Exception){
            e.printStackTrace()
            val appPackageName = "com.whatsapp"
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
            } catch (e :ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
            }
        }

    }


}