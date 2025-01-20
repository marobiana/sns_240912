package com.sns.timeline;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.Comment;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.domain.CardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class TimelineController {
//    private final PostBO postBO;
//    private final CommentBO commentBO;
    private final TimelineBO timelineBO;

    @GetMapping("/timeline")
    public String timeline(Model model) {
        List<CardDTO> cardList = timelineBO.generateCardList();
        model.addAttribute("cardList", cardList);

//        List<PostEntity> postList = postBO.getPostList();
//        List<Comment> commentList = commentBO.getCommentList();
//        model.addAttribute("postList", postList);
//        model.addAttribute("commentList", commentList);

        return "timeline/timeline";
    }
}
