package com.sns.comment.bo;

import com.sns.comment.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentBO {
    private final CommentMapper commentMapper;

    public int addComment(int postId, int userId, String content) {
        return commentMapper.insertComment(postId, userId, content);
    }
}
