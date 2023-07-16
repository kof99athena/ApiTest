package com.athena.apitest

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.GregorianCalendar

@RequiresApi(Build.VERSION_CODES.O)
class ViewModel : ViewModel() {

    val TAG = "ViewModel"
    val Service = RetrofitClient.getRetrofitService()
    var job: Job? = null //Job은 버튼 클릭했을때 그 순간 해야하는 이벤트, 즉 일이라고 생각하면된다. 
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception: ${throwable.localizedMessage}")
    }

    var data = MutableLiveData<List<DETAIL>>()
    val dataError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        getData()
    }

    fun getData(){
        loading.value = true
        job = CoroutineScope(Dispatchers.IO+exceptionHandler).launch {

            val response = Service.getInfoData(getdate())
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    data.postValue(response.body()?.result?.detail)
                    dataError.postValue(null)
                    loading.postValue(false)
                }else{
                    onError("Error : ${response.message()}")
                }
            }
        }//job
    }//getData()


    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    fun getdate() :String{
        //하루 전 날짜
        val calendar : Calendar = GregorianCalendar()
        val SDF : SimpleDateFormat = SimpleDateFormat("yyyyMMdd")
        calendar.add(Calendar.DATE,-1)
        val res = SDF.format(calendar.time)
        Log.d("getDate()",res )
        return res
    }

    private fun onError(message: String) {
        dataError.postValue(message)
        loading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }



}//ViewModel