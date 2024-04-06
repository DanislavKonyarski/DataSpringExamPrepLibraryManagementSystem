package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Library_Members")
public class LibraryMember extends BaseEntity{

    @Column
    private String address;
    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(name = "phone_number",nullable = false,unique = true)
    private String proneNumber;

    public LibraryMember() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public String getProneNumber() {
        return proneNumber;
    }

    public void setProneNumber(String proneNumber) {
        this.proneNumber = proneNumber;
    }
}
