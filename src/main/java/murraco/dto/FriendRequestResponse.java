package murraco.dto;

import murraco.model.User;

public class FriendRequestResponse {

   private int requestId;

   private UserResponseDTO user;

    public FriendRequestResponse(int requestId, UserResponseDTO user) {
        this.requestId = requestId;
        this.user = user;
    }

    public int getRequestId() {

        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;

    }
}
