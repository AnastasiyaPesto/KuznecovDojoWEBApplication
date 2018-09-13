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
    private final EntityManager em;

    @Autowired
    public CertificateDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Certificate addTo(Instructor instructor, String numberCert, int degree, Date dateComplete) {
        em.getTransaction().begin();
        Certificate certificate = null;
        try {
            // создать сертификат
            certificate = new Certificate(numberCert, degree, dateComplete);
            em.persist(certificate);
            // добавить к нему сертификат
            instructor.addCertificate(certificate);
            em.getTransaction().commit();
        } catch (PersistenceException pe) {
            em.getTransaction().rollback();
            throw pe;
        }
        return certificate;
    }

    @Override
    public Certificate deleteFrom(Instructor instructor, String numberCert) {
        em.getTransaction().begin();
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
            em.getTransaction().commit();
        } catch (PersistenceException pe) {
            em.getTransaction().rollback();
            throw pe;
        }
        return certificate;
    }

    @Override
    public void delete(int id) {
        em.getTransaction().begin();
        try {
            Certificate certificate = em.find(Certificate.class, id);
            em.remove(certificate);
            em.getTransaction().commit();
        } catch (PersistenceException pe) {
            em.getTransaction().rollback();
            throw pe;
        }
    }

    @Override
    public Certificate create(String number, int degree, Date dateCompleted) {
        em.getTransaction().begin();
        Certificate certificate = null;
        try {
            certificate = new Certificate(number, degree, dateCompleted);
            em.persist(certificate);
            em.getTransaction().commit();
        } catch (PersistenceException pe) {
            em.getTransaction().rollback();
            throw pe;
        }
        return certificate;
    }

    @Override
    public ArrayList<Certificate> getAll() {
        ArrayList<Certificate> result;
        em.getTransaction().begin();
        try {
            result = (ArrayList<Certificate>) em
                    .createQuery("SELECT c FROM Certificate c", Certificate.class)
                    .getResultList();
            em.getTransaction().commit();
        } catch (PersistenceException pe) {
            em.getTransaction().rollback();
            throw pe;
        }
        return result;
    }
}
