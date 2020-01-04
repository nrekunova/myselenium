package com.hillel.ui;

import java.util.Objects;

public class Owner {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;
    private String pets;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPets() {
        return pets;
    }

    public void setPets(String pets) {
        this.pets = pets;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(firstName, owner.firstName) &&
                Objects.equals(lastName, owner.lastName) &&
                Objects.equals(address, owner.address) &&
                Objects.equals(city, owner.city) &&
                Objects.equals(telephone, owner.telephone) &&
                Objects.equals(pets, owner.pets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, address, city, telephone, pets);
    }
}

