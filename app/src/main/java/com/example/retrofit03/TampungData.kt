package com.example.retrofit03

import android.app.Person
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.retrofit03.databinding.ActivityMainBinding
import com.example.retrofit03.databinding.ActivityTampungDataBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.POST

class TampungData : AppCompatActivity() {
    private lateinit var binding: ActivityTampungDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTampungDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDataForm()
        deleted()
    }
    private fun getDataForm(){
        Api.APIEndpoint().getId(intent.getIntExtra("id", 1))
            .enqueue(object : Callback<SingleResponse<personRespon>>{
                override fun onFailure(call: Call<SingleResponse<personRespon>>, t: Throwable) {
                    println(t.message)
                }

                override fun onResponse(
                    call: Call<SingleResponse<personRespon>>,
                    response: Response<SingleResponse<personRespon>>
                ) {
                    if (response.isSuccessful){
                        val data = response.body()
                        if (data != null){
                            show(data.data)
                        }
                    }
                }

            })
    }
    private fun show(post : personRespon){
        binding.tv1.text = post.first_name
        binding.tv2.text = post.last_name
        binding.tv3.text = post.email
        binding.tv4.text = post.createdAt
        binding.tv5.text = post.updatedAt
    }
    private fun delete(){
        Api.APIEndpoint().delete(intent.getIntExtra("id",1))
            .enqueue(object : Callback<Void>{
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    println(t.message)
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful){
                        Toast.makeText(applicationContext, "Sukses Dihapus", Toast.LENGTH_LONG).show()
                    }
                }

            })
    }
    private fun deleted(){
        binding.btnHapus.setOnClickListener {
            delete()
            finish()
        }
    }
}