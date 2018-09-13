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
public class InstructorsFindByFirstName {
    private final InstructorDAO instructorDAO;

    @Autowired
    public InstructorsFindByFirstName(InstructorDAO instructorDAO) {
        this.instructorDAO = instructorDAO;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/instructors/findByFirstName")
    public String findByFirstNamePostForm(@RequestParam String firstName, ModelMap modelMap) {
        List<Instructor> instructors = instructorDAO.findByFirstName(firstName);
//        InstructorListBean instructorListBean = new InstructorListBean(instructors);

//        modelMap.put("instructorListBean", instructorListBean);

        return "instructor-findbyfirstname";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/instructors/findByFirstName")
    public String afindByFirstNamePostFormShowForm(){
        return "instructor-findbyfirstname";
    }
}
