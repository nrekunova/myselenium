package com.automation.petclinic.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by alpa on 1/9/20
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Specialty {

    private int id;
    private String name;

    public Specialty() {
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Specialty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
