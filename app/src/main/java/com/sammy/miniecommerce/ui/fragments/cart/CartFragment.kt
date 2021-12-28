package com.sammy.miniecommerce.ui.fragments.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sammy.miniecommerce.R
import com.sammy.miniecommerce.adapters.CartAdapter
import com.sammy.miniecommerce.adapters.ProductAdapter
import com.sammy.miniecommerce.databinding.CartFragmentBinding
import com.sammy.miniecommerce.ui.fragments.home.HomeViewModel
import com.sammy.miniecommerce.ui.fragments.products.ProductsFragmentDirections
import com.sammy.miniecommerce.utils.LineItemDecoration
import com.sammy.miniecommerce.utils.SpacingItemDecoration
import kotlinx.coroutines.launch

class CartFragment : Fragment() {

    companion object {
        fun newInstance() = CartFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private var _binding: CartFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.cart)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CartFragmentBinding.inflate(layoutInflater, container, false)
        setUpRecyclerView()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        lifecycleScope.launch {
            viewModel.getProducts().observe(viewLifecycleOwner, { list ->
                cartAdapter = CartAdapter(requireContext(), list)
                binding.recyclerView.adapter = cartAdapter

                cartAdapter.setOnItemClickListener { view, product, position ->

                }
            })
        }
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.addItemDecoration(LineItemDecoration(requireContext(), 1))
        binding.recyclerView.setHasFixedSize(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}