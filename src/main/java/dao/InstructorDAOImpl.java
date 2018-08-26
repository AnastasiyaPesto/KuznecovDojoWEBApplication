package dao;

import domain.Certificate;
import domain.Instructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class InstructorDAOImpl implements InstructorDAO {
    private final SessionFactory sessionFactory;

    public InstructorDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Instructor create(String firstName, String secondName, byte age) {
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
    public Instructor createWithCertificates(String firstName, String secondName, byte age, Set<Certificate> certificates) {
        Instructor instructor = new Instructor();
        instructor.setFirstName(firstName);
        instructor.setSecondName(secondName);
        instructor.setAge(age);
        instructor.setCertificateSet(certificates);

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

        String query = "FROM instructor";

        List<Instructor> resultListInstructors = session.createQuery(query, Instructor.class).getResultList();

        transaction.commit();
        session.close();

        return resultListInstructors;
    }
}
