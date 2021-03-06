package com.sammy.miniecommerce.ui.fragments.productdetail

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sammy.miniecommerce.BuildConfig
import com.sammy.miniecommerce.R
import com.sammy.miniecommerce.adapters.ImagesAdapter
import com.sammy.miniecommerce.databinding.ProductDetailFragmentBinding
import com.sammy.miniecommerce.models.Product
import com.sammy.miniecommerce.ui.fragments.ShareProductFragment
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ProductDetailFragment : Fragment() {

    companion object {
        fun newInstance() = ProductDetailFragment()
    }

    private lateinit var viewModel: ProductDetailViewModel
    private var _binding: ProductDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private var product: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProductDetailFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val args: ProductDetailFragmentArgs by navArgs()
        product = args.product

        (activity as AppCompatActivity).supportActionBar?.title = product?.name

        viewModel = ViewModelProvider(this).get(ProductDetailViewModel::class.java)

        product?.let {
            val sliderAdapter = ImagesAdapter(context, it.images)
            binding.imageSlider.setSliderAdapter(sliderAdapter)
            binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
            binding.imageSlider.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION)
            binding.imageSlider.isAutoCycle = true
            binding.imageSlider.startAutoCycle()

            binding.tvTitle.text = it.name
            binding.tvPrice.text = "GHS " + it.price
            binding.tvDesc.text = it.description

            binding.btnAddToCart.setOnClickListener { view ->
                val message = it.name + " added to your cart successfully"
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.product_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                val shareProductFragment = ShareProductFragment(product)
                shareProductFragment.show(
                    activity!!.supportFragmentManager,
                    shareProductFragment.tag
                )
                return true
            }
            R.id.action_cart -> {
                findNavController().navigate(ProductDetailFragmentDirections.actionProductDetailFragmentToCartFragment())
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }


}