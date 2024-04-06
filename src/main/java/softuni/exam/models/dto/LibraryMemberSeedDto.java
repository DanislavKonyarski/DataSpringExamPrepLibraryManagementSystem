package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LibraryMemberSeedDto {


    @Expose
    private String address;
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String phoneNumber;

    @Size(min = 2,max = 40)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Size(min = 2,max = 30)
    @NotNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Size(min =2,max = 30)
    @NotNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Size(min = 2,max = 20)
    @NotNull
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
