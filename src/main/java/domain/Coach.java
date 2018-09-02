package domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "coach")
public class Coach extends SimpleUser {
    @Column
    String phone;

    public Coach(String login, String password) {
        super(login, password);
    }

    public Coach() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
