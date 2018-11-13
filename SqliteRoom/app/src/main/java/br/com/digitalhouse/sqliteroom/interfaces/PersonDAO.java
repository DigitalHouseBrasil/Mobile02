package br.com.digitalhouse.sqliteroom.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.digitalhouse.sqliteroom.model.Person;

@Dao
public interface PersonDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Person person);

    @Update
    public void update(Person person);

    @Query("SELECT * FROM person LIMIT 30")
    public List<Person> getAll();

    @Query("SELECT * FROM person LIMIT 30")
    public LiveData<List<Person>> getAllLiveData();

    @Query("Select * from person where name = :namePerson")
    public Person getByName(String namePerson);

    @Delete
    public void delete(Person person);
}
