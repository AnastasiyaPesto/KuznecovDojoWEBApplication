package dao;

import domain.Certificate;
import domain.Instructor;

import java.util.ArrayList;
import java.util.Date;

public interface CertificateDAO {
    Certificate addTo(Instructor instructor, String numberCert, int degree, Date dateComplete);
    Certificate deleteFrom(Instructor instructor, String numberCert);
    void delete(int id);
    Certificate create(String number, int degree, Date dateCompleted);
    ArrayList<Certificate> getAll();
}
