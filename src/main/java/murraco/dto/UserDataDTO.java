package murraco.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import murraco.model.Role;

public class UserDataDTO {
  
  @ApiModelProperty(position = 0)
  private String username;
  @ApiModelProperty(position = 1)
  private String email;
  @ApiModelProperty(position = 2)
  List<Role> roles;


  String firstname;


  String lastname;


  private String password;


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

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

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  @Override
  public String toString() {
    return "UserDataDTO{" +
            "username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", roles=" + roles +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}
