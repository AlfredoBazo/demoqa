package utils;

public class Person {
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // Constructor
    public Person(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Print person data
    void printPerson() {

        System.out.println("Name: " + lastName + ", " + firstName);
    }
}
