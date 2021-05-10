package com.example.retrofit03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit03.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    lateinit var main : Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
        Tambah()
    }
    fun getData(){
        Api.APIEndpoint().getPerson().enqueue(object :Callback<ListResponse<personRespon>>{
            override fun onFailure(call: Call<ListResponse<personRespon>>, t: Throwable) {
                println(t.message)
            }
            override fun onResponse(
                call: Call<ListResponse<personRespon>>,
                response: Response<ListResponse<personRespon>>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    if(body != null){
                        RV(body.data)
                        Toast.makeText(applicationContext,"Berhasil", Toast.LENGTH_LONG).show()
                    }
                }
            }

        })

    }fun RV(Person : List<personRespon>){
        main = Adapter(Person,object : Adapter.Click{
            override fun click(post: personRespon) {
                startActivity(Intent(this@MainActivity,TampungData::class.java).apply {
                    putExtra("id",post.id)
                })
            }
            override fun Edit(post: personRespon) {
                startActivity(Intent(this@MainActivity,Post::class.java).apply {
                    putExtra("id",post.id)
                    putExtra("first_name",post.first_name)
                    putExtra("last_name",post.last_name)
                    putExtra("email",post.email)
                } )
            }
        })
        binding.rcy.apply{
            adapter = main
            layoutManager = LinearLayoutManager(this@MainActivity)


        }
        }
    fun Tambah(){
        binding.tambah.setOnClickListener {
            startActivity(Intent(this,Post::class.java))
        }
    }
    }
