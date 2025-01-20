package com.sns.comment.bo;

import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentDTO;
import com.sns.comment.mapper.CommentMapper;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentBO {
    private final CommentMapper commentMapper;
    private final UserBO userBO;

    public int addComment(int postId, int userId, String content) {
        return commentMapper.insertComment(postId, userId, content);
    }

    public List<Comment> getCommentList() {
        return commentMapper.selectCommentList();
    }

    // i: postId
    // o: List<CommentDTO>
    public List<CommentDTO> generateCommentListByPostId(int postId) {
        List<CommentDTO> commentDTOList = new ArrayList<>();

        List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);

        // Comment -> CommentDTO    => CommentDTO를 리스트에 넣음
        for (Comment comment : commentList) {
            CommentDTO commentDTO = new CommentDTO();

            // 댓글 1개
            commentDTO.setComment(comment);

            // 댓글쓴이
            UserEntity user = userBO.getUserEntityById(comment.getUserId());
            commentDTO.setUser(user);

            // !!!!!!!!!!리스트에 넣기
            commentDTOList.add(commentDTO);
        }

        return commentDTOList;
    }
}
