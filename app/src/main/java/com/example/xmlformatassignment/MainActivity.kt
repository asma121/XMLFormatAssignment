package com.example.xmlformatassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var rvmain:RecyclerView
    lateinit var students: List<student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvmain=findViewById(R.id.rvmain)


        try{
            val parser = MyXmlPullParserHandler()
            val iStream = assets.open("StudentDetails.xml")
            students = parser.parse(iStream)


            rvmain.adapter=myAdapter(students)
            rvmain.layoutManager= LinearLayoutManager(this)
        }catch (e: IOException) {
            println("ISSUE: $e")
        }
    }
}