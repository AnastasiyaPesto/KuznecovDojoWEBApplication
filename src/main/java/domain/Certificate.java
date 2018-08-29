package domain;

import javax.persistence.*;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
@Table(name = "certificate")
public class Certificate {
    @Id
    @Column(name = "certif_id")
    @GeneratedValue
    private int certificateId;

    @Column(nullable = false, unique = true)
    private String number;

    @Column
    private int degree;

    @Column(name = "date_pass")
    private Date dateCompletion;

    @OneToOne //(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "instr_id")
    private Instructor instructor;

    public Certificate() {
    }

    public Certificate(String number, int degree, Date dateCompletion) {
        this.certificateId = certificateId;
        this.number = number;
        this.degree = degree;
        this.dateCompletion = dateCompletion;
    }

    public int getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(int certificateId) {
        this.certificateId = certificateId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public Date getDateCompletion() {
        return dateCompletion;
    }

    public void setDateCompletion(Date dateCompletion) {
        this.dateCompletion = dateCompletion;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "certificateId = " + certificateId +
                ", number = '" + number + '\'' +
                ", degree = '" + degree + '\'' +
//                ", dateCompletion=" + dateCompletion +
                '}';
    }
}
