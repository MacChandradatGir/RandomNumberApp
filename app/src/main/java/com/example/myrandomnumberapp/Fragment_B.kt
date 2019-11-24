package com.example.myrandomnumberapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_layout_b.*

class Fragment_B :Fragment() {

    private lateinit var fragmentDelegate: FragmentBDelegate

    fun setDelegate(fragmentADelegate: FragmentBDelegate){
        this.fragmentDelegate = fragmentADelegate
    }

    interface FragmentBDelegate {
        fun sendMessageToFragmentA(message: String)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_layout_b,container, false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_b_button.setOnClickListener {
            fragmentDelegate.sendMessageToFragmentA("")
        }
    }

    fun displayMessageInB(message: String){
        fragment_b_textView.text = message
    }


}