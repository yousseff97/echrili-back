package murraco.repository;

import murraco.model.Task;
import murraco.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    boolean existsById(Integer id);

    Task findById(Integer id);

    @Query("SELECT t from Task t WHERE  not exists (select t1 from Task t1,Bid b where b.task=t1 and b.user=:user and t1=t)")
    List<Task> getAllWithoutBid(@Param("user") User user);


    @Transactional
    void deleteById(Integer id);


    List<Task> getAllByUser(User user);


}
