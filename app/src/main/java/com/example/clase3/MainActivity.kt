package com.example.clase3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import com.example.clase3.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Thread.sleep
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        numberPicker()
        GlobalScope.launch {
            progressBarHorizonal()
        }
        seekBarStandar()
        ratingBar()
        irAWebView()
        call()
    }

    private fun numberPicker(){
        binding.numberPicker.minValue = 1
        binding.numberPicker.maxValue = 100
        binding.numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            binding.tvData.text = "Resultado: ${newVal}"
        }
    }

    private fun progressBarHorizonal() {
        while (binding.pbHorizontal.progress < binding.pbHorizontal.max) {
            sleep(1000L)
            binding.pbHorizontal.incrementProgressBy(5)
        }
    }

    private fun seekBarStandar(){
        binding.sbStandar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //Cuando el seekBar cambia mostrar el progreso
                binding.tvData.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) { //cuando se toca el SeekBar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {//Cuando se deja de tocar el SeekBar
            }
        })
    }

    private fun ratingBar(){
        binding.rbStart.setOnRatingBarChangeListener { _, rating, _ ->
            binding.tvData.text = "Tu calificación es: ${rating}"
        }
    }

    private fun irAWebView(){
        binding.btnWebView.setOnClickListener {
            val intent = Intent(this, WebView::class.java)
            startActivity(intent)
        }
    }
    private fun call(){
        binding.btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = "tel:3127030308".toUri()
            startActivity(intent)
        }
    }
}