package domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    private List<Certificate> certificates;

    @ManyToMany(mappedBy = "instructors")
    private List<SportClub> sportClubs;

    public Instructor() {
    }

    public Instructor(String firstName, String secondName, int age, String phone) {
        this.instructorId = instructorId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.phone = phone;
    }

    public List<Certificate> getCertificateSet() {
        return certificates;
    }

    public void setCertificateSet(List<Certificate> certificates) {
        if (certificates == null) throw new IllegalArgumentException("Certificates shouldn't be null");
        this.certificates = certificates;
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

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    public List<SportClub> getSportClubs() {
        return sportClubs;
    }

    public void setSportClubs(List<SportClub> sportClubs) {
        this.sportClubs = sportClubs;
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
