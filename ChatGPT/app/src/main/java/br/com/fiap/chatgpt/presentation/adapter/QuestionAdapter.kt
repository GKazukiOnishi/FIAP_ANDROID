package br.com.fiap.chatgpt.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.chatgpt.R
import br.com.fiap.chatgpt.data.TalkDataSource
import br.com.fiap.chatgpt.data.TalkModel
import br.com.fiap.chatgpt.databinding.ViewQuestionItemBinding

class QuestionAdapter(
    private val talkList: List<TalkModel>,
    private val onCardClick: (TalkModel) -> Unit //Unit porque a função lambda passada retorna nada, se fosse String seria String
                                //os parênteses é como se fosse a assinatura da lambda, só passamos o tipo
) : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    inner class QuestionViewHolder(
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {

        private val binding = ViewQuestionItemBinding.bind(itemView)

        fun bind(item: TalkModel) {
            binding.questionTitle.text = item.question
            binding.root.setOnClickListener {
                onCardClick.invoke(item)
            }
        }
    }

    //ViewGroup -> tem outra view dentro dela, é o caso da activity_questions
    //View -> não tem outras dentro
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(
            LayoutInflater.from( //outra forma de acessar o inflater para montar a tela
                parent.context
            ).inflate(R.layout.view_question_item, parent, false)
        )
    }

    //O param holder é justamente a view criada no método anterior, posição é o elemento da lista
    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        //como ele tem acesso a posição, é nele que preenchemos o dado
        holder.bind(
            talkList[position]
        )
    }

    override fun getItemCount(): Int {
        return talkList.size
    }

}