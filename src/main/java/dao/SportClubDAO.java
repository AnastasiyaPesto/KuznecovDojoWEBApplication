package dao;

import domain.Instructor;
import domain.SportClub;

public interface SportClubDAO {
    SportClub create(String metro, String address, String phone);
    void addInstructor(SportClub sportClub, Instructor instructor);
    void deleteInstructor(SportClub sportClub, Instructor instructor);
}
