package bank.demo.dto.dto;

import bank.demo.dto.enum_class.TypeOfBenefits;

public class Users1 {
    private String firstName;
    private String lastName;
    private int age;
    private TypeOfBenefits typeOfBenefits;
    private int idUser;

    public Users1(String firstName, String lastName, int age, TypeOfBenefits typeOfBenefits) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.typeOfBenefits = typeOfBenefits;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public TypeOfBenefits getTypeOfBenefits() {
        return typeOfBenefits;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTypeOfBenefits(TypeOfBenefits typeOfBenefits) {
        this.typeOfBenefits = typeOfBenefits;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", typeOfBenefits=" + typeOfBenefits +
                ", idUser=" + idUser +
                '}';
    }
}
