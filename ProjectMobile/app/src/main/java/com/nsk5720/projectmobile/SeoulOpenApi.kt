package com.nsk5720.projectmobile

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import data.Library

class SeoulOpenApi {
    companion object {
        val DOMAIN = "http://openAPI.seoul.go.kr:8088/"
        val API_KEY = "6d43464e716e736b3631414a445447"
    }
}

interface SeoulOpenService {
    @GET("{api_key}/json/SeoulPublicLibraryInfo/1/203")
    fun getLibrary(@Path("api_key") key:String) : Call<Library>
}