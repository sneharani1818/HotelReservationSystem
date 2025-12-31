package model;

public class Customer {
    final private String firstName;
    final private String lastName;
    final private String email;

    public Customer(String firstName, String lastName, String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format. Expected format: name@domain.extension");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    private boolean isValidEmail(String email) {
        //regex to check valid emailId
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email != null && email.matches(emailRegex);
    }

    @Override
    public String toString() {
        return "Customer details:\n" +
                "First Name='" + firstName + '\'' +
                ", Last Name='" + lastName + '\'' +
                ", Email='" + email + '\'';
    }
}
