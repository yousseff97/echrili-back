package murraco.controller;

import murraco.dto.BidDataDTO;
import murraco.model.Bid;
import murraco.model.User;
import murraco.service.BidService;
import murraco.service.TaskService;
import murraco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/bids")
public class BidController {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private BidService bidService;


    @PostMapping
    public void save(HttpServletRequest req, @RequestBody BidDataDTO bidDataDTO) {
       // User whoami=userService.whoami(req);
        User whoami=userService.getUserById(1);
        Bid bid = new Bid();
        bid.setPrice(bidDataDTO.getPrice());
        bid.setDeliveryTime(bidDataDTO.getDeliveryTime());
        bid.setTask(taskService.find(bidDataDTO.getTaskId()));
        bid.setUser(whoami);
        bidService.save(bid);

    }


}
