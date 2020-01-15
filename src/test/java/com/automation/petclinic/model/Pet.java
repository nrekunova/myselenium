package com.automation.petclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

/**
 * Created by alpa on 1/9/20
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pet {

    private int id;
    private String name;
    private String birthDate;
    private PetType type;
    private Owner owner;

    @JsonIgnore
    private List<Object> visits;


//        "id": 1,
//            "name": "Leo",
//            "birthDate": "2010/09/07",
//            "type": {
//        "id": 1,
//                "name": "cat"
//    },
//        "owner": {
//        "id": 1,
//                "firstName": "George",
//                "lastName": "Franklin",
//                "address": "110 W. Liberty St.",
//                "city": "Madison",
//                "telephone": "6085551023"
//    },
//        "visits": []

    public Pet() {
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Object> getVisits() {
        return visits;
    }

    public void setVisits(List<Object> visits) {
        this.visits = visits;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", type=" + type +
                ", owner=" + owner +
                ", visits=" + visits +
                '}';
    }
}
