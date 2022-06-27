package com.example.calculate

import android.content.Context
import android.content.SharedPreferences

class SharedDB(context: Context)  {
    val sharedPref:SharedPreferences=context.getSharedPreferences("cal",Context.MODE_PRIVATE)
    var editor: SharedPreferences.Editor = sharedPref.edit()
    var index:Int=1
    fun clear(){
        editor.clear().apply()
    }
    fun putString(a:String)
    {
        editor.putString("${index}",a).apply()
        index+=1
    }


}