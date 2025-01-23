package com.sns.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component // spring bean
public class FileManagerService {
    public static final String FILE_UPLOAD_PATH = "D:\\신보람\\6_spring_project\\s_images/";

    // i: file, userLoginId
    // o: imagePath
    public String uploadFile(MultipartFile file, String loginId) {
        // 폴더(디렉토리) 생성
        // 예:aaaa_17237482334/sun.png
        String directoryName = loginId + "_" + System.currentTimeMillis();
        String filePath = FILE_UPLOAD_PATH + directoryName + "/"; // D:\신보람\6_spring_project\s_images/aaaa_17237482334/

        // 폴더 생성
        File directory = new File(filePath);
        if (directory.mkdir() == false) {
            return null; // 폴더 생성시 실패하면 경로를 null로 리턴(에러 아님)
        }

        // 파일 업로드
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(filePath + file.getOriginalFilename()); // D:\신보람\6_spring_project\s_images/aaaa_17237482334/sun.png
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 이미지 업로드 시 실패하면 경로를 null 리턴(에러 아님)
        }

        // 파일업로드가 성공하면 이미지 url path 리턴
        // 주소는 이렇게 될 것이다.(예언)
        // /images/aaaa_17237482334/sun.png
        return "/images/" + directoryName + "/" + file.getOriginalFilename();
    }

    // 파일 삭제
    // input:이미지 경로(path) output:X
    public void deleteFile(String imagePath) { // /images/aaaa_1721209532586/flower-8557060_640.jpg
        // 주소에 겹치는 /images/ 를 지운다.
        Path path = Paths.get(FILE_UPLOAD_PATH + imagePath.replace("/images/", ""));

        // 삭제할 이미지가 존재하는가?
        if (Files.exists(path)) {
            // 이미지 삭제
            try {
                Files.delete(path);
            } catch (IOException e) {
                log.info("[FileManagerService 파일삭제] 삭제 실패. path:{}", path.toString());
                return;
            }

            // 폴더(디렉토리) 삭제
            path = path.getParent();
            if (Files.exists(path)) {
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    log.info("[FileManagerService 파일삭제] 디렉토리 삭제 실패 path:{}", path.toString());
                }
            }
        }
    }
}
