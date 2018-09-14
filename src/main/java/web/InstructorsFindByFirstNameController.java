package web;

import dao.InstructorDAO;
import domain.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class InstructorsFindByFirstNameController {
    private final InstructorDAO instructorDAO;

    @Autowired
    public InstructorsFindByFirstNameController(InstructorDAO instructorDAO) {
        this.instructorDAO = instructorDAO;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/instructors/findByFirstName")
    public String findByFirstNamePostForm(@RequestParam String firstName, ModelMap modelMap) {
        List<Instructor> instructors = instructorDAO.findByFirstName(firstName);
        InstructorListBean instructorListBean = new InstructorListBean(instructors);

        modelMap.put("instructorListBean", instructorListBean);

        return "instructor-foundedinstructorsbyfirstname";
    }

//    @RequestMapping(method = RequestMethod.GET, path = "/instructors/foundedByFirstName")
//    public String foundedInstructors() {
//        return "instructor-foundedinstructorsbyfirstname";
//    }

    @RequestMapping(method = RequestMethod.GET, path = "/instructors/findByFirstName")
    public String findByFirstNamePostFormShowForm(){
        return "instructor-findbyfirstname";
    }
}
