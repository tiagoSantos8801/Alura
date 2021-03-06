package com.example.apkagedaalura.iu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apkagedaalura.R;
import com.example.apkagedaalura.dao.AlunoDAO;
import com.example.apkagedaalura.model.Aluno;

import static com.example.apkagedaalura.iu.activity.ConstantesActivity.CHAVE_ALUNO_ESCOLHIDO;

public class FormularioAlunoActivity extends AppCompatActivity {

     public static final String TITULO_APPBAR_NOVO_ALUNO = "Novo aluno";
     private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita aluno";
     private EditText campoNome, campoEmail, campoTelefone;
     final AlunoDAO dao = new AlunoDAO();
     private Aluno aluno;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_formulario_aluno);

          inicializarCampos();
          carregaAluno();
     }

     @Override//Criando menu de opcoes
     public boolean onCreateOptionsMenu(Menu menu) {
          getMenuInflater().inflate(R.menu.actvity_formulario_aluno_menu, menu);
          return super.onCreateOptionsMenu(menu);
     }

     @Override
     public boolean onOptionsItemSelected(@NonNull MenuItem item) {

          int itemId = item.getItemId();
          if (itemId == R.id.activity_formulario_aluno_menu_salvar){
               finalizaFormulario();
          }

          return super.onOptionsItemSelected(item);
     }

     private void carregaAluno() {

          Intent dadosPut = getIntent();
          if (dadosPut.hasExtra(CHAVE_ALUNO_ESCOLHIDO)) {
               setTitle(TITULO_APPBAR_EDITA_ALUNO);

               //Cast de Serializable para Aluno
               aluno = (Aluno) dadosPut.getSerializableExtra(CHAVE_ALUNO_ESCOLHIDO);
               preencheCampos();
          } else {
               setTitle(TITULO_APPBAR_NOVO_ALUNO);
               aluno = new Aluno();
          }
     }

     private void preencheCampos() {
          campoNome.setText(aluno.getNome());
          campoTelefone.setText(aluno.getTelefone());
          campoEmail.setText(aluno.getEmail());
     }

     private void finalizaFormulario() {
          preencheAluno();
          if (aluno.temIdValido()) {
               dao.editar(aluno);
          } else {
               dao.salvar(aluno);
          }
          finish();
     }

     private void inicializarCampos() {
          campoNome = findViewById(R.id.activity_formulario_aluno_nome);
          campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
          campoEmail = findViewById(R.id.activity_formulario_aluno_email);
     }

     private void preencheAluno() {

          String nome = campoNome.getText().toString();
          String telefone = campoTelefone.getText().toString();
          String email = campoEmail.getText().toString();

          //Setando na model
          aluno.setNome(nome);
          aluno.setTelefone(telefone);
          aluno.setEmail(email);
     }
}

//######################################################################################################
//     private void configuraBotaoSalvar() {
//          buttonSalvar.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View view) {
//                    Aluno alunoCriado = preencheAluno();
//                    salvar(alunoCriado);
//
//                    finalizaFormulario();
//               }
//          });
//     }

//######################################################################################################
//     private void salvar(Aluno alunoCriado) {
//          dao.salvar(alunoCriado);
//          //startActivity(new Intent(FormularioAlunoActivity.this, ListaAlunosActivity.class));
//          finish();
//     }