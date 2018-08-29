package dao;

import domain.Certificate;
import domain.Instructor;

public interface CertificateDAO {
    void addTo(Instructor instructor, Certificate certificate);
    Certificate delete(Instructor instructor, Certificate certificate);
}
