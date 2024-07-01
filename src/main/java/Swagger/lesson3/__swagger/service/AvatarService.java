package Swagger.lesson3.__swagger.service;

import Swagger.lesson3.__swagger.model.Avatar;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

public interface AvatarService {
    void uploadAvatar(Long studentId, MultipartFile file) throws IOException;

    Avatar findAvatar(Long studentId);

    Collection<Avatar> getAll(Integer pageNumber, Integer pageSize);
}
