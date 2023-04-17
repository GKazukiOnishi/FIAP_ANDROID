package br.com.fiap.chatgpt.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.fiap.chatgpt.R
import br.com.fiap.chatgpt.data.TalkModel
import br.com.fiap.chatgpt.databinding.ActivityAnswersBinding
import br.com.fiap.chatgpt.presentation.adapter.AnswerAdapter

class AnswersActivity : AppCompatActivity() {

    lateinit var binding: ActivityAnswersBinding

    private val talkModel by lazy { //tipo do talkModel não é obrigatório, ele deduz pelo retorno
        intent.extras?.getSerializable(TALK_MODEL_KEY) as TalkModel //cast para TalkModel no Kotlin
    } //poderíamos ter chamado direto, a diferença do by lazy é que ele só vai executar a lambda
    // de atribuição dela quando a variável talkModel for utilizada

    //se não usarmos o by lazy aqui, ao acessar o intent ele não teria sido instanciado ainda
    //pois a atribuição rodaria antes do onCreate, nisso o by lazy garante que não vai chamar variável nula

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupQuestion()
        setupRecyclerView()
    }

    private fun setupQuestion() {
        binding.textInputEditTextQuestion.setText(talkModel?.question)
    }

    private fun setupRecyclerView() {
        with(binding.recyclerViewAnswers) {
            talkModel?.answers?.let {
                adapter = AnswerAdapter(
                    it,
                    ::callImplicitIntent
                )
            }
        }
    }

    private fun callImplicitIntent(messageValue: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(
                Intent.EXTRA_TEXT,
                messageValue
            )
        }

        val shareIntent = Intent.createChooser(
            sendIntent,
            getString(R.string.chooser_title)
        )

        startActivity(shareIntent)
    }

    //companion object, é como o static do java, criar algo estático
    companion object {
        const val TALK_MODEL_KEY = "TALK_MODEL_KEY"
    }
}