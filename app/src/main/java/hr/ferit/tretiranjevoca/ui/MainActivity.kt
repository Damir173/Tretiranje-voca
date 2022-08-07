package hr.ferit.tretiranjevoca.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.ferit.tretiranjevoca.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

