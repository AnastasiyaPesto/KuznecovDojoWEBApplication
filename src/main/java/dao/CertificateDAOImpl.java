package dao;

import domain.Certificate;
import domain.Instructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.Date;

public class CertificateDAOImpl implements CertificateDAO {
    private final EntityManager entityManager;

    public CertificateDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Certificate addTo(Instructor instructor, String numberCert, int degree, Date dateComplete) {
        entityManager.getTransaction().begin();
        Certificate certificate = null;
        try {
            // создать сертификат
            certificate = new Certificate(numberCert, degree, dateComplete);
            entityManager.persist(certificate);
            // найти инструктора
            Instructor foundInstructor = entityManager.find(Instructor.class, instructor.getInstructorId());
            // добавить к нему сертификат
            instructor.addCertificate(certificate);
            entityManager.getTransaction().commit();
        } catch (PersistenceException pe) {
            entityManager.getTransaction().rollback();
            throw pe;
        }
        return certificate;
    }

    @Override
    public Certificate deleteFrom(Instructor instructor, String numberCert) {
        entityManager.getTransaction().begin();
        Certificate certificate = null;
        try {
            certificate = instructor.getCertificateMap().get(numberCert);

            if (certificate == null) {
                throw new IllegalArgumentException("Certificate not found in instructor which id = "
                        + instructor.getInstructorId());
            }
            // entityManager.remove(certificate);
            // todo либо удалить из мапы и все
            if (instructor.getCertificateMap().remove(numberCert) == null) {
                certificate = null;
            }
            entityManager.getTransaction().commit();
        } catch (PersistenceException pe) {
            entityManager.getTransaction().rollback();
            throw pe;
        }
        return certificate;
    }

    @Override
    public void delete(Certificate certificate) {
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(certificate);
            entityManager.getTransaction().commit();
        } catch (PersistenceException pe) {
            entityManager.getTransaction().rollback();
            throw pe;
        }
    }
}
