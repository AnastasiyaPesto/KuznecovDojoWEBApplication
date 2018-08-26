package dao;

import domain.Certificate;
import domain.Instructor;

import java.util.List;
import java.util.Set;

public interface InstructorDAO {
    Instructor create(String firstName, String secondName, byte age);
    Instructor createWithCertificates(String firstName, String secondName, byte age, Set<Certificate> certificates);
    List<Instructor> getAll();
}
