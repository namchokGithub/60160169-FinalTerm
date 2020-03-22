package com.example.a60160169_finalterm

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import org.json.JSONArray
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerAdapter(fragmentActivity: FragmentActivity, val dataSource: JSONArray) : RecyclerView.Adapter<MyRecyclerAdapter.Holder>() {

    private val thisContext : Context = fragmentActivity.baseContext
    private val thisActivity = fragmentActivity


    class Holder(view : View) : RecyclerView.ViewHolder(view) {

        private val View = view;

        lateinit var layout : LinearLayout
        lateinit var text1: TextView
        lateinit var text2: TextView
        lateinit var image: ImageView

        fun Holder(){

            layout = View.findViewById<View>(R.id.recyview_layout) as LinearLayout
            text1 = View.findViewById<View>(R.id.text1) as TextView
            text2 = View.findViewById<View>(R.id.text2) as TextView
            image = View.findViewById<View>(R.id.image) as ImageView

        }

    }


    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.recy_layout, parent, false))
    }


    override fun getItemCount(): Int {
        return dataSource.length()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.Holder()

        holder.text1.text = "Github-ID" + dataSource.getJSONObject(position).getString("login").toString()
        holder.text2.text = "Github-Node" + dataSource.getJSONObject(position).getString("node_id").toString()

        Glide.with(thisContext)
            .load(dataSource.getJSONObject(position).getString("avatar_url").toString())
            .into(holder.image)

        holder.layout.setOnClickListener{

            // สร้าง Dialog
            val builder: AlertDialog.Builder = AlertDialog.Builder(thisActivity)

            // กำหนดข้อความให้ Dialog
            builder.setMessage(
                "URL : ${dataSource.getJSONObject(position).getString("url").toString()}"
            )

            // กำหนดปุ่มยกเลิก
            builder.setNegativeButton("CLOSE") { _, _ ->
                Toast.makeText(thisContext, "CLOSED", Toast.LENGTH_SHORT).show()
            }

            // แสดง Dialog
            builder.show()
            // Toast.makeText(thisContext,holder.text1.text.toString(),Toast.LENGTH_SHORT).show()

        }

    }



}
