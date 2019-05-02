package murraco.service;

import murraco.model.Bid;
import murraco.model.Task;
import murraco.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidService {

    @Autowired
    BidRepository bidRepository;

    public void save(Bid bid) {

        bidRepository.save(bid);

    }

    public List<Bid> getAllByTask(Task t) {

    return bidRepository.getAllByTask(t);
    }

    public Bid getOne(int id) {
   return bidRepository.getOne(id);
    }
}
