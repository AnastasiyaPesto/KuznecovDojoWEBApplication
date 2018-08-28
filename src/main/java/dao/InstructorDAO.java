package dao;

import domain.Certificate;
import domain.Instructor;

import java.util.List;
import java.util.Set;

public interface InstructorDAO {
    Instructor create(String firstName, String secondName, int age);
    List<Instructor> getAll();
    List<Instructor> findByFirstName(String firstName);
}
