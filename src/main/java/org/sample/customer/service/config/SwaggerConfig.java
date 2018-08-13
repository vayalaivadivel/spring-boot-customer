package org.sample.customer.service.config;

import org.sample.customer.service.SpringBootJpaApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author vadivel
 */
@EnableSwagger2
@EnableAutoConfiguration
@Configuration
public class SwaggerConfig {
	private static final Logger LOG = LoggerFactory.getLogger(SwaggerConfig.class);
	@Value("${swagger.api.title}")
	private String title;

	@Value("${swagger.api.description}")
	private String description;

	@Value("${swagger.api.version}")
	private String version;

	@Value("${swagger.api.termsOfServiceUrl}")
	private String termsOfServiceUrl;

	@Value("${swagger.api.license}")
	private String license;

	@Value("${swagger.api.licenseUrl}")
	private String licenseUrl;

	@Value("${swagger.api.contact.name}")
	private String name;

	@Value("${swagger.api.contact.url}")
	private String url;

	@Value("${swagger.api.contact.email}")
	private String email;

	@Bean
	public Docket docketApi() {
		LOG.info("Creating Docket object Swagger-API");
		return new Docket(DocumentationType.SWAGGER_2).pathMapping("").select()
				.apis(RequestHandlerSelectors.basePackage("org.sample.customer.service.controller"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	@SuppressWarnings("deprecation")
	public ApiInfo apiInfo() {
		LOG.info("Creating ApiInfo object Swagger-API");
		return new ApiInfo(title, description, version, termsOfServiceUrl, name, license, licenseUrl);

	}
}