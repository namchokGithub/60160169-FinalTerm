package com.example.a60160169_finalterm


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import org.json.JSONObject
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass.
 */
class RecyclerView : Fragment() {

    override fun onCreateView(inflater : LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recycler_view, container, false)
        // Inflate the layout for this fragment

        // Some url endpoint that you may have
        val myUrl = "https://api.github.com/users/hadley/orgs"

        //String to place our result in
        var result : String

        //Perform the doInBackground method, passing in our url
        result = HttpGetRequest().execute(myUrl).get()
        var str = "{data:$result}"

        val json = JSONObject(str)
        val jsonArray = json.getJSONArray("data")

        val recyclerView: RecyclerView = view.findViewById(R.id.recyLayout)

        //ตั้งค่า Layout
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity!!.baseContext)
        recyclerView.layoutManager = layoutManager

        //ตั้งค่า Adapter
        val adapter = MyRecyclerAdapter(activity!!, jsonArray)
        recyclerView.adapter = adapter

        return view
    }

    /*
     * @Name: loadJsonFromAsset
     * @Description: Function for load JSON from folder asset
     * @Author: Namchok Singhachai
     *  */
    private fun loadJsonFromAsset(filename: String, context: Context): String? {
        val json: String?

        try {
            val inputStream = context.assets.open(filename)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: java.io.IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }
    // ตัวอย่างการใช้ loadJsonFromAsset
    //  val jsonString : String = loadJsonFromAsset("recipes.json", activity!!.baseContext).toString()
    // ----------------------------------------------------------------------------------------------------------------------- //



}