package br.com.digitalhouse.sqlitedbopenhelper;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.sqlitedbopenhelper.data.Dao.PersonDAO;
import br.com.digitalhouse.sqlitedbopenhelper.data.adapters.RecyclerViewPersonAdapter;
import br.com.digitalhouse.sqlitedbopenhelper.data.interfaces.RecyclerViewOnItemClickListener;
import br.com.digitalhouse.sqlitedbopenhelper.model.Person;

public class MainActivity extends AppCompatActivity implements RecyclerViewOnItemClickListener {

    private PersonDAO personDAO;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutProfession;
    private ImageView imageViewDelete;
    private List<Person> personList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializamos as Views
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fabAddPerson);
        recyclerView = findViewById(R.id.recycleView);
        textInputLayoutName = findViewById(R.id.textInputLayoutName);
        textInputLayoutProfession = findViewById(R.id.textInputLayoutProfession);
        imageViewDelete = findViewById(R.id.imageViewDelete);

        //Configuramos a toolbar
        setSupportActionBar(toolbar);

        // Inivcializamos o DAO para podermos buscar os dados
        personDAO = new PersonDAO(this);

        // Criamos o adapter para o RecycleView, pssando a lista de pessoas vazia esse momento e  que responde ao clique
        final RecyclerViewPersonAdapter adapter = new RecyclerViewPersonAdapter(personList, this);

        // Criamos o layoutmanager para o RecycleView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        //Setamos o layoutManager no recyclerView
        recyclerView.setLayoutManager(layoutManager);

        //setamos o adapter no recyclerView
        recyclerView.setAdapter(adapter);

        // atualizamos a lista de pessoas do adapter do recyclerView
        adapter.update(personDAO.getAll());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pegamos os dados que o usuario digitou
                String name = textInputLayoutName.getEditText().getText().toString();
                String profession = textInputLayoutProfession.getEditText().getText().toString();

                // Fazemos uma busca por  uma pessoa pelo nome, e colocamos os dados qu eo usuario digitou
                Person newPerson = personDAO.getByName(name);
                newPerson.setName(name);
                newPerson.setProfession(profession);

                // Se o ID for maior qu ezero é por que a pessoa já exite então vamos apenas atualizar os dados no banco de dados
                if (newPerson.getId() > 0) {
                    personDAO.update(newPerson);
                    Toast.makeText(MainActivity.this, "Pessoa: " + name + " atualizada", Toast.LENGTH_SHORT).show();
                } else {
                    // Se o id for menor ou igual a zero, essa é uma nova pessoa e iremos inserir no banco de dados
                    personDAO.insert(newPerson);
                    Toast.makeText(MainActivity.this, "Pessoa: " + name + " inserida", Toast.LENGTH_SHORT).show();
                }

                // Apos inserir a pessoa atualizamos a lista do adapter para mostrarmos a nova pessoa
                adapter.update(personDAO.getAll());
            }
        });


        //Clique na image para deletar
        imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = textInputLayoutName.getEditText().getText().toString();
                Person person = personDAO.getByName(name);

                // Se o id maior que zero, a pessoa existe então deletamos
                if (person.getId() > 0) {
                    personDAO.delete(person);

                    // Atualizamos a lista do adapter
                    adapter.update(personDAO.getAll());
                    Toast.makeText(MainActivity.this, "Pessoa: " + name + " deletado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Pessoa: " + name + " não existe na base de dados", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemClick(Person person) {
        textInputLayoutName.getEditText().setText(person.getName());
        textInputLayoutProfession.getEditText().setText(person.getProfession());
    }
}
