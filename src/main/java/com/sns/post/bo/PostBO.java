package com.sns.post.bo;

import com.sns.common.FileManagerService;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostBO {
    private final PostRepository postRepository;
    private final FileManagerService fileManager;

    public List<PostEntity> getPostList() {
        return postRepository.findAllByOrderByIdDesc();
    }

    // i: userId, userLoginId, content, file   => save()
    // o: PostEntity or null(파일업로드 실패)
    public PostEntity addPost(int userId, String userLoginId, String content, MultipartFile file) {
        // 이미지 업로드 후 imagePath 받아옴(필수)
        String imagePath = fileManager.uploadFile(file, userLoginId);

        if (imagePath == null) { // 파일업로드 실패
           return null;
        }

        // save는 실패하면 null이 아니라 exception이다.(그 처리는 생략했음)
        return postRepository.save(
                PostEntity.builder()
                        .userId(userId)
                        .content(content)
                        .imagePath(imagePath)
                        .build()
        );
    }
}
