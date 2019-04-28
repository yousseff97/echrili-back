package murraco.model;

import javax.persistence.*;

@Entity
public class TrustedUsers {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToOne
    private User user;


    @OneToOne
    private User trustedUser;


    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getTrustedUser() {
        return trustedUser;
    }

    public void setTrustedUser(User trustedUser) {
        this.trustedUser = trustedUser;
    }
}
