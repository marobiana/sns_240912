package com.sns.like.bo;

import com.sns.like.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeBO {
    private final LikeMapper likeMapper;

    public void toggleLike(int postId, int userId) {
        // 있으면 -> 삭제    없으면 -> 추가
        if (likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) > 0) { // 있으면
            // 삭제
            likeMapper.deleteLikeByPostIdUserId(postId, userId);
        } else { // 없으면
            // 추가
            likeMapper.insertLike(postId, userId);
        }
    }

    // i: postId
    // o: int(개수)
    public int getLikeCountByPostId(int postId) {
        return likeMapper.selectLikeCountByPostIdOrUserId(postId, null);
    }

    // i: postId, userId
    // o: boolean
    // true:로그인된 사람이 눌렀을 때, false:로그인 된 사람이 안 눌렀을 때 또는 비로그인
    public boolean isFilledLikeByPostIdUserId(int postId, Integer userId) {
        // 1) 비로그인 => 빈하트 false
        if (userId == null) {
            return false;
        }

        // 2) 로그인 => 누른적 있다 true
        // 2) 로그인 => 누른적 없다 false
        return likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) > 0;
    }

    public void deleteLikesByPostId(int postId) {
        likeMapper.deleteLikesByPostId(postId);
    }
}
