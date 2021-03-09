package bg.softuni.gamingstore.models.services;

public class BillingHistoryServiceModel {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String country;
    private String town;

    public BillingHistoryServiceModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public BillingHistoryServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BillingHistoryServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BillingHistoryServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public BillingHistoryServiceModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public BillingHistoryServiceModel setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getTown() {
        return town;
    }

    public BillingHistoryServiceModel setTown(String town) {
        this.town = town;
        return this;
    }
}
