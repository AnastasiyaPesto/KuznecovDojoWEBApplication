package dao;

import domain.Certificate;
import domain.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Date;

@Service
public class CertificateDAOImpl implements CertificateDAO {
    private final EntityManager entityManager;

    @Autowired
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
            // либо удалить из мапы и все
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
    public void delete(int id) {
        entityManager.getTransaction().begin();
        try {
            Certificate certificate = entityManager.find(Certificate.class, id);
            entityManager.remove(certificate);
            entityManager.getTransaction().commit();
        } catch (PersistenceException pe) {
            entityManager.getTransaction().rollback();
            throw pe;
        }
    }

    @Override
    public Certificate create(String number, int degree, Date dateCompleted) {
        entityManager.getTransaction().begin();
        Certificate certificate = null;
        try {
            certificate = new Certificate(number, degree, dateCompleted);
            entityManager.persist(certificate);
            entityManager.getTransaction().commit();
        } catch (PersistenceException pe) {
            entityManager.getTransaction().rollback();
            throw pe;
        }
        return certificate;
    }

    @Override
    public ArrayList<Certificate> getAll() {
        ArrayList<Certificate> result;
        entityManager.getTransaction().begin();
        try {
            result = (ArrayList<Certificate>) entityManager
                    .createQuery("SELECT c FROM Certificate c", Certificate.class)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (PersistenceException pe) {
            entityManager.getTransaction().rollback();
            throw pe;
        }
        return result;
    }
}
