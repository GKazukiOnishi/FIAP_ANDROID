package br.com.fiap.chatgpt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.fiap.chatgpt.databinding.ActivityChatBinding
import br.com.fiap.chatgpt.databinding.ActivityMainBinding

class ChatActivity : AppCompatActivity() {

    lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButtons()

        //intent todas activities tem a propriedade intent, que é o que tem os dados que ele recebe via intent
        //intent.extras?.getString(Intent.EXTRA_TEXT) //acessa dos conteúdos extras a chave EXTRA_TEXT
            //por isso usamos putExtra Intent.EXTRA_TEXT em baixo
        //intent.extras?.getParcelable<>() // forma de acessar um objeto com mais dados

        //inicializa o texto da edição com o que for compartilhado de outro aplicativo
        binding.textInputEditTextMessage.setText(
            intent.extras?.getString(Intent.EXTRA_TEXT) ?: "O texto enviado é nulo" //operador para substituir o valor null
        )

    }

    fun setupButtons() {
        binding.buttonSendMessage.setOnClickListener {
            binding.messageValue.text = binding.textInputEditTextMessage.text
            clearText()
        }

        binding.buttonShareMessage.setOnClickListener { //forma de simplificar a chamada
            val sendIntent: Intent = Intent().apply { //executa algo em cima do sendIntent
                action = Intent.ACTION_SEND //ação de envio -> usada em Intent implícitas para definir o que ela
                    //faz e o sistema android saber quais aplicativos vai dar para usar, só Actions do tipo
                    //ACTION_SEND podem ser abertas
                type = "text/plain" //tipo do dado que passaremos
                putExtra( //passa o valor para a Activity em chave/valor
                    Intent.EXTRA_TEXT, // exclamação ! no tipo do dado indica que ele pode ser nulo
                    binding.messageValue.text.toString()
                )
            }
//            val sendIntent: Intent = Intent()
//            sendIntent.action = ""

            val shareIntent = Intent.createChooser( //Método vai mudando de acordo com a versão
                //usado para criar Intent de escolha
              sendIntent,
              getString(R.string.chooser_title)
            ) //Kotlin não obriga o tipo quando já é atribuído porque
                //ele consegue pegar o tipo do retorno

            startActivity(shareIntent)
        }
    }

    private fun clearText() {
        binding.textInputEditTextMessage.text?.clear();
    }

}