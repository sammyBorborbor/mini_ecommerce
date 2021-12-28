package com.sammy.miniecommerce.ui.fragments.newarrivals

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sammy.miniecommerce.adapters.NewArrivalAdapter
import com.sammy.miniecommerce.databinding.NewArrivalsFragmentBinding
import com.sammy.miniecommerce.ui.activities.MainActivity
import com.sammy.miniecommerce.ui.fragments.home.HomeFragmentDirections
import kotlinx.coroutines.launch

class NewArrivalsFragment : Fragment() {

    companion object {
        fun newInstance() = NewArrivalsFragment()
    }

    private lateinit var viewModel: NewArrivalsViewModel
    private var _binding: NewArrivalsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var newArrivalAdapter: NewArrivalAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NewArrivalsFragmentBinding.inflate(layoutInflater, container, false)
        setUpRecyclerView()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewArrivalsViewModel::class.java)
        lifecycleScope.launch {
            viewModel.getNewArrivals().observe(viewLifecycleOwner, {   list ->
                newArrivalAdapter = NewArrivalAdapter(requireContext(), list)
                binding.recyclerView.adapter = newArrivalAdapter

                newArrivalAdapter.setOnItemClickListener { view, product, position ->
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
        binding.recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false)
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