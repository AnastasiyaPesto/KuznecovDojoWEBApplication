package web.certificates;

import domain.Certificate;

import java.util.List;

public class CertificateListBean {
    private List<Certificate> certificates;

    private String firstName;

    public CertificateListBean(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }
}
