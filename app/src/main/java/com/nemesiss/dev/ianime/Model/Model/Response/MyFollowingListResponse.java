package com.nemesiss.dev.ianime.Model.Model.Response;

import java.util.List;

public class MyFollowingListResponse {
    private List<MyFollowingInfoResponse> myFollowingInfoResponses;

    public List<MyFollowingInfoResponse> getMyFollowingInfoResponses() {
        return myFollowingInfoResponses;
    }

    public void setMyFollowingInfoResponses(List<MyFollowingInfoResponse> myFollowingInfoResponses) {
        this.myFollowingInfoResponses = myFollowingInfoResponses;
    }
}
