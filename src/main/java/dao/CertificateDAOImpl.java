package dao;

import domain.Certificate;
import domain.Instructor;

import javax.persistence.EntityManager;

public class CertificateDAOImpl implements CertificateDAO {
    private final EntityManager entityManager;

    public CertificateDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addTo(Instructor instructor, Certificate certificate) {
        // найти инструктора
        entityManager.getTransaction().begin();
        Instructor foundInstructor = entityManager.find(Instructor.class, instructor.getInstructorId());
        // добавить к нему сертификат
        if (foundInstructor != null && certificate != null) {
            foundInstructor.getCertificateSet().add(certificate);
        }
        entityManager.getTransaction().commit();
        // если такой сертификат уже есть, то выбросить исключение ?
    }

    @Override
    public Certificate delete(Instructor instructor, Certificate certificate) {
        return null;
    }
}
