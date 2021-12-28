package com.sammy.miniecommerce.ui.fragments

import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sammy.miniecommerce.BuildConfig
import com.sammy.miniecommerce.R
import com.sammy.miniecommerce.databinding.CartFragmentBinding
import com.sammy.miniecommerce.databinding.FragmentShareProductBinding
import com.sammy.miniecommerce.models.Product
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ShareProductFragment(private var product: Product?) : BottomSheetDialogFragment() {
    private var mBehavior: BottomSheetBehavior<View>? = null

    private var _binding: FragmentShareProductBinding? = null
    private val binding get() = _binding!!


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: BottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        _binding = FragmentShareProductBinding.inflate(
            LayoutInflater.from(
                context
            )
        )
        dialog.setContentView(binding.root)

        binding.product = product

        mBehavior = BottomSheetBehavior.from(binding.root.parent as View)
        mBehavior!!.peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO

        binding.btnShare.setOnClickListener {
            if(binding.etPrice.text.toString() != "" ) {
                shareProductQrCode(binding.productImage)
            } else {
                Toast.makeText(requireContext(), getString(R.string.set_price), Toast.LENGTH_SHORT).show()
            }

        }

        binding.btnClose.setOnClickListener { dismiss() }

        return dialog
    }

    private fun shareProductQrCode(qrImage: ImageView) {
        val bitmap = (qrImage.drawable as BitmapDrawable).bitmap
        try {
            val shareBody = getString(R.string.hey_there) + binding.etPrice.text
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "image/*"
            sharingIntent.putExtra(Intent.EXTRA_STREAM, getLocalBitmapUri(bitmap))
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, product?.name)
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            sharingIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_via)))
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), getString(R.string.error_occurred), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun getLocalBitmapUri(bitmap: Bitmap): Uri? {
        var bmpUri: Uri? = null
        try {
            val file = File(
                requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                "share_image_" + System.currentTimeMillis() + ".png"
            )
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out)
            out.close()
            bmpUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                FileProvider.getUriForFile(
                    requireContext(),
                    BuildConfig.APPLICATION_ID + ".provider",
                    file
                )
            } else {
                Uri.fromFile(file)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bmpUri
    }

    override fun onStart() {
        super.onStart()
        mBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
    }
}