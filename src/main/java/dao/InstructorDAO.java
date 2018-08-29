package dao;

import domain.Certificate;
import domain.Instructor;
import domain.SportClub;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Set;

public interface InstructorDAO {
    Instructor create(String firstName, String secondName, int age);
    List<Instructor> getAll();
    List<Instructor> findByFirstName(String firstName);
    List<Instructor> findWhereDegreeIsMore(String degree);
    List<Instructor> findBySportClub(SportClub sportClub);
}
