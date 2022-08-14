package com.affan.challengechapter4.view.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.affan.challengechapter4.databinding.FragmentCustomDialogBinding

class CustomDialogFragment(
    private val closeDialog :()-> Unit,
    private val goToMenu : ()-> Unit,
    name : String,
    result : String
) : DialogFragment() {

    private lateinit var binding : FragmentCustomDialogBinding
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
        /**
         I set "setCancelable" to false, because dialog in the flowchart only has two decisions.
         All those decisions are "back to menu" and "play again". Play again button automatically
         refresh this game. Refresh button can used in multiplayer mode, when player 1 has
         selected a hand and player 2 has not selected a hand.
         **/
        dialog?.setCancelable(false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvNameDialog.text = name
        binding.tvTheWinner.text = result
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnPlayAgain.setOnClickListener {
            dismiss()
            closeDialog()
        }

        binding.btnBackToMenu.setOnClickListener {
            goToMenu()
        }
    }
}