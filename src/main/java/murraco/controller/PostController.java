package murraco.controller;

import murraco.model.User;
import murraco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import murraco.model.Post;
import murraco.service.PostService;

import javax.servlet.http.HttpServletRequest;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/posts")
@Api(tags = "posts")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;



    @DeleteMapping("/delete")
 //   @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The post doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public Integer delete(@RequestParam("id") Integer id) {
        postService.delete(id);
        return id;
    }

    @PostMapping("/add")
    public void add(HttpServletRequest req,@RequestBody Post p) {
        User whoami = userService.whoami(req);
        p.setUser(whoami);
        postService.add(p);
    }

}
