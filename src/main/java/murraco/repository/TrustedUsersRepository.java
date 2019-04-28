package murraco.repository;

import murraco.model.TrustedUsers;
import murraco.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrustedUsersRepository extends JpaRepository<TrustedUsers, Integer> {




    TrustedUsers getFirstByUserAndTrustedUser(User user, User trustedUser);
    Boolean existsByUserAndTrustedUser(User user, User trustedUser);
    List<TrustedUsers> getAllByUser(User user);
    List<TrustedUsers> getAllByTrustedUser(User user);






}