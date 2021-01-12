package com.example.apkagedaalura.dao;

import com.example.apkagedaalura.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

     //A lista sera um "mini banco de dados"
     private static final List<Aluno> alunos = new ArrayList<>();
     private static int contadorIds = 1;

     //Adciona a lista de Alunos
     public void salvar(Aluno alunoCriado) {

          alunoCriado.setId(contadorIds);//Ids para obj passados
          alunos.add(alunoCriado);//Coloca aluno na lista
          atualizaIds();
     }

     private void atualizaIds() {
          contadorIds++;
     }

     public void editar(Aluno aluno) {
          Aluno alunoEncontrado = buscaAlunoPeloId(aluno);
          if (alunoEncontrado != null) {
               int posicaoAluno = alunos.indexOf(alunoEncontrado);//Pega index do OBJ
               alunos.set(posicaoAluno, aluno);//Seta na posicao tal o OBJ tal
          }
     }

     private Aluno buscaAlunoPeloId(Aluno aluno) {
          Aluno alunoEncontrado = null;
          for (Aluno a :
                  alunos) {
               if (aluno.getId() == a.getId()) {
                     return a;
               }
          }
          return alunoEncontrado;//Retorna null
     }

     //Retorna um array vazio mais o outro array passado == array passado -> Copia do "banco"
     public List<Aluno> todos() {
          return new ArrayList<>(alunos);//Copia do "Banco"
     }

     public void remove(Aluno alunoEscolhido) {

          Aluno alunoDevolvido = buscaAlunoPeloId(alunoEscolhido);
          if (alunoDevolvido != null)
               alunos.remove(alunoDevolvido);
     }
}
