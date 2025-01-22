package com.sns.timeline.bo;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.CommentDTO;
import com.sns.like.bo.LikeBO;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.timeline.domain.CardDTO;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TimelineBO {
    private final PostBO postBO;
    private final UserBO userBO;
    private final CommentBO commentBO;
    private final LikeBO likeBO;

    // i: userId(null 가능) - 비로그인 사용자도 타임라인 볼 수 있음
    // o: List<CardDTO>
    public List<CardDTO> generateCardList(Integer userId) {
        List<CardDTO> cardList = new ArrayList<>();

        // 글 목록 가져옴
        List<PostEntity> postList = postBO.getPostList();

        // 반복문 => PostEntity -> CardDTO     => 리스트에 넣는다.
        for (PostEntity postEntity : postList) {
            CardDTO card = new CardDTO();

            // 글 1개
            card.setPost(postEntity);

            // 글쓴이
            UserEntity user = userBO.getUserEntityById(postEntity.getUserId());
            card.setUser(user);

            // 댓글 N개
            List<CommentDTO> commentList = commentBO.generateCommentListByPostId(postEntity.getId());
            card.setCommentList(commentList);

            // 좋아요 개수
            int likeCount = likeBO.getLikeCountByPostId(postEntity.getId());
            card.setLikeCount(likeCount);

            // 좋아요 여부 - true(채워진 하트), false(비워진 하트)
            card.setFilledLike(likeBO.isFilledLikeByPostIdUserId(postEntity.getId(), userId));

            // !!!!!!!!!! list에 꼭 담기
            cardList.add(card);
        }

        return cardList;
    }
}
