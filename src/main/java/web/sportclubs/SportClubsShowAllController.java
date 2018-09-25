package web.sportclubs;

import dao.SportClubDAO;
import domain.SportClub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.sportclubs.SportClubsListBean;

import java.util.List;

@Controller
public class SportClubsShowAllController {
    private final SportClubDAO sportClubDAO;

    @Autowired
    public SportClubsShowAllController(SportClubDAO dao) {
        this.sportClubDAO = dao;
    }

    @RequestMapping(method = RequestMethod.GET, path = "sportclubs/all")
    public String sportClubsAllList(ModelMap modelMap) {
        List<SportClub> sportClubs = sportClubDAO.getAll();

        SportClubsListBean sportClubsListBean = new SportClubsListBean(sportClubs);
        modelMap.put("sportClubsAllBean", sportClubsListBean);

        return "sportclubs/sportclubs-list";
    }
}
