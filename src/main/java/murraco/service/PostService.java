package murraco.service;

import murraco.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import murraco.exception.CustomException;
import murraco.model.Post;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void delete(Integer id) {
        postRepository.deleteById(id);
    }

    public void add(Post p) {
        postRepository.save(p);
    }

    public Post find(Integer id) {
        Post post = postRepository.findById(id);
        if (post == null) {
            throw new CustomException("The post doesn't exist", HttpStatus.NOT_FOUND);
        }
        return post;
    }
}
