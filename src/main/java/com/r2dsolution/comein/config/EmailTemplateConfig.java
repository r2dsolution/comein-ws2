package com.r2dsolution.comein.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class EmailTemplateConfig {
	@Bean
	public ITemplateResolver templateResolver()
	{
	    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	    templateResolver.setPrefix("templates/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode(TemplateMode.HTML);

	    return templateResolver;
	}

	@Bean
	public TemplateEngine templateEngine()
	{
	    TemplateEngine templateEngine = new TemplateEngine();
	    templateEngine.setTemplateResolver(this.templateResolver());

	    return templateEngine;
	}
}
