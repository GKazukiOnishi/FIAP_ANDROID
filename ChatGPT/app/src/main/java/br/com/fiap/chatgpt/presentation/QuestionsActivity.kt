package br.com.fiap.chatgpt.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.fiap.chatgpt.data.TalkDataSource
import br.com.fiap.chatgpt.data.TalkModel
import br.com.fiap.chatgpt.databinding.ActivityQuestionsBinding
import br.com.fiap.chatgpt.presentation.adapter.QuestionAdapter

class QuestionsActivity : AppCompatActivity() {

    lateinit var binding: ActivityQuestionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewQuestions.adapter = QuestionAdapter(
            mutableListOf<TalkModel>().apply {
                addAll(TalkDataSource.talkList)
                addAll(TalkDataSource.talkList)
            },
            ::goToAnswersActivity //desse jeito passamos a função como parâmetro direto
//            { //abrindo e fechando chaves já temos uma função
//                goToAnswersActivity() //função que chama a função goToAnswersActivity
//            }
        )
    }

    private fun goToAnswersActivity(talkModel: TalkModel) {
        startActivity(
            Intent(
                this,
                AnswersActivity::class.java
            ).putExtra(AnswersActivity.TALK_MODEL_KEY, talkModel) //talkModel está entrando como Serializable
            //isso pode acontecer porque o TalkModel está extendendo Serializable
            //putExtra é o que passa os parâmetros para a próxima activity
            //TALK_MODEL_KEY fazendo import estático
            //AnswersActivity.TALK_MODEL_KEY ou AnswersActivity.Companion.TALK_MODEL_KEY
        )
    }

}