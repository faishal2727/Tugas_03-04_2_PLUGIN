package com.example.retrofit03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.retrofit03.databinding.ActivityPostBinding
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class Post : AppCompatActivity() {
    private lateinit var binding: ActivityPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ButtonSimpan()
        getDataActivity()
        BtnUpdate()
    }
    private fun postData(){
        var namaPertama = binding.et1.text.toString()
        var namaTerakhir = binding.et2.text.toString()
        var email = binding.et3.text.toString()

        Api.APIEndpoint().PostData(namaPertama,namaTerakhir,email)
            .enqueue(object : retrofit2.Callback<ListResponse<personRespon>>{
                override fun onFailure(call: Call<ListResponse<personRespon>>, t: Throwable) {
                    println(t.message)
                }

                override fun onResponse(
                    call: Call<ListResponse<personRespon>>,
                    response: Response<ListResponse<personRespon>>
                ) {
                    if (response.isSuccessful){
                        val body  = response.body()
                        Toast.makeText(applicationContext, " Sukses", Toast.LENGTH_LONG).show()
                        println("sukses tambah data"+ body)
                    }
                }


            })
    }
    private fun ButtonSimpan(){
        binding.btnSave.setOnClickListener {
            postData()
            finish()
        }
    }
    fun getDataActivity(){
        binding.et1.setText(intent.getStringExtra("first_name"))
        binding.et2.setText(intent.getStringExtra("last_name"))
        binding.et3.setText(intent.getStringExtra("email"))
    }
    fun UpdateData(){
        val id = intent.getIntExtra("id",1)
        val first_name = binding.et1.text.toString()
        val last_name = binding.et2.text.toString()
        val email = binding.et3.text.toString()
        Api.APIEndpoint().UpdateData(id,first_name,last_name,email)
            .enqueue(object : retrofit2.Callback<SingleResponse<personRespon>>{
                override fun onFailure(call: Call<SingleResponse<personRespon>>, t: Throwable) {
                    println("Sukses Update Data")
                }

                override fun onResponse(
                    call: Call<SingleResponse<personRespon>>,
                    response: Response<SingleResponse<personRespon>>
                ) {
                    if(response.isSuccessful){
                        val body = response
                        Toast.makeText(applicationContext,"Sukses Update Data", Toast.LENGTH_LONG).show()
                        println("Sukses Update data")
                    }
                }

            })

    }
    private fun BtnUpdate(){
        binding.btnUpdate.setOnClickListener {
            UpdateData()
            finish()
        }
    }
}