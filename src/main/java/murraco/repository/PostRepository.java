package murraco.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import murraco.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    boolean existsById(int id);

    Post findById(int id);

    @Transactional
    void deleteById(int id);



}
