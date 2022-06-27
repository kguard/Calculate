package com.example.calculate


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.calculate.databinding.ActivityMainBinding
import androidx.recyclerview.widget.LinearLayoutManager


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        var viewModel=VM(this);
        viewModel.clear()

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
        {
            btn.setOnClickListener {
                clear=true
                var a = binding.editTextCal.text.toString()
                var b = btn.text.toString()
                binding.editTextCal.setText(a + b)
            }
        }
        binding.clear.setOnClickListener { binding.editTextCal.setText("") }

        binding.result.setOnClickListener {
            var a=binding.editTextCal.text.toString()
            var b:List<String> = a.split(" ");

            if(b[b.lastIndex].isNullOrBlank()) Toast.makeText(this@MainActivity, "수식에 오류가 있습니다.", Toast.LENGTH_SHORT).show()
            else binding.editTextCal.setText(viewModel.calculate(a).toString())

            viewModel.putString(a+"="+viewModel.calculate(a).toString())
            clear=false
        }
        binding.historyButton.setOnClickListener{
            if(binding.recycle.visibility== View.INVISIBLE)
            {
                binding.recycle.visibility=View.VISIBLE
                binding.tableLayout.visibility=View.INVISIBLE
            }
            else if(binding.recycle.visibility== View.VISIBLE)
            {
                binding.recycle.visibility=View.INVISIBLE
                binding.tableLayout.visibility=View.VISIBLE
            }
            binding.recycle.layoutManager=LinearLayoutManager(this)
            binding.recycle.adapter=HistoryAdapter(viewModel.share.sharedPref)

        }

    }
}