package com.tothemoon.app.service;

import com.bird.enums.FileType;
import com.tothemoon.app.feign.client.AuthFeignClient;
import com.tothemoon.common.config.S3FileManager;
import com.tothemoon.common.entity.FileInfo;
import com.tothemoon.common.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FileService {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private AuthFeignClient authFeignClient;
    private static final List<String> allowedImageContentTypes = Arrays.asList("image/jpg", "image/jpeg", "image/png", "image/gjf");
    private final S3FileManager s3FileManager;


    @SneakyThrows
    public FileInfo uploadProfileImage(MultipartFile imageFile) {
        if (isImage(imageFile)) {
            return null;
        }
        String fileName = generateUniqueFileName(imageFile.getOriginalFilename());
        String url = s3FileManager.uploadAvatarImage(fileName, imageFile);
        String original_logo = authFeignClient.getAndUpdateMemberProfileImage(url).getBody();
        deleteOriginalImage(original_logo, FileType.AVATAR);
        return saveFile(imageFile, url, fileName);
    }

    @SneakyThrows
    public String uploadCommentImage(MultipartFile imageFile) {
        if (isImage(imageFile)) {
            return null;
        }
        String fileName = generateUniqueFileName(imageFile.getOriginalFilename());
        return s3FileManager.uploadCommentImageUrl(fileName, imageFile);
    }

    @SneakyThrows
    public FileInfo uploadPostImage(MultipartFile imageFile) {
        if (isImage(imageFile)) {
            return null;
        }
        String fileName = generateUniqueFileName(imageFile.getOriginalFilename());
        String url = s3FileManager.uploadPostImage(fileName, imageFile);
        return saveFile(imageFile, url, fileName);
    }

    @SneakyThrows
    public void deletePostImage(String imageUrl) {
        deleteOriginalImage(imageUrl, FileType.POST);
    }

    @SneakyThrows
    public void deleteCommentImage(String imageUrl) {
        deleteOriginalImage(imageUrl, FileType.COMMENT);
    }

    private FileInfo saveFile(MultipartFile multipartFile, String url, String fileName) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setUrl(url);
        fileInfo.setFileName(fileName);
        fileInfo.setContentType(multipartFile.getContentType());
        fileInfo.setFileSize(multipartFile.getSize());
        fileInfo.setOriginalName(multipartFile.getOriginalFilename());
        return fileRepository.save(fileInfo);
    }

    private void deleteFile(String url) {
        fileRepository.deleteByUrl(url);
    }


    private void deleteOriginalImage(String original_logo, FileType type) {
        if (original_logo != null && !original_logo.isEmpty()) {
            String keyName = original_logo.substring(original_logo.lastIndexOf("/") + 1);
            switch (type) {
                case POST:
                    s3FileManager.deletePostImages(keyName);
                    break;
                case AVATAR:
                    s3FileManager.deleteAvatarImages(keyName);
                    break;
                case COMMENT:
                    s3FileManager.deleteCommentImages(keyName);
                    break;
            }
            deleteFile(original_logo);
        }
    }

    private String generateUniqueFileName(String originalFileName) {
        String uniqueId = UUID.randomUUID().toString();
        String fileExtension = StringUtils.getFilenameExtension(originalFileName);
        return uniqueId + "." + fileExtension;
    }

    private boolean isImage(MultipartFile imageFile) {
        return imageFile.isEmpty() || !allowedImageContentTypes.contains(imageFile.getContentType());
    }


}