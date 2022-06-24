package com.example.calculate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.calculate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)

        val numButton = arrayOf<Button>(
            binding.button0,
            binding.button1,
            binding.button2,
            binding.button3,
            binding.button4,
            binding.button5,
            binding.button6,
            binding.button7,
            binding.button8,
            binding.button9,
        )

        val operateButton = arrayOf<Button>(
            binding.plus,
            binding.minus,
            binding.multipli,
            binding.divide
        )

        var clear=true

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


        for(btn in numButton)  //숫자 버튼
        {
            btn.setOnClickListener{
                if(clear==false)
                {
                    binding.editTextCal.setText("")
                    clear=true
                }
                var a=binding.editTextCal.text.toString()
                var b=btn.text.toString()
                binding.editTextCal.setText(a+b)
            }
        }
        for (btn in operateButton) //연산자 버튼
            btn.setOnClickListener{
                if(clear==false)
                {
                    binding.editTextCal.setText("")
                    clear=true
                }
                var a=binding.editTextCal.text.toString()
                var b=btn.text.toString()
                binding.editTextCal.setText(a+b)
            }
        binding.clear.setOnClickListener { binding.editTextCal.setText("") }
        binding.result.setOnClickListener {
            var a=binding.editTextCal.text.toString()
            var b:List<String> = a.split(" ");
            if(b[b.lastIndex].isNullOrBlank()) Toast.makeText(this@MainActivity, "수식에 오류가 있습니다.", Toast.LENGTH_SHORT).show()
            else binding.editTextCal.setText(calculate(a).toString())
            clear=false
        }

    }
}