package br.com.digitalhouse.sqliteroom.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "person")
public class Person {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "profession")
    private String profession;

    @ColumnInfo(name = "nicks")
    private List<String> nickNames;

    @ColumnInfo(name = "comments")
    private List<Comment> coments;

    public Person() {
    }

    public Person(long id, String name, String profession, List<String> nickNames, List<Comment> coments) {
        this.id = id;
        this.name = name;
        this.profession = profession;
        this.nickNames = nickNames;
        this.coments = coments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public List<String> getNickNames() {
        return nickNames;
    }

    public void setNickNames(List<String> nickNames) {
        this.nickNames = nickNames;
    }

    public List<Comment> getComents() {
        return coments;
    }

    public void setComents(List<Comment> coments) {
        this.coments = coments;
    }
}
