package com.cy.pj.sys.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties( prefix="page.configure")
public class PageProperties {
    private Integer pageSize;

}
