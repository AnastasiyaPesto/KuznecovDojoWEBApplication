package web.sportclubs.add;

import javax.validation.constraints.Size;

public class SportClubsAddFormBean {
    @Size(min = 1)
    private String metro;

    @Size(min = 1)
    private String address;

    @Size(min = 1)
    private String phone;

    public String getMetro() {
        return metro;
    }

    public void setMetro(String metro) {
        this.metro = metro;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
