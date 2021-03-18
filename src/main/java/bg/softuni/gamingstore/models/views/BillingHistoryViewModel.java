package bg.softuni.gamingstore.models.views;


public class BillingHistoryViewModel {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String country;
    private String town;
    private String user;

    public BillingHistoryViewModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public BillingHistoryViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BillingHistoryViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BillingHistoryViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public BillingHistoryViewModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public BillingHistoryViewModel setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getTown() {
        return town;
    }

    public BillingHistoryViewModel setTown(String town) {
        this.town = town;
        return this;
    }

    public String getUser() {
        return user;
    }

    public BillingHistoryViewModel setUser(String user) {
        this.user = user;
        return this;
    }
}
