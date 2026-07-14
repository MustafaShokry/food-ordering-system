package src.model

public class Customer {
    private final int id;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private static idCounter = 0;

    public Customer(String name, String email, String phoneNumber) {
        id = idCounter++;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public int getName(){
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}