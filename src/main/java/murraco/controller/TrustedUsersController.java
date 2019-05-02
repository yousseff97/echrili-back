package murraco.controller;

import murraco.dto.UserResponseDTO;
import murraco.exception.CustomException;
import murraco.model.TrustedUsers;
import murraco.model.User;
import murraco.service.TrustedUsersService;
import murraco.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/trusted/users")
public class TrustedUsersController {

    @Autowired
    private TrustedUsersService trustedUsersService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping("/add/{id}")
    public void addTrustedUser(HttpServletRequest req, @PathVariable int id) {
        User whoami = userService.whoami(req);
      //  User whoami = userService.getUserById(1);
        User trustedUser = userService.getUserById(id);
        if (trustedUsersService.existsByUserAndTrustedUser(whoami, trustedUser)|| (whoami.getId() == id)) {
            throw new CustomException("user already trused", HttpStatus.CONFLICT);
        }

        TrustedUsers trustedUsers = new TrustedUsers();
        trustedUsers.setUser(whoami);
        trustedUsers.setTrustedUser(trustedUser);
        trustedUsersService.save(trustedUsers);

    }


    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public List<UserResponseDTO> getAllTrustedUsers(HttpServletRequest req) {
        User whoami = userService.whoami(req);
        List<TrustedUsers> trustedUsers = trustedUsersService.getAllByUser(whoami);
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
        for (TrustedUsers request : trustedUsers) {
            System.out.println(request.getUser().getUsername());
            userResponseDTOS.add(modelMapper.map(request.getTrustedUser(), UserResponseDTO.class));
        }

        return userResponseDTOS;
    }


}
