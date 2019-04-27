package murraco.service;

import murraco.model.Request;
import murraco.model.User;
import murraco.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;


    public void save(Request addFriendRequest) {
        requestRepository.save(addFriendRequest);
    }


    public Request getRequestByFirstUserAndSecondUser(User firstUser, User secondUser) {
//        Request ret = null;
//        ret = requestRepository.getFirstByFirstUserAndSecondUser(firstUser, secondUser);
//        if (ret != null) {
//        } else {
//            ret = requestRepository.getFirstByFirstUserAndSecondUser(secondUser, firstUser);
//        }
//        return ret;

        return requestRepository.getFirstByFirstUserAndSecondUserAndStatusIsFalse(firstUser,secondUser);

    }

    public Request getRequestByFirstUserOrSecondUser(User firstUser, User secondUser) {

        return requestRepository.getFirstByFirstUserAndSecondUserAndStatusIsFalse(firstUser,secondUser);

    }

    public List<Request> getFriends(User user) {
        System.out.println("waaaaaaaa");
        List<Request> ret = new ArrayList<>();
        List<Request> firstList = requestRepository.getAllByFirstUserAndStatusIsTrue(user);
        List<Request> secondList = requestRepository.getAllBySecondUserAndStatusIsTrue(user);
        System.out.println("ffffffffff");
        System.out.println(firstList);
        System.out.println("sssssssss");
        System.out.println(secondList);
        ret.addAll(firstList);
        ret.addAll(secondList);
        System.out.println("rrrrrrrr");
        System.out.println(ret);
        return ret;
    }


    public List<Request> getRequestsBySecondUserAndStatusIsFalse(User whoami) {


        return requestRepository.getAllBySecondUserAndStatusIsFalse(whoami);
    }
}
