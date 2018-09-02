package dao;

import domain.Instructor;
import domain.SportClub;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import static org.junit.Assert.*;

public class SportClubDAOImplTest {
    private EntityManagerFactory emf;
    private EntityManager em;
    private SportClubDAO dao;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("TestKuznecovDojoPersistenceUnit");
        em = emf.createEntityManager();
        dao = new SportClubDAOImpl(em);
    }

    @After
    public void tearDown() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    @Test
    public void testCreate() {
        String metro = "Лесная";
        String address = "ул. Антонова, 11";
        String phone = "8888";
        SportClub sportClub = dao.create(metro, address, phone);

        assertEquals(metro, sportClub.getMetro());
        assertEquals(address, sportClub.getAddress());
        assertEquals(phone, sportClub.getPhone());

        assertNotEquals(0, sportClub.getSportClubId());
    }

    @Test
    public void testAddInstructor() {
        SportClub createdSportClub = dao.create("Лесная", "ул. Антонова, 11", "8888");
        dao.addInstructor(createdSportClub, createAndPersistInstructor());

        assertEquals(1, createdSportClub.getInstructors().size());
    }

    @Test
    public void testDeleteInstructorFrom() {
        // Создали в базе клбу и инструктора
        SportClub createdSportClub = dao.create("Лесная", "ул. Антонова, 11", "8888");
        Instructor instructor = createAndPersistInstructor();

        // В поле с клубами инструктору добавили текущий спортивный клуб
        instructor.addSportClub(createdSportClub);
        // В спортивный клуб добавили инструктора
        dao.addInstructor(createdSportClub, instructor);
        dao.deleteInstructorFrom(createdSportClub, instructor);
        assertEquals(0, createdSportClub.getInstructors().size());
    }

    // todo как проверить через Assert
    @Test
    public void testDelete() {
        String metro = "Лесная";
        String address = "ул. Антонова, 11";
        String phone = "8888";
        SportClub sportClub = dao.create(metro, address, phone);

        dao.delete(sportClub.getSportClubId());
    }

    private Instructor createAndPersistInstructor() {
        em.getTransaction().begin();
        Instructor instructor;
        try {
            instructor = new Instructor("Иванов", "Иван", 5,"1111");
            em.persist(instructor);
            em.getTransaction().commit();
        } catch (PersistenceException pe) {
            em.getTransaction().rollback();
            throw pe;
        }
        return instructor;
    }
}