package com.affan.challengechapter4.view.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.affan.challengechapter4.databinding.FragmentCustomDialogBinding

class CustomDialogFragment(name : String, result : String) : DialogFragment() {

    interface DialogListener{
        fun getCloseDialog()
    }

    private lateinit var binding : FragmentCustomDialogBinding
    private lateinit var listener : DialogListener
    private var name : String
    private var result : String


    init {
        this.name = name
        this.result = result
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomDialogBinding.inflate(layoutInflater,container,false)
        dialog?.setCancelable(true)
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

        binding.btnBackToMenu.setOnClickListener {
//            val intent = Intent(viewLifecycleOwner,MenuActivity::class.java)
        }
    }

//    override fun onAttachFragment(childFragment: Fragment) {
//        if (childFragment is CustomDialogFragment){
//            childFragment.setListener(this)
//        }
//    }

    fun setListener(listener : DialogListener){
        this.listener = listener
    }
}