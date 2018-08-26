package dao;

import domain.Instructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class InstructorDAOImplTest {
    private InstructorDAO instructorDAO;
    private SessionFactory factory;
    private Session session;
    private Transaction transaction;

    @Before
    public void setup() {
        this.factory = Mockito.mock(SessionFactory.class);
        this.instructorDAO = new InstructorDAOImpl(this.factory);
    }

    @Test
    public void testFindByName() {
        session = Mockito.mock(Session.class);
        Mockito.when(factory.openSession()).thenReturn(session);

        transaction = Mockito.mock(Transaction.class);
        Mockito.when(session.beginTransaction()).thenReturn(transaction);

        session = Mockito.mock(Session.class);
        Mockito.when(factory.openSession()).thenReturn(session);

        transaction = Mockito.mock(Transaction.class);
        Mockito.when(session.beginTransaction()).thenReturn(transaction);

        String first_name = "Deev";
        String second_name = "Alexey";
        byte age = 20;
        Instructor instructor = new Instructor();
        instructor.setFirstName(first_name);
        instructor.setSecondName(second_name);
        instructor.setAge(age);

        String first_name2 = "Ivanov";
        String second_name2 = "Ivan";
        byte age2 = 35;
        Instructor instructor2 = new Instructor();
        instructor2.setFirstName(first_name2);
        instructor2.setSecondName(second_name2);
        instructor2.setAge(age2);

        List<Instructor> sets = new ArrayList<>();
        sets.add(instructor);
        sets.add(instructor2);

        instructorDAO.create(first_name, second_name, age);
        instructorDAO.create(first_name2, second_name2, age2);

        List<Instructor> instructors = instructorDAO.findByFirstName("Ivanov");

        assertEquals(1, instructors.size());
    }

    @Test
    public void testGetAll() {
        session = Mockito.mock(Session.class);
        Mockito.when(factory.openSession()).thenReturn(session);

        transaction = Mockito.mock(Transaction.class);
        Mockito.when(session.beginTransaction()).thenReturn(transaction);

        String first_name = "Deev";
        String second_name = "Alexey";
        byte age = 20;
        Instructor instructor = new Instructor();
        instructor.setFirstName(first_name);
        instructor.setSecondName(second_name);
        instructor.setAge(age);

        String first_name2 = "Ivanov";
        String second_name2 = "Ivan";
        byte age2 = 35;
        Instructor instructor2 = new Instructor();
        instructor2.setFirstName(first_name2);
        instructor2.setSecondName(second_name2);
        instructor2.setAge(age2);

        List<Instructor> sets = new ArrayList<>();
        sets.add(instructor);
        sets.add(instructor2);

        instructorDAO.create(first_name, second_name, age);
        instructorDAO.create(first_name2, second_name2, age2);

        List<Instructor> allInstructors = instructorDAO.getAll();

        assertEquals(sets, allInstructors);

        Mockito.verify(transaction).commit();
        Mockito.verify(session, Mockito.times(1)).close();
    }

    @Test
    public void testCreate() {
        session = Mockito.mock(Session.class);
        Mockito.when(factory.openSession()).thenReturn(session);

        transaction = Mockito.mock(Transaction.class);
        Mockito.when(session.beginTransaction()).thenReturn(transaction);

        String first_name = "Deev";
        String second_name = "Alexey";
        byte age = 20;

        Instructor instructor = instructorDAO.create(first_name, second_name, age);

        assertEquals(first_name, instructor.getFirstName());
        assertEquals(second_name, instructor.getSecondName());
        assertEquals(age, instructor.getAge());

        Mockito.verify(transaction).commit();
        Mockito.verify(session, Mockito.times(1)).close();
    }
}
