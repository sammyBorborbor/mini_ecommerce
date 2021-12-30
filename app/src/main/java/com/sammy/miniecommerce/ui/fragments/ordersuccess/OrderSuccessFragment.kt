package com.sammy.miniecommerce.ui.fragments.ordersuccess

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sammy.miniecommerce.databinding.OrderSuccessFragmentBinding

class OrderSuccessFragment : Fragment() {

    companion object {
        fun newInstance() = OrderSuccessFragment()
    }

    private var _binding: OrderSuccessFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = OrderSuccessFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnContinue.setOnClickListener {
            findNavController().navigate(OrderSuccessFragmentDirections.actionOrderSuccessFragmentToHomeFragment())
        }

        binding.btnTrack.setOnClickListener {
            val message =
                "Kindly update me with the status of my order #0012345678  placed on 12 Dec 2021"

            onWhatsAppShareClicked("233547576916", message)
        }

    }

    private fun onWhatsAppShareClicked(mobileNumber: String, message: String) {

        try {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, message)
                putExtra("jid", "${mobileNumber}@s.whatsapp.net")
                type = "text/plain"
                setPackage("com.whatsapp")
            }
            startActivity(sendIntent)
        } catch (e: Exception) {
            e.printStackTrace()
            val appPackageName = "com.whatsapp"
            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$appPackageName")
                    )
                )
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                    )
                )
            }
        }

    }


}