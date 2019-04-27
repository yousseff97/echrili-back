package murraco.repository;

import murraco.model.Request;
import murraco.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Integer> {

    Request getFirstByFirstUserAndSecondUserAndStatusIsFalse(User firstUser, User secondUser);
    List<Request> getAllByFirstUserAndStatusIsTrue(User user);
    List<Request> getAllBySecondUserAndStatusIsTrue(User user);
    List<Request> getAllBySecondUserAndStatusIsFalse(User user);






}