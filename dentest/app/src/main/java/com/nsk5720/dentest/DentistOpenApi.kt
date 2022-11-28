package com.nsk5720.dentest


import com.nsk5720.projectmobile.data.Dentist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

class DentistOpenApi {
    companion object {
        val DOMAIN = "https://openapi.gg.go.kr/"
        val API_KEY = "48001835c3af4a1aacc5eab043c0dcac"
    }
}

interface DentistOpenService {
    @GET("DentistryPrivateHospital?key={api_key}&pIndex=1&type=json&pSize=1000")
    fun getDentist(@Path("api_key") key: String): Call<Dentist>
}