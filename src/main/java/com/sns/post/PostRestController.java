package com.sns.post;

import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class PostRestController {
    private final PostBO postBO;

    /**
     * 글쓰기 API
     * @param content
     * @param file
     * @param session
     * @return
     */
    @PostMapping("/create")
    public Map<String, Object> create(
            @RequestParam(value = "content", required = false) String content,
            @RequestParam("file") MultipartFile file,
            HttpSession session
    ) {
        Integer userId = (Integer)session.getAttribute("userId");
        String userLoginId = (String)session.getAttribute("userLoginId");

        // 로그인 검사
        Map<String, Object> result = new HashMap<>();
        if (userId == null) {
            result.put("code", 403); // 비로그인 상태
            result.put("error_message", "로그인을 해주세요.");
            return result;
        }

        // 글쓰기
        PostEntity post = postBO.addPost(userId, userLoginId, content, file);

        // 응답
        if (post != null) {
            result.put("code", 200);
            result.put("result", "성공");
        } else {
            // 파일 업로드 실패
            result.put("code", 500);
            result.put("error_message", "글을 쓰는데 실패했습니다.");
        }
        return result;
    }

    @DeleteMapping("/delete")
    public Map<String, Object> delete(
            @RequestParam("postId") int postId,
            HttpSession session
    ) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer)session.getAttribute("userId");
        if (userId == null) {
            result.put("code", 403);
            result.put("error_message", "로그인이 필요합니다.");
            return result;
        }

        postBO.deletePostByPostId(postId);

        result.put("code", 200);
        result.put("result", "성공");
        return result;
    }
}
