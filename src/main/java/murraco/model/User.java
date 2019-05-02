package murraco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.aspectj.weaver.ast.Not;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
  @Column( nullable = false)
  private String username;

  @Column( nullable = false)
  private String email;

  @Size(min = 8, message = "Minimum password length: 8 characters")
  @JsonIgnore
  private String password;


  private String firstname;


  private String lastname;


  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  @ElementCollection(fetch = FetchType.EAGER)
          @JsonIgnore
  List<Role> roles;


  @OneToMany
  List<Notification> notifications;

  @OneToOne(mappedBy = "user")
  Review review;

  public Review getReview() {
    return review;
  }

  public void setReview(Review review) {
    this.review = review;
  }

  public List<Notification> getNotifications() {
    return notifications;
  }

public void addNotification(Notification notification)
{

  if(notifications==null)
  {
    notifications=new ArrayList<>();
  }


  notifications.add(notification);

}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

}
