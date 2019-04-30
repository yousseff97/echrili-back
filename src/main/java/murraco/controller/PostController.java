package murraco.controller;

import javax.servlet.http.HttpServletRequest;

import murraco.dto.MyUserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import murraco.dto.UserDataDTO;
import murraco.dto.UserResponseDTO;
import murraco.model.Post;
import murraco.service.PostService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/posts")
@Api(tags = "posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private ModelMapper modelMapper;


    @DeleteMapping(value = "/{post}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${PostController.delete}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The post doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public String delete(@ApiParam("PostID") @PathVariable String id) {
        postService.delete(id);
        return id;
    }


}
