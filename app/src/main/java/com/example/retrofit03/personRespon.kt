package com.example.retrofit03

data class personRespon (
    var id : Int,
    var first_name : String,
    var last_name : String,
    var email : String,
    var createdAt : String,
    var updatedAt : String

    )
    data class ListResponse<T>(
        var message : String,
        var status : Int,
        var data : List<T>
    )
    data class SingleResponse<T>(
    var message : String,
    var status : Int,
    var data : T
)

