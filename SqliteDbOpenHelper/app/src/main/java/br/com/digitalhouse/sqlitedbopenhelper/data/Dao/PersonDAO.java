package br.com.digitalhouse.sqlitedbopenhelper.data.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.sqlitedbopenhelper.data.database.Database;
import br.com.digitalhouse.sqlitedbopenhelper.model.Person;

public class PersonDAO {

    private SQLiteDatabase db;
    private Context context;

    //Construtor para inicializar o DAO
    public PersonDAO(Context context) {
        // Cria a instancia da base de dados
        Database database = new Database(context);

        // Pega a permissão para escrever na baase de  dados
        db = database.getWritableDatabase();

        // inicializa o contexto que vem da activity
        this.context = context;
    }

    public void insert(Person person) {
        try {
            // Cria um map/dicionario para inserir ios dados
            ContentValues values = new ContentValues();

            // insere os dados no map
            values.put("name", person.getName());
            values.put("profession", person.getProfession());

            // Insere os dados do map na base de dados
            db.insert("person", null, values);

        } catch (Exception e) {
            Log.i("LOG", "error in insert: " + e.getMessage());
        }
    }

    public void update(Person person) {
        try {
            // Cria um map/dicionario para inserir ios dados
            ContentValues values = new ContentValues();

            // insere os dados no map
            values.put("name", person.getName());
            values.put("profession", person.getProfession());

            // Insere os dados atualizados do map na base de dados onde o Id da pessoa for = ao ID passado
            db.update("person", values, "_id = ?", new String[]{"" + person.getId()});

        } catch (Exception e) {
            Log.i("LOG", "error in update: " + e.getMessage());
        }
    }

    public List<Person> getAll() {
        // Declaramos uma lista de pessoas vazia que será retornada no final
        List<Person> list = new ArrayList<Person>();

        // Criamos um array que indicará quais colunas serão trazidas do banco de dados
        String[] columns = {"_id", "name", "profession"};

        // Criamos um cursor para executar o SELECT na base de dados e executamos
        Cursor cursor = db.query("person", columns, null, null, null, null, "_id");

        try {
            // Se o cursor tiver dados
            if (cursor.getCount() > 0) {
                // Se o cursor tiver dados movemos o cursor para a primeira posição dos dados
                cursor.moveToFirst();

                do {
                    // inicialisamos uma pessoa
                    Person person = new Person();

                    //Setamos os dados do usuario buscado no banco, os dados são posicionais(a ordem importa)
                    person.setId(cursor.getLong(0));
                    person.setName(cursor.getString(1));
                    person.setProfession(cursor.getString(2));

                    // Adicionamos o usuario na lista
                    list.add(person);

                    //movemos para a proxima posição do cursor
                } while (cursor.moveToNext());
            }

            //retornamos a lista
            return list;

        } catch (Exception e) {
            Log.i("LOG", "erro in getAll: " + e.getMessage());
            return (list);

        } finally {
            // Fechamos o cursor sempre, para evitar leak de mémoria/crash
            cursor.close();
        }
    }

    public Person getByID(long id) {
        // Declaramos e inicialisamos uma pessoa vazia que será retornada no final
        Person person = new Person();

        // Criamos um array que indicará quais colunas serão trazidas do banco de dados
        String[] columns = {"_id", "name", "profession"};

        String where = "_id = ?";

        // Criamos um cursor para executar o SELECT na base de dados e executamos
        Cursor cursor = db.query("person", columns, where, new String[]{"" + id}, null, null, null);

        try {
            // Se o cursor tiver dados
            if (cursor.getCount() > 0) {
                // Se o cursor tiver dados movemos o cursor para a primeira posição dos dados
                cursor.moveToFirst();

                //Setamos os dados do usuario buscado no banco, os dados são posicionais(a ordem importa)
                person.setId(cursor.getLong(0));
                person.setName(cursor.getString(1));
                person.setProfession(cursor.getString(2));
            }

            // Retornamos a pessos
            return person;

        } catch (Exception e) {
            Log.i("LOG", "erro in getAll: " + e.getMessage());
            return (person);

        } finally {
            // Fechamos o cursor sempre, para evitar leak de mémoria/crash
            cursor.close();
        }
    }

    public Person getByName(String name) {
        // Declaramos e inicialisamos uma pessoa vazia que será retornada no final
        Person person = new Person();

        // Criamos um array que indicará quais colunas serão trazidas do banco de dados
        String[] columns = {"_id", "name", "profession"};

        String where = "name = ?";

        // Criamos um cursor para executar o SELECT na base de dados e executamos
        Cursor cursor = db.query("person", columns, where, new String[]{name}, null, null, null);

        try {
            // Se o cursor tiver dados
            if (cursor.getCount() > 0) {
                // Se o cursor tiver dados movemos o cursor para a primeira posição dos dados
                cursor.moveToFirst();

                //Setamos os dados do usuario buscado no banco, os dados são posicionais(a ordem importa)
                person.setId(cursor.getLong(0));
                person.setName(cursor.getString(1));
                person.setProfession(cursor.getString(2));
            }

            // Retornamos a pessos
            return person;

        } catch (Exception e) {
            Log.i("LOG", "erro in getAll: " + e.getMessage());
            return (person);

        } finally {
            // Fechamos o cursor sempre, para evitar leak de mémoria/crash
            cursor.close();
        }
    }

    public void delete(Person person) {
        try {
            // Deleta a pessoa onde o Id for igual aoi Id da pessao passado
            db.delete("person", "_id = ?", new String[]{"" + person.getId()});
        } catch (Exception e) {
            Log.i("LOG", "error in delete: " + e.getMessage());
        }
    }
}
