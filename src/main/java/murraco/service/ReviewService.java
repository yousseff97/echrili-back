package murraco.service;

import murraco.model.Review;
import murraco.model.User;
import murraco.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReviewService {


@Autowired
private ReviewRepository reviewRepository;






public Review save(Review review)
{

    return reviewRepository.save(review);

}


public Review getOneByUser(User user)
{
    return reviewRepository.getByUser(user);
}








}
