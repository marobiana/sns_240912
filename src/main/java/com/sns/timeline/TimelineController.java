package com.sns.timeline;

import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class TimelineController {
    private final PostBO postBO;

    @GetMapping("/timeline")
    public String timeline(Model model) {
        List<PostEntity> postList = postBO.getPostList();
        model.addAttribute("postList", postList);

        // +댓글

        return "timeline/timeline";
    }
}
