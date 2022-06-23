package com.example.calculate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

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
        /* fun calculate(a:String):Double{
            var b:List<String> = a.split(' ');
            var num=ArrayList<String>();
            var operator=ArrayList<String>();
            for (i in b.indices) // 숫자와 연산자 나누기
            {
                if(i%2==1)
                {
                    operator.add(b[i]);
                }
                if(i%2==0)
                {
                    num.add(b[i])
                }
            }
            for(i in operator.indices)
            {
                if(operator[i+1].equals("*")||operator[i+1].equals("/"))
                {

                }
                var result=operate(num[i],num[i+1],operator[i]);

            }
        }
    }
        */
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
            var b=a.split(" ")
            cal.setText(operate(b[0],b[2],b[1]).toString())
        }

    }
}