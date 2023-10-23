package com.rudy.ryanto.core.report.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import static com.rudy.ryanto.core.report.util.ReportConstant.UTF_8;
import static org.thymeleaf.templatemode.TemplateMode.HTML;

@Configuration
public class ClassTemplateResolver {
    @Bean
    public ClassLoaderTemplateResolver classLoaderTemplateResolver(){
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(HTML);
        templateResolver.setCharacterEncoding(UTF_8);
        return templateResolver;
    }
}