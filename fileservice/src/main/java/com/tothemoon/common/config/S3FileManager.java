package com.tothemoon.common.config;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class S3FileManager {
    public static final String POST_PATH = "post-image/";
    public static final String AVATAR_PATH = "avatar-image/";
    public static final String COMMENT_PATH = "comment-image/";

    @Value("${application.s3-info.bucket-name}")
    private String bucketName;

    public String uploadPostImage(String imageName, MultipartFile multipartFile) throws IOException {
        return upload(imageName, multipartFile, POST_PATH, true);
    }

    public String uploadAvatarImage(String imageName, MultipartFile multipartFile) throws IOException {
        return upload(imageName, multipartFile, AVATAR_PATH, true);
    }

    public String uploadCommentImageUrl(String imageName, MultipartFile multipartFile) throws IOException {
        return upload(imageName, multipartFile, COMMENT_PATH, true);
    }

    private S3ObjectResponse getFile(String objectName, String objectPath) throws Exception {
        String keyName = objectPath + objectName;
        S3ObjectResponse res = new S3ObjectResponse();
        AmazonS3 s3client = getS3Client();
        S3Object object = s3client.getObject(bucketName, keyName);

        res.setContent(IOUtils.toByteArray(object.getObjectContent()));
        res.setMediaType(object.getObjectMetadata().getContentType());
        object.close();
        return res;
    }

    private String getBaseImageUrl() {
        return "https://s3-eu-west-1.amazonaws.com/" + bucketName + "/";
    }

    private AmazonS3 getS3Client() {
        return new AmazonClientProvider().getAmazonS3();
    }

    private void deleteFile(String objectName, String objectPath) {
        String keyName = objectPath + objectName;
        AmazonS3 s3client = getS3Client();
        s3client.deleteObject(new DeleteObjectRequest(bucketName, keyName));
    }

    public void deleteAvatarImages(String filename) {
        AmazonS3 s3client = getS3Client();
        String keyName = AVATAR_PATH + filename;
        s3client.deleteObject(new DeleteObjectRequest(bucketName, keyName));
    }

    public void deleteCommentImages(String filename) {
        AmazonS3 s3client = getS3Client();
        String keyName = COMMENT_PATH + filename;
        s3client.deleteObject(new DeleteObjectRequest(bucketName, keyName));
    }


    public void deletePostImages(String filename) {
        AmazonS3 s3client = getS3Client();
        String keyName = POST_PATH + filename;
        s3client.deleteObject(new DeleteObjectRequest(bucketName, keyName));
    }

    private String upload(String objectName, MultipartFile multipartFile, String objectPath, boolean isPublic) throws IOException {
        String keyName = objectPath + objectName;
        ObjectMetadata meta = new ObjectMetadata();
        long contentLength = IOUtils.toByteArray(multipartFile.getInputStream()).length;
        meta.setContentLength(contentLength);
        meta.setContentType(multipartFile.getContentType());
        AmazonS3 s3client = getS3Client();
        // need to use multipartFile.getInputStream() to Re obtain the input stream
        PutObjectRequest request = new PutObjectRequest(bucketName, keyName, multipartFile.getInputStream(), meta);
        if (isPublic) {
            request.setCannedAcl(CannedAccessControlList.PublicRead);
        }
        s3client.putObject(request);
        return getBaseImageUrl() + objectPath + objectName;
    }

}
