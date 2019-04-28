package murraco.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import murraco.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

  boolean existsByUsername(String username);

  User findByUsername(String username);

  @Transactional
  void deleteByUsername(String username);


    List<User> getAllBy();



//  @Query(value = "select u from User u where not exists (select r from Request r where (r.firstUser=:user or r.secondUser=:user) and (r.firstUser=u.id or r.secondUser=u.id)) ")
//  List<User> getAllUsersExceptRequest(@Param("user") User user);


}
