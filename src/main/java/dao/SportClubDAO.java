package dao;

import domain.Instructor;
import domain.SportClub;

public interface SportClubDAO {
    SportClub create(String metro, String address, String phone);
    void addInstructor(Instructor instructor);
    void deleteInstructor(Instructor instructor);
}
