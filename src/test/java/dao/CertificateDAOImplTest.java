package dao;

import domain.Certificate;
import domain.Instructor;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class CertificateDAOImplTest {
    private EntityManagerFactory emf;
    private EntityManager em;
    private CertificateDAO dao;

    @Before
    public void setup() {
        emf = Persistence.createEntityManagerFactory("TestKuznecovDojoPersistenceUnit");
        em = emf.createEntityManager();
        dao = new CertificateDAOImpl(em);
    }

    @After
    public void after() {
        if (em != null) {
            em.close();
        }

        if (emf != null) {
            emf.close();
        }
    }

    @Test
    public void testAddTo() {
        em.getTransaction().begin();
        Instructor instructor = null;
        try {
            instructor = new Instructor("Иванов", "Иван", 5,"1111");
            em.persist(instructor);
            em.getTransaction().commit();
        } catch (PersistenceException pe) {
            em.getTransaction().rollback();
            throw pe;
        }

        String number = "KD_001";
        int degree = 5;
        Date date = null;
        try {
            date = new SimpleDateFormat("dd.MM.YYYY").parse("01.01.2018");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dao.addTo(instructor, number, degree, date);
        assertEquals(1, instructor.getCertificateMap().size());
    }

    @Test
    public void testDeleteFrom() {
        em.getTransaction().begin();
        Instructor instructor = null;
        try {
            instructor = new Instructor("Иванов", "Иван", 5,"1111");
            em.persist(instructor);
            em.getTransaction().commit();
        } catch (PersistenceException pe) {
            em.getTransaction().rollback();
            throw pe;
        }

        String number = "KD_001";
        int degree = 5;
        Date date = null;
        try {
            date = new SimpleDateFormat("dd.MM.YYYY").parse("01.01.2018");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dao.addTo(instructor, number, degree, date);
        assertEquals(1, instructor.getCertificateMap().size());
        dao.deleteFrom(instructor, number);
        assertEquals(0, instructor.getCertificateMap().size());
    }

    @Test
    public void testGetAll() {
        Date date1 = null;
        Date date2 = null;
        Date date3 = null;
        try {
            date1 = new SimpleDateFormat("dd.MM.YYYY").parse("01.01.2018");
            date2 = new SimpleDateFormat("dd.MM.YYYY").parse("06.06.2006");
            date3 = new SimpleDateFormat("dd.MM.YYYY").parse("25.09.1992");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dao.create("kd_1111", 1, date1);
        dao.create("ER_394", -2, date2);
        dao.create("FFF_000", 6, date3);

        ArrayList<Certificate> all = dao.getAll();

        assertEquals(3, all.size());
    }


    @Test
    public void testDelete() {
        String number = "KD_001";
        int degree = 5;
        Date date = null;
        try {
            date = new SimpleDateFormat("dd.MM.YYYY").parse("01.01.2018");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Certificate createdCertificate = dao.create(number, degree, date);
        dao.delete(createdCertificate.getCertificateId());

        em.getTransaction().begin();

        try {
            assertNull(em.find(Certificate.class, createdCertificate.getCertificateId()));
            em.getTransaction().commit();
        } catch (PersistenceException pe) {
            em.getTransaction().rollback();
            throw pe;
        }
    }

    @Test
    public void testCreate() {
        String number = "KD_001";
        int degree = 5;
        Date date = null;
        try {
            date = new SimpleDateFormat("dd.MM.YYYY").parse("01.01.2018");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Certificate createdCertificate = dao.create(number, degree, date);

        assertEquals(number, createdCertificate.getNumber());
        assertEquals(degree, createdCertificate.getDegree());
        assertEquals(date, createdCertificate.getDateCompletion());
    }
}