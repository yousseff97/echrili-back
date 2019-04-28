package murraco.service;

import murraco.model.TrustedUsers;
import murraco.model.User;
import murraco.repository.TrustedUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrustedUsersService {

    @Autowired
    private TrustedUsersRepository trustedUsersRepository;


    public void save(TrustedUsers addFriendRequest) {
        trustedUsersRepository.save(addFriendRequest);
    }

//
//    public TrustedUsers getTrustedUsersByUserAndTrustedUser(User user, User trustedUser) {
//
//        return trustedUsersRepository.getFirstByUserAndTrustedUser(user,trustedUser);
//
//    }
    public Boolean existsByUserAndTrustedUser(User user, User trustedUser) {

        return trustedUsersRepository.existsByUserAndTrustedUser(user,trustedUser);

    }

    public TrustedUsers getRequestByFirstUserOrSecondUser(User firstUser, User secondUser) {

   //     return trustedUsersRepository.getFirstByFirstUserAndSecondUserAndStatusIsFalse(firstUser,secondUser);
        return null;

    }

    public List<TrustedUsers> getFriends(User user) {
//        System.out.println("waaaaaaaa");
//        List<TrustedUsers> ret = new ArrayList<>();
//        List<TrustedUsers> firstList = trustedUsersRepository.getAllByFirstUserAndStatusIsTrue(user);
//        List<TrustedUsers> secondList = trustedUsersRepository.getAllBySecondUserAndStatusIsTrue(user);
//        System.out.println("ffffffffff");
//        System.out.println(firstList);
//        System.out.println("sssssssss");
//        System.out.println(secondList);
//        ret.addAll(firstList);
//        ret.addAll(secondList);
//        System.out.println("rrrrrrrr");
//        System.out.println(ret);
//        return ret;
        return null;
    }


    public List<TrustedUsers> getAllByUser(User whoami) {


        return trustedUsersRepository.getAllByUser(whoami);
    }
}
