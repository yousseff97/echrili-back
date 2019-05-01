package murraco.service;

import murraco.model.Bid;
import murraco.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidService {

    @Autowired
    BidRepository bidRepository;

    public void save(Bid bid) {

        bidRepository.save(bid);

    }
}
