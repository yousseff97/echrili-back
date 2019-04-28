package murraco.controller;


import murraco.dto.NotificationResponseDTO;
import murraco.exception.CustomException;
import murraco.model.Notification;
import murraco.model.User;
import murraco.service.NotificationService;
import murraco.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {


    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Notification> getCurrentUserNotifications(HttpServletRequest req) {

        User whoami = userService.whoami(req);
        return notificationService.getAllByUser(whoami);

    }


    @DeleteMapping("/notification/{id}")
    public void deleteNotification(HttpServletRequest req,@PathVariable int id) {
        User whoami = userService.whoami(req);
        Notification notification = notificationService.getOne(id);
        if (notification.getUser().getId() == whoami.getId())
            notificationService.delete(id);
        else
            throw new CustomException("no notification", HttpStatus.BAD_REQUEST);

    }


}
