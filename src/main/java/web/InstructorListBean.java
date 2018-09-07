package web;

import domain.Instructor;

import java.util.List;

public class InstructorListBean {
    private String userAgent;
    private List<Instructor> instructors;

    public InstructorListBean(String userAgent, List<Instructor> instructors) {
        this.userAgent = userAgent;
        this.instructors = instructors;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }
}
