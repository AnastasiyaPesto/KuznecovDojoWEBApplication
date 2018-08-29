package dao;

import domain.Certificate;
import domain.Instructor;

import javax.persistence.EntityManager;
import java.util.Date;

public class CertificateDAOImpl implements CertificateDAO {
    private final EntityManager entityManager;

    public CertificateDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addTo(Instructor instructor, String numberCert, String degree, Date dateComplete) {
        entityManager.getTransaction().begin();
        // создать сертификат
        Certificate certificate = new Certificate(numberCert, degree, dateComplete);
        // найти инструктора
        Instructor foundInstructor = entityManager.find(Instructor.class, instructor.getInstructorId());
        // добавить к нему сертификат
        if (foundInstructor != null) {
            foundInstructor.setCertificate(certificate);
        } else {
            throw new IllegalArgumentException("Instructor is not found");
        }
        entityManager.getTransaction().commit();
        // если такой сертификат уже есть, то выбросить исключение ?
    }

    @Override
    public Certificate delete(Instructor instructor, String numberCert) {
        return null;
    }
}
