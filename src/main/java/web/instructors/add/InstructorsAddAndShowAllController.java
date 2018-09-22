package web.instructors.add;

import dao.InstructorDAO;
import domain.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.instructors.InstructorListBean;

import javax.validation.Valid;
import java.util.List;

@Controller
public class InstructorsAddAndShowAllController {
    private final InstructorDAO instructorDAO;

    @Autowired
    public InstructorsAddAndShowAllController(InstructorDAO instructorDAO) {
        this.instructorDAO = instructorDAO;
    }

    @ModelAttribute("formAddBean")
    public InstructorAddFormBean createDefaultFormBean() {
        return new InstructorAddFormBean();
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

        return "instructors/instructor-list";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/instructors/add")
    public String addInstructorShowForm(){
        return "instructors/instructor-add";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/instructors/add")
    public String addInstructorPostForm(@Valid @ModelAttribute("formAddBean") InstructorAddFormBean addFormBean,
                                        BindingResult binding){
        if (addFormBean.getFirstName().isEmpty()) {
            binding.addError(new FieldError("formAddBean",
                    "firstName",
                    "Фамилия пустая"));
        }

        if (addFormBean.getSecondName().isEmpty()) {
            binding.addError(new FieldError("formAddBean",
                    "secondName",
                    "Имя пустое"));
        }

        if (addFormBean.getAge() <= 15) {
            binding.addError(new FieldError("formAddBean",
                    "age",
                    "Возраст должен быть больше 15"));
        }

        if (binding.hasErrors()) {
            return "instructors/instructor-add";
        }

        Instructor instructor = instructorDAO.create(
                addFormBean.getFirstName(),
                addFormBean.getSecondName(),
                addFormBean.getAge());
        instructorDAO.update(instructor, addFormBean.getPhone());

        return "redirect:/instructors/all";
    }
}
