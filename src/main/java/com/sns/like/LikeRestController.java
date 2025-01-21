package com.sns.like;

import com.sns.like.bo.LikeBO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class LikeRestController {
    private final LikeBO likeBO;

    // GET: /like?postId=13      @RequestParam("postId")
    // GET: /like/13             @PathVariable(name = "postId")
    @GetMapping("/like/{postId}")
    public Map<String, Object> likeToggle(
            @PathVariable(name = "postId") int postId,
            HttpSession session
    ) {
        // 로그인 확인
        Integer userId = (Integer)session.getAttribute("userId");
        Map<String, Object> result = new HashMap<>();
        if (userId == null) {
            result.put("code", 403);
            result.put("error_message", "좋아요를 하려면 로그인을 해주세요.");
            return result;
        }

        // 토글 로직 BO
        likeBO.toggleLike(postId, userId);

        // 응답값
        result.put("code", 200);
        result.put("result", "성공");
        return result;
    }
}
