package murraco.service;

import murraco.exception.CustomException;
import murraco.model.Task;
import murraco.model.User;
import murraco.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public void delete(Integer id) {
        taskRepository.deleteById(id);
    }

    public Task add(Task p) {
     return   taskRepository.save(p);
    }

    public Task find(Integer id) {
        Task post = taskRepository.findById(id);
        if (post == null) {
            throw new CustomException("The post doesn't exist", HttpStatus.NOT_FOUND);
        }
        return post;
    }

    public List<Task> getAll(User user) {

       // return this.taskRepository.findAll();
        return this.taskRepository.getAllWithoutBid(user);
    }


}
