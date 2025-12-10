package com.rubypaper.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rubypaper.constant.ItemCategory;

@Service
public class FileService {

    @Value("${upload.path}")
    private String uploadPath;

    public String saveImage(MultipartFile file, ItemCategory category) throws Exception {

        if (file.isEmpty()) {
            throw new Exception("이미지 파일이 없습니다.");
        }

        // 폴더 구조 생성
        String folder = uploadPath + category.toString().toLowerCase() + "/";

        Path folderPath = Paths.get(folder).toAbsolutePath();
        Files.createDirectories(folderPath);

        // 파일명 생성 (UUID + 원본명)
        String originalName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String savedName = uuid + "_" + originalName;

        Path savePath = folderPath.resolve(savedName);

        Files.copy(file.getInputStream(), savePath, StandardCopyOption.REPLACE_EXISTING);

        return savedName;
    }
}
