package com.sns.timeline.bo;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.CommentDTO;
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

    // i: X
    // o: List<CardDTO>
    public List<CardDTO> generateCardList() {
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

            // !!!!!!!!!! list에 꼭 담기
            cardList.add(card);
        }

        return cardList;
    }
}
