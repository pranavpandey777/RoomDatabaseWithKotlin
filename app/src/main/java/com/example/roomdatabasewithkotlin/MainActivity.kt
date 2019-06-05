package com.example.roomdatabasewithkotlin


import android.arch.persistence.room.Room
import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast




class MainActivity : AppCompatActivity() {

    lateinit var name: EditText
    lateinit var save: Button
    lateinit var show: Button
    lateinit var datadao: DataDao

    lateinit var myDatabase: MyDatabase
    lateinit var data: Data
    lateinit var datalist: List<Data>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name = findViewById(R.id.name)
        save = findViewById(R.id.save)
        show = findViewById(R.id.show)


       /* myDatabase = Room.databaseBuilder(this, MyDatabase::class.java, "Wahh2")
            .build()
        datadao = myDatabase.dao()*/
        val datalist = ArrayList<Data>()
         myDatabase = MyDatabase.getUserInstance(this)!!
          datadao = myDatabase.dao()

        save.setOnClickListener {


            var name: String = name.text.toString()


        Insertion(datadao,this@MainActivity).execute(name)

        }

        show.setOnClickListener {

            ViewUser(datadao,datalist,this@MainActivity).execute()

        }


    }

    class Insertion(val userDao:DataDao,val context:Context) : AsyncTask<String, Void, Long>() {
        override fun doInBackground(vararg params: String?): Long? {
            var iname= params[0]



           var data = Data(0,iname.toString())

           var id:Long =userDao.insert(data)

          /*  context.runOnUiThread{
                if(id.equals(-1))
                    Toast.makeText(context,"okk",Toast.LENGTH_SHORT).show()

            }*/


            return id
        }

        override fun onPostExecute(result: Long?) {
            super.onPostExecute(result)

            if(result!!.equals(-1))
                Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(context,"Inserted",Toast.LENGTH_SHORT).show()
        }
    }

    class ViewUser (var datadao:DataDao,var datalist:ArrayList<Data>,var context:Context): AsyncTask<Void, Void, ArrayList<Data>>() {
        override fun doInBackground(vararg params: Void?): ArrayList<Data> {

datalist= datadao.getdata() as ArrayList<Data>

            return datalist
        }

        override fun onPostExecute(result: ArrayList<Data>?) {
            super.onPostExecute(result)

            var buffer= StringBuffer()

         result?.forEach {
buffer.append("$it \n ")

         }
            Toast.makeText(context,""+buffer.toString(),Toast.LENGTH_SHORT).show()
        }
    }

}
