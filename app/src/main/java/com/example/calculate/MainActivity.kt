package com.example.calculate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.nio.file.Files.find

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cal: EditText = findViewById(R.id.editTextTextPersonName4);
        val numButton = arrayOf<Button>(
            findViewById(R.id.button0),
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById(R.id.button8),
            findViewById(R.id.button9)
        )
        val operateButton = arrayOf<Button>(
            findViewById(R.id.plus),
            findViewById(R.id.minus),
            findViewById(R.id.multipli),
            findViewById(R.id.divide)
        )
        val clearButton: Button = findViewById(R.id.clear)
        val resultButton:Button=findViewById(R.id.result)


        fun operate(a: String, b: String, c: String): Double {
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

         fun calculate(a:String):Double{
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


        for(btn in numButton)
        {
            btn.setOnClickListener{
                var a=cal.text.toString()
                var b=btn.text.toString()
                cal.setText(a+b)
            }
        }
        for (btn in operateButton)
            btn.setOnClickListener{
                var a=cal.text.toString()
                var b=btn.text.toString()
                cal.setText(a+b)
            }
        clearButton.setOnClickListener{
            cal.setText("")
        }
        resultButton.setOnClickListener{
            var a=cal.text.toString()
            var b:List<String> = a.split(" ");
            if(b[b.lastIndex].isNullOrBlank()) Toast.makeText(this@MainActivity, "수식에 오류가 있습니다.", Toast.LENGTH_SHORT).show()
            else cal.setText(calculate(a).toString())
        }

    }
}