package web.certificates;

import dao.CertificateDAO;
import dao.InstructorDAO;
import domain.Certificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class CertificatesAddController {
    private final CertificateDAO certificateDAO;
    private final InstructorDAO instructorDAO;

    @Autowired
    public CertificatesAddController(CertificateDAO dao, CertificateDAO certificateDAO, InstructorDAO instructorDAO) {
        this.certificateDAO = certificateDAO;
        this.instructorDAO = instructorDAO;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/instructors/certificate?InstructorId={id}")
    public String certificateGetAll(@PathVariable("id") String id,
                                    ModelMap model) {
//        String firstName = instructorDAO.findById(id);
        String firstName = "Пестовникова";
        ArrayList<Certificate> allCertificate = instructorDAO.getAllCertificate(Integer.parseInt(id));

        CertificateListBean bean = new CertificateListBean(allCertificate);
        bean.setFirstName(firstName);

        model.put("certificateListBean", bean);

        return "instructors/certificate/certificate-all";
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public String certificateAdd() {
//        return "instructors/certificate-add";
//    }
}
