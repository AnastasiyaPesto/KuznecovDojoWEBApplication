package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Map<Integer, Instructor> instructors = new HashMap<>();

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

    public Map<Integer, Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(Map<Integer, Instructor> instructors) {
        if (instructors == null) throw new IllegalArgumentException("Instructors shouldn't be null");

        if (this.instructors == null) {
            this.instructors = new HashMap<>();
        }
        for (Integer keyId : instructors.keySet()) {
            this.instructors.put(keyId, instructors.get(keyId));
        }
    }

    public void addInstructor(Instructor instructor) {
        if (instructor == null) throw new IllegalArgumentException("Instructor shouldn't be null");
        this.instructors.put(instructor.getInstructorId(), instructor);
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
