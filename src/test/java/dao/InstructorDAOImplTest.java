package dao;

import domain.Instructor;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class InstructorDAOImplTest {
    private EntityManagerFactory emf;
    private EntityManager em;
    private InstructorDAO dao;

    @Before
    public void setup() {
        emf = Persistence.createEntityManagerFactory("TestKuznecovDojoPersistenceUnit");
        em = emf.createEntityManager();
        dao = new InstructorDAOImpl(em);
    }

    @Test
    public void testCreate_validData_returnSavedObject() {
        String firstName = "Иванов";
        String secondName = "Иван";
        int age = 23;
        String phone = "89115552323";
        Instructor instructor = new Instructor();
        instructor.setFirstName(firstName);
        instructor.setSecondName(secondName);
        instructor.setAge(age);

        Instructor createdInstructor = dao.create(firstName, secondName, age);

        assertEquals(firstName, createdInstructor.getFirstName());
        assertEquals(secondName, createdInstructor.getSecondName());
        assertEquals(age, createdInstructor.getAge());

        // todo могу ли я на реальных даннных проверить вызов метода persist
        // todo или этого не надо в данном тесте, так как assertEquals проверяем поля
    }

    @After
    public void after() {
        if ( em != null){
            em.close();
        }

        if (emf != null) {
            emf.close();
        }
    }
}
