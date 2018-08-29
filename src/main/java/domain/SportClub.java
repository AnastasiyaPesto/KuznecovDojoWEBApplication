package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sport_club")
public class SportClub {
    @Id
    @Column(name = "sc_id")
    @GeneratedValue
    private int sportClubId;

    @Column
    private String metro;

    @Column(nullable = false)
    private String address;

    @Column
    private String phone;

    @ManyToMany
    @JoinTable(
            name = "instructor_sport_club",
            joinColumns = { @JoinColumn(name = "sc_id") },
            inverseJoinColumns = { @JoinColumn(name = "instr_id") }
    )
    private List<Instructor> instructors = new ArrayList<>();

    public SportClub() {
    }

    public SportClub(String metro, String address, String phone) {
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

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
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
