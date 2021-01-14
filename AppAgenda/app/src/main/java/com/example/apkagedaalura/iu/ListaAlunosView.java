package com.example.apkagedaalura.iu;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;

import com.example.apkagedaalura.dao.AlunoDAO;
import com.example.apkagedaalura.iu.adapter.ListaAlunosAdapter;
import com.example.apkagedaalura.model.Aluno;

public class ListaAlunosView {

     private final AlunoDAO dao;//Pega lista de alunos do formulario
     private Context context;
     public ListaAlunosAdapter adapter;

     public ListaAlunosView(Context context) {
          this.context = context;
          this.adapter = new ListaAlunosAdapter(this.context);//Adapter personalizado
          this.dao = new AlunoDAO();
     }

     public void confirmaRemocao(Aluno alunoEscolhido) {
          new AlertDialog.Builder(context)
                  .setTitle("Removendo aluno")
                  .setMessage("Tem certeza que deseja remover o aluno ?")
                  .setCancelable(false)
                  .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                            remover(alunoEscolhido);//remove o aluno
                       }
                  })
                  .setNegativeButton("Não", null)
                  .show();//O builder so cria, temos que mostrar tambem
     }

     public void atualizandoAlunos() {
          adapter.atualiza(dao.todos());
     }

     private void remover(Aluno alunoEscolhido) {
          dao.remove(alunoEscolhido);//Remove do banco
          adapter.remove(alunoEscolhido);//Atualiza o adapter
     }

     public void configuraAdapter(ListView listaAlunos) {
          listaAlunos.setAdapter(adapter);
     }
}
