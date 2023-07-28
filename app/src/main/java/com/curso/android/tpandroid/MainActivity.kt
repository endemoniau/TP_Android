package com.curso.android.tpandroid

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.curso.android.tpandroid.databinding.ActivityMainBinding
import model.ComparadorModel
import model.tpViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: tpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.setOkMessage(getString(R.string.txt_result_ok))
        mainViewModel.setFailMessage(getString(R.string.txt_result_fail))

        mainViewModel.resultMessage.observe(this, Observer{
            binding.textView4.setText(it)
        })

        binding.btnComparar.setOnClickListener{
            val textos = ComparadorModel(binding.primerTxt.text.toString(),binding.segundoTxt.text.toString())
            mainViewModel.checkStrings(textos)
        }
    }
}

