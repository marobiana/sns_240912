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
        if (likeMapper.selectLikeCountByPostIdUserId(postId, userId) > 0) { // 있으면
            // 삭제
            likeMapper.deleteLikeByPostIdUserId(postId, userId);
        } else { // 없으면
            // 추가
            likeMapper.insertLike(postId, userId);
        }
    }
}
