package murraco.controller;

import murraco.model.Notification;
import murraco.model.Task;
import murraco.model.TrustedUsers;
import murraco.model.User;
import murraco.service.NotificationService;
import murraco.service.TaskService;
import murraco.service.TrustedUsersService;
import murraco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {




    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;


    @Autowired
    private NotificationService notificationService;

    @Autowired
    private TrustedUsersService trustedUsersService;


    @DeleteMapping("/delete")
    //   @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Integer delete(@RequestParam("id") Integer id) {
        taskService.delete(id);
        return id;
    }

    @GetMapping
public List<Task> getAllWithoutBid()
{

    //  User whoami = userService.whoami(req);
    User whoami = userService.getUserById(1);

    return this.taskService.getAllWithoutBid(whoami);
}


@GetMapping("/my")
public List<Task> getTasksByUser(HttpServletRequest req)
{
    //  User whoami = userService.whoami(req);
    User whoami = userService.getUserById(1);

    return this.taskService.getAllByUser(whoami);
}



    @PostMapping("/add")
    public void add(HttpServletRequest req, @RequestBody Task p) {
      //  User whoami = userService.whoami(req);
        User whoami = userService.getUserById(1);
        p.setUser(whoami);
       Task t= taskService.add(p);
       List<TrustedUsers> trustedUsers= trustedUsersService.getAllByUser(whoami);


       for( TrustedUsers trustedUser : trustedUsers)
       {

           Notification notification =new Notification();
           notification.setUser(trustedUser.getTrustedUser());
           notification.setMessage(trustedUser.getUser().getUsername()+" added new task");
           notificationService.save(notification);

       }

        System.out.println(t.getDescription());
    }



}
