package com.example.volleykullanimi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.volleykullanimi.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
   //viewbinding
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)

        val url="https://jsonplaceholder.typicode.com/posts"


      binding.btnInput.setOnClickListener {
          val queue= Volley.newRequestQueue(this)
          val request=StringRequest(Request.Method.GET,url,
              Response.Listener {response ->
                  val gelenVeri=response.toString()
                  //Request İŞLEMİ(JSON and JARRAY)
                 try {
                     val jArray=JSONArray(gelenVeri)
                     for(i in 0..1){
                         var jObject = jArray.getJSONObject(i)
                         val userid = jObject.getInt("userId")
                         val id = jObject.getInt("id")
                         val title =jObject.getString("title")
                         val body=jObject.getString("body")
                         binding.textView.text="USER ID:"+userid.toString()+"\n"+"ID: "+id.toString()+"\n"+"TITLE: "+title+"\n"+"BODY: "+body

                         Log.e("USERID:",userid.toString())
                         Log.e("ID:",id.toString())
                         Log.e("TITLE:",title.toString())
                         Log.e("BODY:",body.toString())
                 }
                  }catch (ex:java.lang.Exception){
                      Log.e("HATA",ex.toString())
                  }

              },Response.ErrorListener {
                  Log.e("Hata",it.toString())
              }
          )
          queue.add(request)
      }
    }
}