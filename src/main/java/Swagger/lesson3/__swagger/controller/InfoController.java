package Swagger.lesson3.__swagger.controller;

import Swagger.lesson3.__swagger.service.InfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/port")
@RestController
public class InfoController {
    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping
    public Integer getPortInfo() {
        return infoService.getPort();
    }
}
