package com.example.maticcodechallenge.ui.fragment.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.maticcodechallenge.R
import com.example.maticcodechallenge.databinding.NoInternetBinding

class NoInternetDialog : DialogFragment() {

    private lateinit var binding: NoInternetBinding
    private lateinit var iInternetFragment: IInternetFragment


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_no_internet, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.closeButton.setOnClickListener {
            iInternetFragment.onInternetFragmentClosed()
        }

    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
        dialog?.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onDestroy() {
        super.onDestroy()
        val parent = view?.parent as? ViewGroup
        parent?.removeView(view)
        iInternetFragment.onInternetFragmentClosed()
    }

    interface IInternetFragment {
        fun onInternetFragmentClosed()
    }

    fun setCallback(IInternetFragment: IInternetFragment) {
        iInternetFragment = IInternetFragment
    }

}
