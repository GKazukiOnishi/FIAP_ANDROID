package br.com.fiap.corrida

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import br.com.fiap.corrida.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<HorseRaceViewModel>()
    private var coroutine: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButtons()
        setObservers()
    }

    private fun setupButtons() {
        binding.startButton.setOnClickListener {
            startHorseRace()
        }
        binding.stopButton.setOnClickListener {
            binding.startButton.isEnabled = true
            coroutine?.cancel()
        }
    }

    private fun startHorseRace() {
        coroutine = lifecycleScope.launch {
            binding.startButton.isEnabled = false
            binding.winnerText.text = ""

            viewModel.startRace()

            binding.startButton.isEnabled = true
        }
    }

    private fun setObservers() {
        viewModel.redHorseProgress.observe(this, Observer { progress ->
            binding.redHorseProgress.progress = progress

            if (progress >= 100) {
                binding.winnerText.text = getString(R.string.winner_text, getString(R.string.red_horse_name).lowercase())
                binding.winnerText.setTextColor(getColor(R.color.red))
            }
        })
        viewModel.greenHorseProgress.observe(this, Observer { progress ->
            binding.greenHorseProgress.progress = progress

            if (progress >= 100) {
                binding.winnerText.text = getString(R.string.winner_text, getString(R.string.green_horse_name).lowercase())
                binding.winnerText.setTextColor(getColor(R.color.green))
            }
        })
    }

}