package com.example.apkagedaalura.iu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.apkagedaalura.R;
import com.example.apkagedaalura.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosAdapter extends BaseAdapter {

     private final List<Aluno> alunos = new ArrayList<>();//Lista da base de dados
     private final Context context;

     public ListaAlunosAdapter(Context context) {
          this.context = context;
     }

     @Override//Quantidade de elementos da lista
     public int getCount() {
          return alunos.size();
     }

     @Override//o item pego com base na posicao
     public Aluno getItem(int position) {//Sem retornar oject para evitar cast
          return alunos.get(position);
     }

     @Override//Pega o id do elemento selecionado baseado na posicao
     public long getItemId(int position) {//Id do tipo long
          return alunos.get(position).getId();//Quando a classe nao possui ids - return 0;
     }

     @Override//Criando a view de fato, com base no layout(arquivo estatico)
     public View getView(int position, View view, ViewGroup viewGroup) {
          View viewCriada = criaView(viewGroup);
          Aluno alunoDevolvido = alunos.get(position);
          vinculaViewCriadaAlunoDevolvido(viewCriada, alunoDevolvido);
          return viewCriada;
     }

     private View criaView(ViewGroup viewGroup) {
          return LayoutInflater
                  .from(context)//De onde vem o context ->onde vai inflar essa lista
                  .inflate(R.layout.item_aluno, viewGroup, false);
          //viewGroup informa que o layout vai ser filho de Listview//Equivalente ao onCreat
//AttachToRoot//Quem vai inflar como o layout ao pai e o adapter nao essa function(viewGroup == Listview ==p ai  -- R.layout.item_aluno == filha)
     }

     private void vinculaViewCriadaAlunoDevolvido(View viewCriada, Aluno alunoDevolvido) {

          TextView nome = viewCriada.findViewById(R.id.item_aluno_nome);
          TextView telefone = viewCriada.findViewById(R.id.item_aluno_telefone);

          nome.setText(alunoDevolvido.getNome());
          telefone.setText(alunoDevolvido.getTelefone());
     }

     //Isso se faz necessario por conta do encapsulamento

     public void atualiza(List<Aluno> alunos){
          this.alunos.clear();
          this.alunos.addAll(alunos);
          this.notifyDataSetChanged();//Atualiza adapter -> onBind-Sempre informa qual e o adapter(this)
     }

     public void remove(Aluno alunoEscolhido) {
          this.alunos.remove(alunoEscolhido);
          this.notifyDataSetChanged();
     }

//     @Override
//     public void notifyDataSetChanged() {
//          super.notifyDataSetChanged();
//     }

}
