package domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @Column(name = "instr_id")
    @GeneratedValue
    private int instructorId;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "second_name", length = 50)
    private String secondName;

    @Column
    private int age;

    @Column(length = 15)
    private String phone;

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY)
    private Map<String, Certificate> certificateMap = new HashMap<>();

    @ManyToMany(mappedBy = "instructors")
    private Map<Integer, SportClub> sportClubs;

    public Instructor() {
    }

    public Instructor(String firstName, String secondName, int age, String phone) {
        this.instructorId = instructorId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.phone = phone;
    }

    public Map<String, Certificate> getCertificateMap() {
        return certificateMap;
    }

    public void setCertificates(Map<String, Certificate> certificates) {
        if (certificates == null) throw new IllegalArgumentException("Certificates shouldn't be null");
        for (String keyNumber : certificates.keySet()) {
            this.certificateMap.put(keyNumber, certificates.get(keyNumber));
        }
    }

    public void addCertificate(Certificate certificate) {
        if (certificate == null) throw new IllegalArgumentException("Certificate shouldn't be null");
        if (this.certificateMap.containsKey(certificate.getNumber())) {
            throw new IllegalArgumentException("Certificate with number " + certificate.getNumber()
                    + " already exists");
        }
        this.certificateMap.put(certificate.getNumber(), certificate);
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Map<Integer, SportClub> getSportClubs() {
        return sportClubs;
    }

    public void setSportClubs(Map<Integer, SportClub> sportClubs) {
        if (sportClubs == null) throw new IllegalArgumentException("Sport clubs shouldn't be null");
        for (Integer keySportClubId : sportClubs.keySet()) {
            this.sportClubs.put(keySportClubId, sportClubs.get(keySportClubId));
        }
    }

    public void addSportClub(SportClub sportClub) {
        if (sportClub == null) throw new IllegalArgumentException("Sport club shouldn't be null");
        if (this.sportClubs == null) {
            this.sportClubs = new HashMap<>();
        }
        if (sportClubs.containsKey(sportClub.getSportClubId())) {
            throw new IllegalArgumentException("The this instructor is already coaching at the sport club");
        }
        this.sportClubs.put(sportClub.getSportClubId(), sportClub);
    }

    public boolean deleteSportClub(SportClub sportClub) {
        return (sportClubs.remove(sportClub.getSportClubId()) != null);
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "instructorId = " + instructorId +
                ", firstName = '" + firstName + '\'' +
                ", secondName = '" + secondName + '\'' +
                ", age = " + age +
                ", phone = '" + phone + '\''
                 + '}';
    }

}
