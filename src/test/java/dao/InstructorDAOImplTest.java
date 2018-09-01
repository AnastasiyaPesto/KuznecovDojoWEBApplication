package dao;

import domain.Certificate;
import domain.Instructor;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

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

    @Test
    public void testUpdate_instructorAndPhone() {
        String firstName = "Иванов";
        String secondName = "Иван";
        int age = 23;

        Instructor instructor = new Instructor();

        instructor = dao.create(firstName, secondName, age);
        dao.update(instructor, "89114013433");

        assertNotEquals("", instructor.getPhone());

//        insert
//                into
//        instructor
//                (age, first_name, phone, second_name, instr_id)
//        values
//                (?, ?, ?, ?, ?)

//        update
//                instructor
//        set
//        age=?,
//        first_name=?,
//        phone=?,
//        second_name=?
//        where
//        instr_id=?
    }

    @Test
    public void testDelete_validId () {
        String firstName = "Иванов";
        String secondName = "Иван";
        int age = 23;

        Instructor createdInstructor = dao.create(firstName, secondName, age);
        // тут инструктор уже получил id
        int id = createdInstructor.getInstructorId();

        dao.delete(id);

        assertNull(em.find(Instructor.class, id));
    }

    @Test
    public void testWhenDeleteInstructorDeleteCertificates() {
        String firstName = "Иванов";
        String secondName = "Иван";
        int age = 23;

        Instructor createdInstructor = dao.create(firstName, secondName, age);
        int id = createdInstructor.getInstructorId();

        Date dateCompleted5 = null;
        Date dateCompleted3 = null;
        try {
            dateCompleted5 = new SimpleDateFormat("dd.MM.YYYY").parse("01.05.2018");
            dateCompleted3 = new SimpleDateFormat("dd.MM.YYYY").parse("17.10.2015");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dao.addCertificate(createdInstructor, "KD_001", 5, dateCompleted5);
        dao.addCertificate(createdInstructor, "KD_095", 3, dateCompleted3);

        Map<String, Certificate> certificateMap = createdInstructor.getCertificateMap();

        dao.delete(id);
        assertNull(em.find(Instructor.class, id));

        for (String key : certificateMap.keySet()) {
            assertNull(em.find(Certificate.class, certificateMap.get(key).getCertificateId()));
        }
    }

    @Test
    public void testAddCertificate() {
        String firstName = "Иванов";
        String secondName = "Иван";
        int age = 23;

        Instructor createdInstructor = dao.create(firstName, secondName, age);

        String numberCertificate = "KD_001";
        int degree = 5; // 5 дан
        Date dateCompleted = null;
        try {
            dateCompleted = new SimpleDateFormat("dd.MM.YYYY").parse("01.05.2018");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertEquals(0, createdInstructor.getCertificateMap().size());
        dao.addCertificate(createdInstructor, numberCertificate, degree, dateCompleted);
        assertEquals(1, createdInstructor.getCertificateMap().size());
    }

    @Test
    public void testGetAll_void_AllInstructors() {
        Instructor instructor1 = dao.create("Пестовникова", "Анастасия", 26);
        Instructor instructor2 = dao.create("Иванов", "Иван", 33);
        Instructor instructor3 = dao.create("Вотинцев", "Сергей", 48);

        List<Instructor> allInstructors = dao.getAll();

        assertEquals(3, allInstructors.size());
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
