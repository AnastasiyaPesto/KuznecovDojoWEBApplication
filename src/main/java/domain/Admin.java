package domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "admin")
public class Admin extends SimpleUser {
    public Admin(String login, String password) {
        super(login, password);
    }

    public Admin() {
    }
}
