package dao;

import domain.Instructor;
import domain.SportClub;

import java.util.List;

public interface SportClubDAO {
    SportClub create(String metro, String address, String phone);
    void addInstructor(SportClub sportClub, Instructor instructor);
    void deleteInstructorFrom(SportClub sportClub, Instructor instructor);
    void delete(int id);
    List<SportClub> getAll();
}
