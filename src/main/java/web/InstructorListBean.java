package web;

import domain.Instructor;

import java.util.List;

public class InstructorListBean {
    private List<Instructor> instructors;
    private boolean nowIsSearch = false;

    public InstructorListBean(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    public InstructorListBean() {

    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public boolean isNowIsSearch() {
        return nowIsSearch;
    }

    public void setNowIsSearch(boolean nowIsSearch) {
        this.nowIsSearch = nowIsSearch;
    }
}
