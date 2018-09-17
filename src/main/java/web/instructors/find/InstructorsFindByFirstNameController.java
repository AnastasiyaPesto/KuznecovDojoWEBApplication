package web.instructors.find;

import dao.InstructorDAO;
import domain.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.InstructorListBean;

import java.util.List;

@Controller
public class InstructorsFindByFirstNameController {
    private final InstructorDAO instructorDAO;

    @Autowired
    public InstructorsFindByFirstNameController(InstructorDAO instructorDAO) {
        this.instructorDAO = instructorDAO;
    }

    // нужен для того, чтобы  при первом запуске формы для поиска был пустой бин
    // чтобы потом при поиске вернуться на текущую страницу
    @ModelAttribute("instructorListBean")
    public InstructorListBean createDefaultInstructorListBean(){
        return new InstructorListBean();
    }

    @ModelAttribute("instructorFindBean")
    public InstructorFindBean createDefaultFormBean() {
        return new InstructorFindBean();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/instructors/find/firstName")
    public String findByFirstNamePostForm(
            @ModelAttribute("instructorFindBean") InstructorFindBean instructorFindBean,
            ModelMap modelMap) {
        List<Instructor> instructors = instructorDAO.findByFirstName(instructorFindBean.getFirstName());
        InstructorListBean instructorListBean = new InstructorListBean(instructors);

        modelMap.put("instructorListBean", instructorListBean);

        return "instructors/find/firstname";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/instructors/find/firstName")
    public String findByFirstNameShowForm(){
        return "instructors/find/firstname";
    }
}
