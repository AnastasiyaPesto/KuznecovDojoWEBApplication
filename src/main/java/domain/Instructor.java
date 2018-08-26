package domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @Column(name = "instr_id")
    private int instructorId;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "second_name", length = 50)
    private String secondName;

    @Column
    private byte age;

    @Column(length = 15)
    private String phone;

    @OneToMany(mappedBy = "instructor")
    private Set<Certificate> certificateSet;

    public Instructor() {
    }

    public Instructor(int instructorId, String firstName, String secondName, byte age, String phone) {
        this.instructorId = instructorId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.phone = phone;
    }

    public Set<Certificate> getCertificateSet() {
        return certificateSet;
    }

    public void setCertificateSet(Set<Certificate> certificateSet) {
        if (certificateSet == null) throw new IllegalArgumentException("Certificates shouldn't be null");
        this.certificateSet = certificateSet;
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

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "instructorId = " + instructorId +
                ", firstName = '" + firstName + '\'' +
                ", secondName = '" + secondName + '\'' +
                ", age = " + age +
                ", phone = '" + phone + '\'' +
                '}';
    }

}
