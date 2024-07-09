package Swagger.lesson3.__swagger.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class InfoServiceTestImpl implements InfoService{
    @Value("${server.port}")
    private Integer port;

    @Override
    public Integer getPort() {
        return port;
    }
}
