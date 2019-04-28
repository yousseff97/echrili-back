package murraco.service;


import murraco.model.Notification;
import murraco.model.User;
import murraco.repository.NotificationRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {


    @Autowired
    private NotificationRepository notificationRepository;

public List<Notification> getAllByUser(User user)
{
    return notificationRepository.getAllByUser(user);
}


public Notification save(Notification notification)
{

    return this.notificationRepository.save(notification);
}


public Notification getOne(int id)
{
    return this.notificationRepository.getOne(id);
}


    public void delete(int id) {
    notificationRepository.delete(id);
}
}
