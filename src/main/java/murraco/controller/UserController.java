package murraco.controller;

import javax.servlet.http.HttpServletRequest;

import murraco.dto.MyUserResponseDTO;
import murraco.model.TrustedUsers;
import murraco.service.TrustedUsersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import murraco.dto.UserDataDTO;
import murraco.dto.UserResponseDTO;
import murraco.model.User;
import murraco.service.UserService;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
@Api(tags = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private TrustedUsersService requestService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/signin")
    @ApiOperation(value = "${UserController.signin}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public MyUserResponseDTO login(//
                                   @ApiParam("Username") @RequestParam String username, //
                                   @ApiParam("Password") @RequestParam String password) {
        String token = userService.signin(username, password);
        MyUserResponseDTO myUserResponseDTO = modelMapper.map(userService.getUserByToken(token), MyUserResponseDTO.class);
        myUserResponseDTO.setToken(token);
        return myUserResponseDTO;

    }

    @PostMapping("/signup")
    @ApiOperation(value = "${UserController.signup}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 422, message = "Username is already in use"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public String signup(@ApiParam("Signup User") @RequestBody UserDataDTO user) {
        return userService.signup(modelMapper.map(user, User.class));
    }

    @DeleteMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${UserController.delete}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public String delete(@ApiParam("Username") @PathVariable String username) {
        userService.delete(username);
        return username;
    }

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${UserController.search}", response = UserResponseDTO.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public UserResponseDTO search(@ApiParam("Username") @PathVariable String username) {
        return modelMapper.map(userService.search(username), UserResponseDTO.class);
    }

    @GetMapping(value = "/me")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${UserController.me}", response = UserResponseDTO.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public UserResponseDTO whoami(HttpServletRequest req) {
        return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public String refresh(HttpServletRequest req) {
        return userService.refresh(req.getRemoteUser());
    }

    @GetMapping("/friends")
    public List<User> getFriendsList(HttpServletRequest req) {
        User currentUser = userService.whoami(req);
        // User currentUser = userService.getUserById(1);
        System.out.println(currentUser);
        List<User> ret = new ArrayList<>();
        List<TrustedUsers> requests = requestService.getFriends(currentUser);

        for (TrustedUsers request : requests) {

            System.out.println(request);
            System.out.println("1111111");
            if (request.getUser().getId() != currentUser.getId()) {
                System.out.println("22222");
                System.out.println(request.getUser().getUsername());

                ret.add(request.getUser());
            } else {
                System.out.println("333333");
                System.out.println(request.getTrustedUser().getUsername());
                ret.add(request.getTrustedUser());
            }


        }
        System.out.println(ret);
        return ret;

    }


    //  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public List<UserResponseDTO> getAllUsersExceptRequest(HttpServletRequest req) {
        User currentUser = userService.whoami(req);
        List<UserResponseDTO> ret = new ArrayList<>();
        for (User user : userService.getAllUsersExceptRequest(currentUser)) {
            ret.add(modelMapper.map(user, UserResponseDTO.class));
        }

        return ret;

    }


    @PostMapping("/update")
    public void update(HttpServletRequest req,@RequestBody UserDataDTO userDataDTO)
    {
       // User whoami = userService.whoami(req);
        User whoami = userService.getUserById(1);
whoami.setEmail(userDataDTO.getEmail());
whoami.setUsername(userDataDTO.getUsername());
whoami.setFirstname(userDataDTO.getFirstname());
whoami.setLastname(userDataDTO.getLastname());
whoami.setPassword(passwordEncoder.encode(userDataDTO.getPassword()));
userService.save(whoami);


    }



}
