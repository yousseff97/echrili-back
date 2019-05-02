package murraco.controller;

import javafx.geometry.Pos;
import murraco.dto.BidDataDTO;
import murraco.model.Bid;
import murraco.model.Task;
import murraco.model.User;
import murraco.service.BidService;
import murraco.service.TaskService;
import murraco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        User whoami=userService.whoami(req);
       // User whoami=userService.getUserById(1);
        Bid bid = new Bid();
        bid.setPrice(bidDataDTO.getPrice());
        bid.setDeliveryTime(bidDataDTO.getDeliveryTime());
        bid.setTask(taskService.find(bidDataDTO.getTaskId()));
        bid.setUser(whoami);
        bidService.save(bid);

    }


    @PostMapping("/accept")
    public void acceptBid(@RequestParam("id") Integer id)
    {
Bid bid = bidService.getOne(id);
bid.setStatus(true);
bidService.save(bid);
    }

    @GetMapping("/{id}")
    public List<Bid> getBidsByTask(@PathVariable int id)
    {
        Task t = taskService.getOne(id);
        return this.bidService.getAllByTask(t);
    }


}
