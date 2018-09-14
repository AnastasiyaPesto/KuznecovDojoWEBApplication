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
public class InstructorsAddAndShowAllController {
    private final InstructorDAO instructorDAO;

    @Autowired
    public InstructorsAddAndShowAllController(InstructorDAO instructorDAO) {
        this.instructorDAO = instructorDAO;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/instructors/all")
    public String instructorAllList(ModelMap modelMap) {
        // Если здесь возникнет ошибка (сломалось что-то/нет в базе),
        // то из doGet вылетит ошибка и попадет в Tomcat.
        // Если эта ошибка нами не обработана, то Tomcat сам сгенерирует ошибку 500 (с stacktrace)
        // Значит нам не нужно тут делать огромный try/catch
        List<Instructor> instructors = instructorDAO.getAll();

        InstructorListBean instructorListBean = new InstructorListBean(instructors);

        modelMap.put("instructorListBean", instructorListBean);

        return "instructor-list";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/instructors/add")
    public String addInstructorShowForm(){
        return "instructor-add";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/instructors/add")
    public String addInstructorPostForm(@RequestParam String firstName,
                                        @RequestParam String secondName,
                                        @RequestParam int age,
                                        @RequestParam String phone,
                                        ModelMap modelMap){
        if (firstName == null) {
            throw new IllegalArgumentException("firstName is missing");
        }

        if (secondName == null) {
            throw new IllegalArgumentException("secondName is missing");
        }

        if (age <= 0) {
            throw new IllegalArgumentException("age is missing");
        }

        if (phone == null) {
            throw new IllegalArgumentException("phone is missing");
        }

        Instructor instructor = instructorDAO.create(firstName, secondName, age);
        instructorDAO.update(instructor, phone);

        return instructorAllList(modelMap);
    }
}
