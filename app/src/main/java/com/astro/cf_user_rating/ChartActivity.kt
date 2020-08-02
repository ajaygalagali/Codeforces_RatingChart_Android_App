package com.astro.cf_user_rating

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.astro.cf_user_rating.API.CF_API
import com.astro.cf_user_rating.API.CF_data
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChartActivity : AppCompatActivity() {

    lateinit  var lineChart: LineChart
    lateinit var arrayList: ArrayList<Entry>
    lateinit var dataSetOne: LineDataSet
    lateinit var dataA: LineData
    lateinit var datasets: java.util.ArrayList<LineDataSet>
//    var fetchFlag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)

        // Line Chart

    arrayList = ArrayList()


    var handleName = intent.getStringExtra("name")









        // Data fetching
        var userInput:String = handleName.toString()
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

                    var count = 0
                    data?.let {
//                        var firstName = it.result[0].firstName
                        for(x in it.result){
                           /* Log.i("Time",x.ratingUpdateTimeSeconds.toString())
                            Log.i("newRating",x.newRating.toString())
                            Log.i("END Loop","=====================================================")
*/
                            var time = x.ratingUpdateTimeSeconds.toFloat()
                            var rating = x.newRating.toFloat()
                            arrayList.add(Entry(count.toFloat(),rating))
                            count+=1



                        }


                        plotChart()



//                        fetchFlag = true

                    }

                }

            }

        })

        /*if(fetchFlag){



        }*/

    }

    fun plotChart(){

        lineChart = findViewById(R.id.lineChart)
        lineChart.visibility = View.VISIBLE
        lineChart.isDragEnabled = true
        lineChart.setScaleEnabled(false)


        lineChart.notifyDataSetChanged()

        Log.i("Array",arrayList.toString())

        dataSetOne = LineDataSet(arrayList,"Set 1")
        datasets = java.util.ArrayList()
        datasets.add(dataSetOne)
        dataA = LineData(datasets as List<ILineDataSet>?)
        lineChart.data = dataA
//    lineChart.notifyDataSetChanged()
        Log.i("LineCHart","End")

    }
}