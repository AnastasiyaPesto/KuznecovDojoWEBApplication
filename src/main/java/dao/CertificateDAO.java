package dao;

import domain.Certificate;
import domain.Instructor;

import java.util.Date;

public interface CertificateDAO {
    void addTo(Instructor instructor, String numberCert, int degree, Date dateComplete);
    Certificate delete(Instructor instructor, String numberCert);
}
