package com.astro.cf_user_rating

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.astro.cf_user_rating.API.CF_API
import com.astro.cf_user_rating.API.CF_data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)


        var userInput:String = "tourist"
        var ratingUpdateTimeSeconds:Int
        var newRating: Int


        val apiService = CF_API()


        val response_cf = apiService.getUserRating(userInput)



        CF_API.invoke().getUserRating(userInput).enqueue(object : Callback<CF_data> {
            override fun onFailure(call: Call<CF_data>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<CF_data>, response: Response<CF_data>) {
                Log.i("In Response","TRUE")
                Log.i("Respose",response.code().toString())
                if(response.code().equals(400)){
                    Toast.makeText(applicationContext,"User not found", Toast.LENGTH_SHORT).show()
                }else if(response.code().equals(200)){
//                    progressBar?.visibility = View.INVISIBLE

                    val data = response.body()


                    data?.let {
//                        var firstName = it.result[0].firstName
                        for(x in it.result){
                            Log.i("Time",x.ratingUpdateTimeSeconds.toString())
                            Log.i("newRating",x.newRating.toString())
                            Log.i("END Loop","=====================================================")
                        }
                    }


                }

            }

        })


    }
}