package com.sammy.miniecommerce.ui.fragments.topcategories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sammy.miniecommerce.adapters.CategoryAdapter
import com.sammy.miniecommerce.databinding.TopcategoriesFragmentBinding
import com.sammy.miniecommerce.ui.activities.MainActivity
import com.sammy.miniecommerce.ui.fragments.home.HomeFragmentDirections
import com.sammy.miniecommerce.ui.fragments.home.HomeViewModel
import com.sammy.miniecommerce.utils.SpacingItemDecoration
import kotlinx.coroutines.launch

class TopCategoriesFragment : Fragment() {

    companion object {
        fun newInstance() = TopCategoriesFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private var _binding: TopcategoriesFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TopcategoriesFragmentBinding.inflate(layoutInflater, container, false)
        setUpRecyclerView()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        lifecycleScope.launch {
            viewModel.getCategories().observe(viewLifecycleOwner, { list ->
                categoryAdapter = CategoryAdapter(requireContext(), list.take(3))
                binding.recyclerView.adapter = categoryAdapter

                categoryAdapter.setOnItemClickListener { _, productCategory, _ ->
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToProductsFragment(
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
                2,
                10,
                false
            )
        )
        binding.recyclerView.setHasFixedSize(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)!!.showBottomNavigationView()
        }
    }
}