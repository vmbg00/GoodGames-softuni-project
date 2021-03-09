package bg.softuni.gamingstore.models.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class BillingHistoryBindingModel {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String country;
    private String town;

    public BillingHistoryBindingModel() {
    }


    @Size(min = 4, message = "First name must be at least 4 characters")
    public String getFirstName() {
        return firstName;
    }

    public BillingHistoryBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Size(min = 4, message = "Last name must be at least 4 characters")
    public String getLastName() {
        return lastName;
    }

    public BillingHistoryBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public BillingHistoryBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    @Size(min = 5, message = "Address must be at least 5 characters long")
    public String getAddress() {
        return address;
    }

    public BillingHistoryBindingModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public BillingHistoryBindingModel setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getTown() {
        return town;
    }

    public BillingHistoryBindingModel setTown(String town) {
        this.town = town;
        return this;
    }
}
