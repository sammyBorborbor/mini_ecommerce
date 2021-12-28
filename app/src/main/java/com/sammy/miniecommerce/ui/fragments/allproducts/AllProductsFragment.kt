package com.sammy.miniecommerce.ui.fragments.allproducts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sammy.miniecommerce.adapters.ProductAdapter
import com.sammy.miniecommerce.databinding.AllProductsFragmentBinding
import com.sammy.miniecommerce.ui.activities.MainActivity
import com.sammy.miniecommerce.ui.fragments.home.HomeFragmentDirections
import com.sammy.miniecommerce.ui.fragments.home.HomeViewModel
import com.sammy.miniecommerce.ui.fragments.products.ProductsFragmentDirections
import com.sammy.miniecommerce.utils.SpacingItemDecoration
import kotlinx.coroutines.launch

class AllProductsFragment : Fragment() {

    companion object {
        fun newInstance() = AllProductsFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private var _binding: AllProductsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AllProductsFragmentBinding.inflate(layoutInflater, container, false)
        setUpRecyclerView()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        lifecycleScope.launch {
            viewModel.getProducts().observe(viewLifecycleOwner, { list ->
                productAdapter = ProductAdapter(requireContext(), list)
                binding.recyclerView.adapter = productAdapter

                productAdapter.setOnItemClickListener { view, product, position ->
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(
                            product
                        )
                    )
                    if (requireActivity() is MainActivity) {
                        (activity as MainActivity?)!!.hideBottomNavigationView()
                    }
                }
            })
        }

    }

    private fun setUpRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.addItemDecoration(
            SpacingItemDecoration(
                2,
                10,
                false
            )
        )
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


}