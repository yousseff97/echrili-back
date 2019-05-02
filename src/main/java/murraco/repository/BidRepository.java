package murraco.repository;

import murraco.model.Bid;
import murraco.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid,Integer> {

    List<Bid> getAllByTask(Task task);

}
