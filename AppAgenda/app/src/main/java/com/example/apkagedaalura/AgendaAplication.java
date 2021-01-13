package com.example.apkagedaalura;

import android.app.Application;

import com.example.apkagedaalura.dao.AlunoDAO;
import com.example.apkagedaalura.model.Aluno;

public class AgendaAplication extends Application {//Tem que ir ao manifest dar o name!!!

     @Override
     public void onCreate() {
          super.onCreate();
          //onCreat primeiro do que todas as activitys
          //Nao fica mais sujeito ao ciclo de vida da activity
          criaAlunosDeTeste();

//          try {//Nao colocar nada pesado pra rodar aqui - impacta na performace
//               Thread.sleep(2000);
//          } catch (InterruptedException e) {
//               e.printStackTrace();
//          }
     }

     private void criaAlunosDeTeste() {
          AlunoDAO dao = new AlunoDAO();

          //Pre estipulados
          dao.salvar(new Aluno("Tiago", "+55(88) 9-97767854", "thiagosist8801@gmail.com"));
          dao.salvar(new Aluno("Bruno", "+55(88) 9-88025502", "bsbBruno@gmail.com"));
          dao.salvar(new Aluno("Brenda", "+55(88) 9-8801936", "brendaJade@gmail.com"));
     }
}
