package com.example.calculate

import android.content.Context
import androidx.lifecycle.ViewModel

class VM(context: Context): ViewModel() {
    var share:SharedDB= SharedDB(context)
    fun operate(a: String, b: String, c: String): Double { //단순 계산 하는 함수
        var result = 0.0
        var ca=a.toDouble();
        var cb=b.toDouble();
        when (c) {
            "+" -> {
                result=ca+cb
            }
            "-" -> {
                result=ca-cb;
            }
            "*" -> {
                result=ca*cb
            }
            "/" -> {
                result=ca/cb
            }
        }
        return result
    }
    fun calculate(a:String):Double{  //전체 계산하는 함수
        var b:List<String> = a.split(" ")
        if(b.size==1)
        {
            return b[0].toDouble()
        }
        var num=ArrayList<String>();
        var operator=ArrayList<String>();
        for (i in b.indices){ // 숫자와 연산자 나누기
            if(i%2==0) { //숫자 넣기
                num.add(b[i])
            }
            else if(i%2==1) {  //연산자 넣기
                operator.add(b[i]);
                if(num.size >2 && operator.size>=1) { //전에 곱하기 나누기 있을때는 먼저 계산
                    if(operator[operator.lastIndex-1]=="*" || operator[operator.lastIndex-1]=="/" ) {
                        num[operator.lastIndex-1]=operate(num[operator.lastIndex-1],num[operator.lastIndex],operator[operator.lastIndex-1]).toString()
                        num.removeLast()
                        operator.removeAt(operator.lastIndex-1);
                    }
                }
            }
        }
        if(operator[operator.lastIndex]=="*" || operator[operator.lastIndex]=="/" ) { //마지막에 곱하기 나누기 있을때
            num[operator.lastIndex]=operate(num[operator.lastIndex],num[operator.lastIndex+1],operator[operator.lastIndex]).toString()
            num.removeLast()
            operator.removeAt(operator.lastIndex);
        }

        var final:String=num[0]
        for(i in operator.indices) //나머지 더하기 빼기 연산
        {
            final=operate(final,num[i+1],operator[i]).toString()
        }
        return final.toDouble();
    }
    fun clear()
    {
        share.clear()
    }
    fun putString(a:String)
    {
        share.putString(a)
    }



}