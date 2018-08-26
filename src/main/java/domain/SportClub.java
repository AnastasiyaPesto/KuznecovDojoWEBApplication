package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sport_club")
public class SportClub {
    @Id
    @Column(name = "sc_id")
    private int sportClubId;

    @Column
    private String metro;

    @Column(nullable = false)
    private String address;

    @Column
    private String phone;

    public SportClub() {
    }

    public SportClub(int sportClubId, String metro, String address, String phone) {
        this.sportClubId = sportClubId;
        this.metro = metro;
        this.address = address;
        this.phone = phone;
    }

    public int getSportClubId() {
        return sportClubId;
    }

    public void setSportClubId(int sportClubId) {
        this.sportClubId = sportClubId;
    }

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

    @Override
    public String toString() {
        return "SportClub{" +
                "sportClubId=" + sportClubId +
                ", metro='" + metro + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}