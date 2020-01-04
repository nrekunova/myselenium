package com.hillel.ui;

public class Veterinarian {
        private String firstName;
        private String lastName;
        private String type;

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Veterinarian veterinarian = (Veterinarian) o;

        if (firstName != null ? !firstName.equals(veterinarian.firstName) : veterinarian.firstName != null) return false;
        if (lastName != null ? !lastName.equals(veterinarian.lastName) : veterinarian.lastName != null) return false;
        return type != null ? type.equals(veterinarian.type) : veterinarian.type == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Veterinarian{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
