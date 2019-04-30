package murraco.repository;

import murraco.model.Review;
import murraco.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {


public Review getByUser(User user);








}
