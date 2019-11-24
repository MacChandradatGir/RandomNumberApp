package com.example.myrandomnumberapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_layout_a.*

class Fragment_A : Fragment(){


    var num:Int=0
    private lateinit var fragmentDelegate: FragmentADelegate

    fun setDelegate(fragmentADelegate: FragmentADelegate){
        this.fragmentDelegate = fragmentADelegate
    }

    interface FragmentADelegate{
        fun sendMessageToFragmentB(message: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_layout_a, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fragment_a_button.setOnClickListener {
            fragmentDelegate.sendMessageToFragmentB("")
        }
    }

    fun displayMessageInA(message: String){
        fragment_a_textView.text=message
    }
}