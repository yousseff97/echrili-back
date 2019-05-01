package murraco.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import murraco.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    boolean existsById(Integer id);

    Post findById(Integer id);

    @Transactional
    void deleteById(Integer id);



}
