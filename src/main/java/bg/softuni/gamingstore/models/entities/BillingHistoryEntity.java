package bg.softuni.gamingstore.models.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "billing_histories")
public class BillingHistoryEntity extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String country;
    private String town;
    private String userEntity;

    public BillingHistoryEntity() {
    }

    @Column(name = "_user")
    public String getUserEntity() {
        return userEntity;
    }

    public BillingHistoryEntity setUserEntity(String userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public BillingHistoryEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public BillingHistoryEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public BillingHistoryEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @Column(nullable = false)
    public String getAddress() {
        return address;
    }

    public BillingHistoryEntity setAddress(String address) {
        this.address = address;
        return this;
    }

    @Column(nullable = false)
    public String getCountry() {
        return country;
    }

    public BillingHistoryEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    @Column(nullable = false)
    public String getTown() {
        return town;
    }

    public BillingHistoryEntity setTown(String town) {
        this.town = town;
        return this;
    }
}
