package dao;

import domain.Certificate;
import domain.Instructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class InstructorDAOImpl implements InstructorDAO {
    private final SessionFactory sessionFactory;

    public InstructorDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Instructor create(String firstName, String secondName, int age) {
        Instructor instructor = new Instructor();
        instructor.setFirstName(firstName);
        instructor.setSecondName(secondName);
        instructor.setAge(age);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(instructor);

        transaction.commit();
        session.close();

        return instructor;
    }

    @Override
    public List<Instructor> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

//        List<Instructor> instructors = session
//                .createQuery("from domain.Instructor", Instructor.class)
//                .list();

        List<Instructor> instructors = session
                .createQuery("from Instructor")
                .list();

        transaction.commit();
        session.close();

        return instructors;
    }

    @Override
    public List<Instructor> findByFirstName(String firstName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Instructor> instructors = null;
        try {
            transaction = session.beginTransaction();
            instructors = session.createQuery("from domain.Instructor where first_name = :name")
                    .setParameter("name", firstName)
                    .list();
            transaction.commit();
        } catch (Exception exc) {
            transaction.rollback();
            exc.printStackTrace();
        } finally {
            session.close();
            return instructors;
        }
    }
}
