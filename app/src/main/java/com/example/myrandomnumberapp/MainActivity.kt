package com.example.myrandomnumberapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Fragment_A.FragmentADelegate, Fragment_B.FragmentBDelegate {

    var num:Int =0
    val requestCode=111
   // lateinit var  button : Button

    override fun sendMessageToFragmentA(message: String) {
        fragmentA.displayMessageInA(num.toString())

    }

    override fun sendMessageToFragmentB(message: String) {
        fragmentB.displayMessageInB(num.toString())

    }
    private lateinit var fragmentA: Fragment_A
    private lateinit var fragmentB: Fragment_B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECEIVE_SMS)
            != PackageManager.PERMISSION_GRANTED
        ){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(arrayOf("android.permission.RECEIVE_SMS"),requestCode)
            }else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf("android.permission.RECEIVE_SMS"),
                    requestCode
                )
            }
        }else{
            Log.d("TAG_X", "Permission Granted")
            opening_settting_button.visibility = View.INVISIBLE
        }

        opening_settting_button.setOnClickListener {
            val intent = Intent("android.permission.RECEIVE_SMS")
            startActivity(intent)
        }

        fragmentA = Fragment_A()
        fragmentB = Fragment_B()

        open_fragment_button.setOnClickListener {
            num = (1..20).shuffled().last()
            openFragments()

        }



    }//oncreate

    private fun openFragments(){
        supportFragmentManager.beginTransaction()
            .add(R.id.frame_layout_one, fragmentA)
            .addToBackStack(fragmentA.tag)
            .commit()

        supportFragmentManager.beginTransaction()
            .add(R.id.frame_layout_two, fragmentB)
            .addToBackStack(fragmentB.tag)
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack()
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is Fragment_A)
            (fragment as Fragment_A).setDelegate(this)
        else
            (fragment as Fragment_B).setDelegate(this)
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == this.requestCode && permissions[0] == "android.permission.RECEIVE_SMS"
            && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Thank yoy for granting permission", Toast.LENGTH_LONG).show()
            opening_settting_button.visibility = View.INVISIBLE
        }else if(
            ActivityCompat.shouldShowRequestPermissionRationale(
                this, "android.permission.RECEIVE_SMS"
            )
        ){
            ActivityCompat.requestPermissions(
                this,
                arrayOf("android.permission.RECEIVE_SMS"),
                requestCode
            )
        }else{
            Toast.makeText(this, "You need to grant permission to use this application",Toast.LENGTH_LONG).show()
            main_textview.text="You need to grant permission to use this application"
            opening_settting_button.visibility = View.VISIBLE
        }
    }


}
