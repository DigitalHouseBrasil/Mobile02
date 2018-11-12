package br.com.digitalhouse.sqliteroom;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import br.com.digitalhouse.sqliteroom.adapters.RecyclerViewPersonAdapter;
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

        //Configuramos a toolbar
        setSupportActionBar(toolbar);

        // Criamos o adapter para o RecycleView, pssando a lista de pessoas vazia esse momento e  que responde ao clique
        final RecyclerViewPersonAdapter adapter = new RecyclerViewPersonAdapter(personList, this);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public void onItemClick(Person person) {
        textInputLayoutName.getEditText().setText(person.getName());
        textInputLayoutProfession.getEditText().setText(person.getProfession());
    }
}
