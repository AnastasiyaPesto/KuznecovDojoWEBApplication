package web.instructors.delete;

import com.sun.org.apache.xpath.internal.operations.Mod;
import dao.InstructorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.instructors.InstructorListBean;
import web.instructors.add.InstructorAddFormBean;

import java.util.List;

@Controller
public class InstructorDeleteController {
    private final InstructorDAO instructorDAO;

    @Autowired
    public InstructorDeleteController(InstructorDAO instructorDAO) {
        this.instructorDAO = instructorDAO;
    }

    // @ModelAttribute("formAddBean")
    // public InstructorAddFormBean createDefaultFormBean() {
      //  return new InstructorAddFormBean();
    // }

    @RequestMapping(method = RequestMethod.POST, path = "instructors/all")
    public String deleteInstructors(@RequestParam("selectedInstr") List<Integer> instructorsIds,
                                    ModelMap model
                                    /*BindingResult binding*/) {
//        if (instructorsIds.size() == 0) {
//            binding.addError(new FieldError(
//                    "",
//                    "selectedInstr",
//                    "Для удаления выберите хотя бы одного инструктора"));
//            return "redirect:/instructors/all";
//        }
        for (Integer id : instructorsIds) {
            instructorDAO.delete(id);
        }
        model.put("instructorListBean", new InstructorListBean(instructorDAO.getAll()));
        return "instructors/instructor-list";
    }
}
