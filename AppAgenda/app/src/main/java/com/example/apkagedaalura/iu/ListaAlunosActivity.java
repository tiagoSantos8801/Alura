package com.example.apkagedaalura.iu;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apkagedaalura.R;
import com.example.apkagedaalura.dao.AlunoDAO;
import com.example.apkagedaalura.iu.activity.FormularioAlunoActivity;
import com.example.apkagedaalura.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.apkagedaalura.iu.activity.ConstantesActivity.CHAVE_ALUNO_ESCOLHIDO;

public class ListaAlunosActivity extends /**Activity*/AppCompatActivity {

     public static final String OPCAO_DE_MENU_DE_CONTEXTO = "Remover";
     AlunoDAO dao = new AlunoDAO();//Pega lista de alunos do formulario
     private ArrayAdapter<Aluno> adapter;

     @Override//Ciclo de vida da activity
     protected void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);//Automatico
          setContentView(R.layout.activity_lista_alunos);//Setando layout Criado - arquivo estatico de layout

          //setTitle("Lista de alunos");//Melhor pelo arquivo xml Strings

          //Pre estipulados
          dao.salvar(new Aluno("Tiago", "+55(88) 9-97767854", "thiagosist8801@gmail.com"));
          dao.salvar(new Aluno("Bruno", "+55(88) 9-88025502", "bsbBruno@gmail.com"));
          dao.salvar(new Aluno("Brenda", "+55(88) 9-8801936", "brendaJade@gmail.com"));

          configuraFABNovoAluno();
          configuraLista();
     }

     @Override//Adciona menus de contexto nas activitys
     public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
          super.onCreateContextMenu(menu, v, menuInfo);
          menu.add(OPCAO_DE_MENU_DE_CONTEXTO);
     }

     @Override//Qualquer menu de contexto que for clicado chamara esta funcao
     public boolean onContextItemSelected(@NonNull MenuItem item) {
          AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
          Aluno alunoEscolhido = adapter.getItem(adapterContextMenuInfo.position);//Posicao do item clicado dentro desse contexto
          adapter.remove(alunoEscolhido);//remove o aluno
          return super.onContextItemSelected(item);
     }

     private void configuraFABNovoAluno() {
          FloatingActionButton floatingActionButton = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
          floatingActionButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                    abreFormularioInsereAluno();
               }
          });
     }

     private void abreFormularioInsereAluno() {
          startActivity(new Intent(this, FormularioAlunoActivity.class));
     }

     @Override//Atualizando a lista de alunos pelo ciclo da activity
     protected void onResume() {
          super.onResume();

          //Atualizando adapter
          atualizandoAlunos();
     }

     private void atualizandoAlunos() {
          adapter.clear();
          adapter.addAll(dao.todos());
     }

     private void configuraLista() {

          ListView listaAlunos = findViewById(R.id.lista_alunos_listView);
          configuraAdapter(listaAlunos);
          ConfiguraListnerDeCliquePorItem(listaAlunos);
          registerForContextMenu(listaAlunos);//Passa a view de menu de contexto

     }

     private void remover(Aluno alunoEscolhido) {
          dao.remove(alunoEscolhido);//Remove do banco
          adapter.remove(alunoEscolhido);//Atualiza o adapter
     }

     private boolean menssagem(String conteudoMenssagem) {
          Toast.makeText(ListaAlunosActivity.this,
                  conteudoMenssagem, Toast.LENGTH_SHORT).show();
          return true;//Somente o longClick
     }

     private void configuraAdapter(ListView listaAlunos) {

          //Intermediario que renderiza os itens do listView Adapter(ArrayAdapter)
          adapter = new ArrayAdapter<>(this,
                  android.R.layout.simple_list_item_1);
          listaAlunos.setAdapter(adapter);
     }

     private void ConfiguraListnerDeCliquePorItem(ListView listaAlunos) {
          listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(position);//Aluno escolhido
                    abreFormularioEditaAluno(alunoEscolhido);
               }
          });
     }

     private void abreFormularioEditaAluno(Aluno alunoEscolhido) {

          Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
          vaiParaFormularioActivity.putExtra(CHAVE_ALUNO_ESCOLHIDO, alunoEscolhido);
          startActivity(vaiParaFormularioActivity);
     }
}
//######################################################################################################
//   Evento de click longo direto na lista, nao no contexto de click
//     private void ConfiguraListnerDeCliqueLongoPorItem(ListView listaAlunos) {
//          listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//               @Override
//               public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
//                    Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(position);//Retorna o obj naquela opcao
//                    remover(alunoEscolhido);
//
//                    //onResume(); - Processmento de varias coisas desnecessarias
//                    //configuraLista() - Nao recarrega de fato - meramente visual!
//
//                    return menssagem("Item excuido com sucesso");
//               }
//          });
//     }
//####################################################################################################
//   Criando lista na mao
//          List<String> alunos = new ArrayList<>(Arrays.asList(
//                                                  "Tiago", "Bruno","Brenda","Fernando","Cinha",
//                                                  "Enmerson", "Sami", "Samuel","Jonas","Agra"
//                                                ));

//####################################################################################################
//Colocando a lista de views na mao
//          TextView primeiroAluno = findViewById(R.id.textView);
//          TextView segundoAluno  = findViewById(R.id.textView2);
//          TextView terceiroAluno = findViewById(R.id.textView3);
//
//          primeiroAluno.setText(alunos.get(0));
//          segundoAluno.setText(alunos.get(1));
//          terceiroAluno.setText(alunos.get(2));

//####################################################################################################
//Toast.makeText(this, "Oi", Toast.LENGTH_SHORT).show();

//Criando TextView direto do main - Nao e uma boa pratica
//          TextView aluno = new TextView(this);
//          aluno.setText("Thiago Santos");
//          setContentView(aluno);
