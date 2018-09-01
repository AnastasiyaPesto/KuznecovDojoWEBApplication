package dao;

import domain.Instructor;
import domain.SportClub;

public interface SportClubDAO {
    SportClub create(String metro, String address, String phone);
    void addInstructor(SportClub sportClub, Instructor instructor);
    void deleteInstructorFrom(SportClub sportClub, Instructor instructor);
    void delete(int id);
}
