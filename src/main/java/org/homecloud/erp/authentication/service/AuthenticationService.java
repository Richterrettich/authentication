package org.homecloud.erp.authentication.service;

import org.homecloud.erp.authentication.entity.User;
import org.homecloud.erp.commons.repository.BaseRepository;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by rene on 20.02.16.
 */
@Stateless
public class AuthenticationService {

  @PersistenceContext
  private EntityManager em;
  private BaseRepository<User> userRepository;

  @PostConstruct
  public void init() {
    this.userRepository = new BaseRepository<>(em,User.class);
  }


  public User authenticate(String email, String password) {
    Map<String,Object> parameters = new HashMap<>();
    parameters.put("email",email);
    parameters.put("password",password);
    List<User> result = userRepository.executeNamedQuery(User.class,"User.authenticate",parameters);

    return result.isEmpty()
        ? null
        : result.get(0);

  }

  public String register(User user) {
    if (user.getId() == null)
      user.setId(UUID.randomUUID().toString());
    this.userRepository.create(user);
    return user.getId();
  }

  public void delete(String id) {
    this.userRepository.remove(id);
  }
}
