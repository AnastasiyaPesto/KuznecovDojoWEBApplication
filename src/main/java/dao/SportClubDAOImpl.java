package dao;

import domain.Instructor;
import domain.SportClub;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

public class SportClubDAOImpl implements SportClubDAO {
    private EntityManager entityManager;

    public SportClubDAOImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public SportClub create(String metro, String address, String phone) {
        entityManager.getTransaction().begin();
        SportClub sportClub = new SportClub(metro, address, phone);
        try {
            entityManager.persist(sportClub);
            entityManager.getTransaction().commit();
        } catch (PersistenceException pe) {
            entityManager.getTransaction().rollback();
            throw pe;
        }
        return sportClub;
    }

    @Override
    public void addInstructor(SportClub sportClub, Instructor instructor) {
        if (sportClub == null) throw new IllegalArgumentException("Sport club shouldn't be null");
        entityManager.getTransaction().begin();
        try {
            sportClub.addInstructor(instructor);
            entityManager.getTransaction().commit();
        } catch (PersistenceException pe) {
            entityManager.getTransaction().rollback();
            throw pe;
        }
    }

    @Override
    public void deleteInstructorFrom(SportClub sportClub, Instructor instructor) {
        if (sportClub == null) throw new IllegalArgumentException("Sport club shouldn't be null");
        entityManager.getTransaction().begin();
        try {
            sportClub.getInstructors().remove(instructor.getInstructorId());
            instructor.deleteSportClub(sportClub);
            entityManager.getTransaction().commit();
        } catch (PersistenceException pe) {
            entityManager.getTransaction().rollback();
            throw pe;
        }
    }

    @Override
    public void delete(int id) {
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(entityManager.find(SportClub.class, id));
            entityManager.getTransaction().commit();
        } catch (PersistenceException pe) {
            entityManager.getTransaction().rollback();
            throw pe;
        }
    }
}
