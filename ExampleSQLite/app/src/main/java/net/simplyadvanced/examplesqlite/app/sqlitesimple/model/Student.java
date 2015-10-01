package net.simplyadvanced.examplesqlite.app.sqlitesimple.model;

/**
 * Created by Danial on 6/22/2014.
 */
public class Student {

    private int id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
