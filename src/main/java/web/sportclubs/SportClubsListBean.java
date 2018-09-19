package web.sportclubs;

import domain.SportClub;

import java.util.List;

public class SportClubsListBean {
    private List<SportClub> sportClubs;
    private boolean nowIsSearch = false;

    public SportClubsListBean(List<SportClub> sportClubs) {
        this.sportClubs = sportClubs;
    }

    public List<SportClub> getSportClubs() {
        return sportClubs;
    }

    public boolean isNowIsSearch() {
        return nowIsSearch;
    }

    public void setNowIsSearch(boolean nowIsSearch) {
        this.nowIsSearch = nowIsSearch;
    }
}
