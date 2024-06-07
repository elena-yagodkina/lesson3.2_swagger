package Swagger.lesson3.__swagger.service;

import Swagger.lesson3.__swagger.model.Avatar;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AvatarService {
    void uploadAvatar(Long studentId, MultipartFile file) throws IOException;

    Avatar findAvatar(Long studentId);

    String getExtensions(String fileName);
}
