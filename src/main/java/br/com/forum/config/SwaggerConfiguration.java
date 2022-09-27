package br.com.forum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.forum.model.Usuario;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {
	
	@Bean
	public Docket forumApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.forum"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.ignoredParameterTypes(Usuario.class);
	}
	
	@SuppressWarnings("unused")
	private ApiInfo apiInfoMetaData() {

        return new ApiInfoBuilder().title("NAME OF SERVICE")
                .description("API Endpoint Decoration")
                .contact(new Contact("Dev-Team", "https://www.dev-team.com/", "dev-team@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build()
                	;
    }

}
