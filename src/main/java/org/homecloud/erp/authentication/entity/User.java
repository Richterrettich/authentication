package org.homecloud.erp.authentication.entity;

import org.homecloud.erp.commons.entity.BaseEntity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * Created by rene on 19.02.16.
 */
@NamedQueries(
    value = {
        @NamedQuery(name = "User.authenticate",query = "SELECT u from User u where email = :email AND password = :password")
    }
)
@XmlRootElement
public class User extends BaseEntity {

  @Column(unique=true)
  private String email;
  @XmlTransient
  private String password;

  @ElementCollection
  private List<String> roles;


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

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }
}
