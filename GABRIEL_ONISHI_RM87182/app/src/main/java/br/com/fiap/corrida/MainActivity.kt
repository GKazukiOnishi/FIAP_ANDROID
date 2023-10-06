package br.com.fiap.corrida

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.fiap.corrida.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}