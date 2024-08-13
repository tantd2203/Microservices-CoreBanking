package com.ojt.kbl.config.vault;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class AppService {
    private final SpringConfigDatasource springConfig;
    @PostConstruct
    public void readConfigs() {
        log.info("Spring configuration {} - {}", springConfig.getPassword());
        log.info("Spring configuration {} - {}", springConfig.getUsername());
        log.info("Spring configuration {} - {}", springConfig.getDatabase());


    }
}