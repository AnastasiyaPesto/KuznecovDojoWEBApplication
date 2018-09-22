package web.sportclubs;

import dao.SportClubDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SportClubsDeleteController {
    private final SportClubDAO sportClubDAO;

    @Autowired
    public SportClubsDeleteController(SportClubDAO dao) {
        this.sportClubDAO = dao;
    }
}
