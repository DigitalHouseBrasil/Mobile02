package br.com.digitalhouse.sqliteroom;

import android.arch.lifecycle.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import br.com.digitalhouse.sqliteroom.adapters.RecyclerViewPersonAdapter;
import br.com.digitalhouse.sqliteroom.data.database.DatabaseRoom;
import br.com.digitalhouse.sqliteroom.interfaces.PersonDAO;
import br.com.digitalhouse.sqliteroom.interfaces.RecyclerViewOnItemClickListener;
import br.com.digitalhouse.sqliteroom.model.Person;

import java.util.ArrayList;
import java.util.List;

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
        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fabAddPerson);
        recyclerView = findViewById(R.id.recycleView);
        textInputLayoutName = findViewById(R.id.textInputLayoutName);
        textInputLayoutProfession = findViewById(R.id.textInputLayoutProfession);
        imageViewDelete = findViewById(R.id.imageViewDelete);
        personDAO = DatabaseRoom.getDatabase(this).personDAO();

        //Configuramos a toolbar
        setSupportActionBar(toolbar);

        // Criamos o adapter para o RecycleView, pssando a lista de pessoas vazia esse momento e  que responde ao clique
        final RecyclerViewPersonAdapter adapter = new RecyclerViewPersonAdapter(personList, this);

        // Criamos o layoutmanager para o RecycleView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        //Setamos o layoutManager no recyclerView
        recyclerView.setLayoutManager(layoutManager);

        //setamos o adapter no recyclerView
        recyclerView.setAdapter(adapter);
        getAlldataFromDataBase(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pegamos os dados que o usuario digitou
                final String name = textInputLayoutName.getEditText().getText().toString();
                final String profession = textInputLayoutProfession.getEditText().getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Person person = personDAO.getByName(name);

                        // Se o id maior que zero, a pessoa existe então atualizamos
                        if (person != null) {

                            person.setName(name);
                            person.setProfession(profession);

                            personDAO.update(person);

                            showToastAlert("Pessoa: " + name + " atualizado");
                        } else {
                            person = new Person();
                            person.setName(name);
                            person.setProfession(profession);
                            personDAO.insert(person);
                            showToastAlert("Pessoa: " + name + " inserido");
                        }
                    }
                }).start();
            }

        });

        imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = textInputLayoutName.getEditText().getText().toString();


                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        final Person person = personDAO.getByName(name);

                        if (person != null) {
                            personDAO.delete(person);
                            showToastAlert("Pessoa: " + name + " deletado");
                        } else {
                            showToastAlert("Pessoa: " + name + " não existe na base de dados");
                        }
                    }
                }).start();
            }
        });

    }

    public void showToastAlert(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAlldataFromDataBase(final RecyclerViewPersonAdapter adapter) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                personDAO.getAllLiveData().observe(MainActivity.this, new Observer<List<Person>>() {
                    @Override
                    public void onChanged(@Nullable final List<Person> personList) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.update(personList);
                            }
                        });
                    }
                });

            }
        }).start();
    }

    @Override
    public void onItemClick(Person person) {
        textInputLayoutName.getEditText().setText(person.getName());
        textInputLayoutProfession.getEditText().setText(person.getProfession());
    }
}
