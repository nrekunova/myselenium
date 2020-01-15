package com.automation.petclinic.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by alpa on 1/9/20
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PetType {

    private int id;
    private String name;

    public PetType() {
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
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
