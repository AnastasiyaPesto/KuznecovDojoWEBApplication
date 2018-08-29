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
    public void addTo(Instructor instructor, String numberCert, int degree, Date dateComplete) {
        entityManager.getTransaction().begin();
        // создать сертификат
        Certificate certificate = new Certificate(numberCert, degree, dateComplete);
        entityManager.persist(certificate);
        // найти инструктора
        Instructor foundInstructor = entityManager.find(Instructor.class, instructor.getInstructorId());
        // добавить к нему сертификат
        if (foundInstructor != null) {
            if (foundInstructor.getCertificateMap().containsKey(certificate.getNumber())) {
                throw new IllegalArgumentException("Certificate already exists");
            }
            foundInstructor.addCertificate(certificate);
        } else {
            throw new IllegalArgumentException("Instructor is not found");
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public Certificate delete(Instructor instructor, String numberCert) {
        entityManager.getTransaction().begin();
        // найти инструктора
        Instructor foundInstructor = entityManager.find(Instructor.class, instructor.getInstructorId());
        Certificate certificate = foundInstructor.getCertificateMap().get(numberCert);

        if (certificate == null) {
            throw new IllegalArgumentException("Certificate not found in instructor which id = "
                    + instructor.getInstructorId());
        }

        int rowCountDeleted = entityManager
                .createQuery("DELETE FROM Certificate c WHERE c.number = :number")
                .setParameter("number", numberCert)
                .executeUpdate();

        return certificate;
    }
}
