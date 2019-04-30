package murraco.service;

import murraco.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import murraco.exception.CustomException;
import murraco.model.Post;
import murraco.security.JwtTokenProvider;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void delete(String id) {
        postRepository.deleteByID(id);
    }

    public Post search(String id) {
        Post post = postRepository.findByID(id);
        if (post == null) {
            throw new CustomException("The post doesn't exist", HttpStatus.NOT_FOUND);
        }
        return post;
    }
}
