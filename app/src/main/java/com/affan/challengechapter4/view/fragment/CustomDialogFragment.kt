package com.affan.challengechapter4.view.fragment

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.affan.challengechapter4.databinding.FragmentCustomDialogBinding

class CustomDialogFragment(name : String, result : String) : DialogFragment() {

    private lateinit var binding : FragmentCustomDialogBinding
    private lateinit var listener : DialogListener
    private var name : String
    private var result : String

    interface DialogListener{
        fun getCloseDialog()
        fun goToMenu()
    }

    init {
        this.name = name
        this.result = result
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DialogListener){
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomDialogBinding.inflate(layoutInflater,container,false)
        dialog?.setCancelable(false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvNameDialog.text = name
        binding.tvTheWinner.text = result
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnPlayAgain.setOnClickListener {
            dismiss()
            if (this::listener.isInitialized){
                listener.getCloseDialog()
            }
        }

        binding.btnBackToMenu.setOnClickListener {
            if (this::listener.isInitialized){
                listener.goToMenu()
            }
        }
    }
}