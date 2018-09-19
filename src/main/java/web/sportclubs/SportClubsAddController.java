package web.sportclubs;

import dao.SportClubDAO;
import dao.SportClubDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class SportClubsAddController {
    private final SportClubDAO sportClubDAO;

    @Autowired
    public SportClubsAddController(SportClubDAO dao) {
        this.sportClubDAO = dao;
    }

    @ModelAttribute("formAddBean")
    public SportClubsAddFormBean createDefaultFormBean() {
        return new SportClubsAddFormBean();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/sportclubs/add")
    public String addSportClubShowForm() {
        return "sportclubs/sportclubs-add";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/sportclubs/add")
    public String addSportClubPostForm(@Valid @ModelAttribute("formAddBean") SportClubsAddFormBean addFormBean,
                                       BindingResult binding) {
        if (addFormBean.getMetro().isEmpty()) {
            binding.addError(new FieldError(
                    "formAddBean",
                    "metro",
                    "Поле МЕТРО не должно быть пустым"));
        }

        if (addFormBean.getAddress().isEmpty()) {
            binding.addError(new FieldError(
                    "formAddBean",
                    "address",
                    "Поле АДРЕС не должно быть пустым"));
        }

        if (addFormBean.getPhone().isEmpty()) {
            binding.addError(new FieldError(
                    "formAddBean",
                    "phone",
                    "Поле ТЕЛЕФОН не должно быть пустым"));
        }

        if (binding.hasErrors()) {
            return "sportclubs/sportclubs-add";
        }

        sportClubDAO.create(
                addFormBean.getMetro(),
                addFormBean.getAddress(),
                addFormBean.getPhone());

        return "redirect:/sportclubs/all";
    }
}
