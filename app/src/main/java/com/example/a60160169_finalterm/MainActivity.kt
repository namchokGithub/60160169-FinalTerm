package com.example.a60160169_finalterm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentRecyclerView = RecyclerView()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.layout, fragmentRecyclerView,"fragmentRecyclerView")
        transaction.addToBackStack("fragmentRecyclerView")
        transaction.commit()

    }
}
