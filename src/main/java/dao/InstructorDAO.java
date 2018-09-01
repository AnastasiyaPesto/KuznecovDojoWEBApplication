package dao;

import domain.Certificate;
import domain.Instructor;
import domain.SportClub;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface InstructorDAO {
    Instructor create(String firstName, String secondName, int age);
    List<Instructor> getAll();
    List<Instructor> findByFirstName(String firstName);
    List<Instructor> findWhereDegreeIsMore(int degree);
//    List<Instructor> findBySportClub(SportClub sportClub);
    void update(Instructor instructor, String phone);
    Instructor delete(int id);
    void addCertificate(Instructor instructor, String number, int degree, Date dateComplete);
    void deleteSportClubFrom(Instructor instructor, SportClub sportClub);
    void addSportClub(Instructor instructor, SportClub sportClub);
    ArrayList<Certificate> getAllCertificate(int id);
}
