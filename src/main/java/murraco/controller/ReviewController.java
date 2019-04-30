package murraco.controller;


import murraco.dto.ReviewDataDTO;
import murraco.model.Notification;
import murraco.model.Review;
import murraco.model.User;
import murraco.service.ReviewService;
import murraco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {


    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;


    @PostMapping("")
  //  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public void addReview(@RequestBody ReviewDataDTO reviewDataDTO) {
        User user = userService.getUserById(reviewDataDTO.getId());
        Review review = reviewService.getOneByUser(user);

        System.out.println(user.getUsername());
        System.out.println(reviewDataDTO.getId());
        System.out.println(reviewDataDTO.getRate());
        if(review==null)
        {
            review = new Review();
            review.setUser(user);
         review= reviewService.save(review);
        }

        System.out.println(review);
        float rate = review.getRate();
        int numberOfVoters = review.getNumberofvoters();
        float newRate = (rate * numberOfVoters + (float) reviewDataDTO.getRate()) /( review.getNumberofvoters() + 1);
        review.setRate(newRate);
        System.out.println(newRate);
        review.setNumberofvoters(review.getNumberofvoters()+1);
        System.out.println(reviewDataDTO.toString());
        reviewService.save(review);

    }


}
