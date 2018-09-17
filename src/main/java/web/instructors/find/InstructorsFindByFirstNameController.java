package web.instructors.find;

import dao.InstructorDAO;
import domain.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.InstructorListBean;

import java.util.List;

@Controller
public class InstructorsFindByFirstNameController {
    private final InstructorDAO instructorDAO;

    @Autowired
    public InstructorsFindByFirstNameController(InstructorDAO instructorDAO) {
        this.instructorDAO = instructorDAO;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/instructors/find/firstName")
    public String findByFirstNamePostForm(@RequestParam String firstName, ModelMap modelMap) {
        List<Instructor> instructors = instructorDAO.findByFirstName(firstName);
        InstructorListBean instructorListBean = new InstructorListBean(instructors);
        instructorListBean.setNowIsSearch(true);
        modelMap.put("instructorListBean", instructorListBean);

        return "instructors/find/firstname";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/instructors/find/firstName")
    public String findByFirstNameShowForm(ModelMap modelMap){
        InstructorListBean instructorListBean = new InstructorListBean();
        instructorListBean.setNowIsSearch(false);
        modelMap.put("instructorListBean", instructorListBean);
        return "instructors/find/firstname";
    }
}
