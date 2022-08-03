package com.affan.challengechapter4.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.affan.challengechapter4.databinding.FragmentThirdLandingPageBinding

class ThirdLandingPageFragment : Fragment() {

    interface ThirdListener {
        fun textFromThirdFragment(name : String)
    }

    private lateinit var binding : FragmentThirdLandingPageBinding
    private lateinit var listener : ThirdListener


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentThirdLandingPageBinding
            .inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getClickListener()
    }

    private fun getClickListener () {
        binding.btnNext.setOnClickListener {
            var isEmptyField = false
            val textToEdit = binding.edtEditName.text.toString().trim()
                .replace("\n","")
                .replace(" ","")
            if (textToEdit.isEmpty()) {
                isEmptyField = true
                binding.edtEditName.error = "This field must be filled"
            }
            if (!isEmptyField){
                listener.textFromThirdFragment(textToEdit)
            }
        }
    }

    fun setListener(listener : ThirdListener){
        this.listener = listener
    }
}