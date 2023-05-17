package com.app.babybaby.service.member.follow;

public interface FollowService {
    public void saveFollow(Long followId, Long followerId);

    public void deleteFollow(Long followId, Long followerId);
}
