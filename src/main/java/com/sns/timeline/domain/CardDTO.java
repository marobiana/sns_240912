package com.sns.timeline.domain;

import com.sns.comment.domain.Comment;
import com.sns.post.entity.PostEntity;
import com.sns.user.entity.UserEntity;
import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

// 글 1개 == 카드 1개
@Data
public class CardDTO {
    // 글 1개
    private PostEntity post;
    
    // 글쓴이 정보
    private UserEntity user;
    
    // 댓글 N개
    private List<Comment> commentList;
    
    // 좋아요 N개 -> 숫자
}
