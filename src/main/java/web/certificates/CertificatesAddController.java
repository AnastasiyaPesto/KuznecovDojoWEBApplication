package web.certificates;

import dao.CertificateDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CertificatesAddController {
    private final CertificateDAO dao;

    @Autowired
    public CertificatesAddController(CertificateDAO dao) {
        this.dao = dao;
    }

}
