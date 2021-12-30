package com.sammy.miniecommerce.ui.fragments.category

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sammy.miniecommerce.R
import com.sammy.miniecommerce.adapters.CategoryAdapter
import com.sammy.miniecommerce.databinding.CategoryFragmentBinding
import com.sammy.miniecommerce.ui.activities.MainActivity
import com.sammy.miniecommerce.ui.fragments.home.HomeViewModel
import com.sammy.miniecommerce.ui.fragments.products.ProductsFragmentDirections
import com.sammy.miniecommerce.utils.SpacingItemDecoration
import kotlinx.coroutines.launch

class CategoryFragment : Fragment() {

    companion object {
        fun newInstance() = CategoryFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private var _binding: CategoryFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CategoryFragmentBinding.inflate(layoutInflater, container, false)
        setUpRecyclerView()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        lifecycleScope.launch {
            viewModel.getCategories().observe(viewLifecycleOwner, { list ->
                categoryAdapter = CategoryAdapter(requireContext(), list)
                binding.recyclerView.adapter = categoryAdapter

                categoryAdapter.setOnItemClickListener { view, productCategory, position ->
                    findNavController().navigate(
                        CategoryFragmentDirections.actionCategoryFragmentToProductsFragment(
                            productCategory.name
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
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.addItemDecoration(
            SpacingItemDecoration(
                3,
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_cart -> {
                findNavController().navigate(CategoryFragmentDirections.actionCategoryFragmentToCartFragment())
                if (requireActivity() is MainActivity) {
                    (activity as MainActivity?)!!.hideBottomNavigationView()
                }
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

}