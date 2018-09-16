package web;

import domain.Instructor;

import java.util.List;

public class InstructorListBean {
    private List<Instructor> instructors;

    public InstructorListBean(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    public InstructorListBean() {

    }

    public List<Instructor> getInstructors() {
        return instructors;
    }
}
