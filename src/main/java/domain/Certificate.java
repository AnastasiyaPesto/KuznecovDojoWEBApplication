package domain;

import javax.persistence.*;
import java.util.GregorianCalendar;

@Entity
@Table(name = "certificate")
public class Certificate {
    @Id
    @Column(name = "certif_id")
    private int certificateId;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(length = 7)
    private String degree;

    @Column(name = "date_pass")
    private GregorianCalendar dateCompletion;

    @ManyToOne //(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "instr_id")
    private Instructor instructor;

    public Certificate() {
    }

    public Certificate(int certificateId, String number, String degree, GregorianCalendar dateCompletion) {
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

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public GregorianCalendar getDateCompletion() {
        return dateCompletion;
    }

    public void setDateCompletion(GregorianCalendar dateCompletion) {
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
                "certificateId=" + certificateId +
                ", number='" + number + '\'' +
                ", degree='" + degree + '\'' +
                ", dateCompletion=" + dateCompletion +
                '}';
    }
}
