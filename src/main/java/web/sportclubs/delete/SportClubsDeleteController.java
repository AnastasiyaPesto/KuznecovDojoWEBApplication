package web.sportclubs.delete;

import dao.SportClubDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.sportclubs.SportClubsListBean;

import java.util.List;

@Controller
public class SportClubsDeleteController {
    private final SportClubDAO sportClubDAO;

    @Autowired
    public SportClubsDeleteController(SportClubDAO dao) {
        this.sportClubDAO = dao;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/sportclubs/all")
    public String deleteSportClubs(@RequestParam("selectedClubs") List<Integer> ids,
                                   ModelMap model) {
        for (Integer id : ids){
            sportClubDAO.delete(id);
        }

        model.put("sporClubsAllBean", new SportClubsListBean(sportClubDAO.getAll()));
        return "sportclubs/sportclubs-list";
    }
}
