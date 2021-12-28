package com.sammy.miniecommerce.ui.fragments.products

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.sammy.miniecommerce.R
import com.sammy.miniecommerce.adapters.ProductAdapter
import com.sammy.miniecommerce.databinding.ProductsFragmentBinding
import com.sammy.miniecommerce.ui.fragments.home.HomeViewModel
import com.sammy.miniecommerce.ui.fragments.productdetail.ProductDetailFragmentDirections
import com.sammy.miniecommerce.utils.SpacingItemDecoration
import kotlinx.coroutines.launch

class ProductsFragment : Fragment() {

    companion object {
        fun newInstance() = ProductsFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private var _binding: ProductsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProductsFragmentBinding.inflate(layoutInflater, container, false)

        setUpRecyclerView()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val args: ProductsFragmentArgs by navArgs()
        val title = args.category
        (activity as AppCompatActivity).supportActionBar?.title = title
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        lifecycleScope.launch {
            viewModel.getProducts().observe(viewLifecycleOwner, { list ->
                productAdapter = ProductAdapter(requireContext(), list)
                binding.recyclerView.adapter = productAdapter

                productAdapter.setOnItemClickListener { view, product, position ->
                    findNavController().navigate(
                        ProductsFragmentDirections.actionProductsFragmentToProductDetailFragment(
                            product
                        )
                    )
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_cart -> {
                findNavController().navigate(ProductsFragmentDirections.actionProductsFragmentToCartFragment())
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}