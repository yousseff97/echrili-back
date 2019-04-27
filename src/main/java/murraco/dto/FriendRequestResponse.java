package murraco.dto;

import murraco.model.User;

public class FriendRequestResponse {

   private int requestId;

   private User user;

    public FriendRequestResponse(int requestId, User user) {
        this.requestId = requestId;
        this.user = user;
    }

    public int getRequestId() {

        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
