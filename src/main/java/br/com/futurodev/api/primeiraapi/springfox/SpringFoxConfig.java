package br.com.futurodev.api.primeiraapi.springfox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket apiDocket(){
        return new Docket(DocumentationType.SWAGGER_2) //informa qualo tipo de documentação se vai usar
                .select() //retornamos um builder para selecionar os endpoints que devem ser expostos
                .apis(RequestHandlerSelectors.any()) //especificar o que queremos, quais controladores, endpoints que o springfox ira se conectar
                .build() //montamos nosso sumario docket
                .apiInfo(metaData())
                .tags(new Tag("Usuario", "Gerencia Usuario"));
    }

    private ApiInfo metaData(){
        return new ApiInfoBuilder()
                .title("Spring boot Rest Api")
                .description("Nossa primeira spring boot rest api")
                .version("1.0.0")
                .license("Apache license 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html") //informamos o arquivo a ser renderizado
                .addResourceLocations("classpath:/META-INF/resources/"); //informamos o caminho do arquivo

        registry.addResourceHandler("/webjars/**") //informa outros arquivos a serem renderizados, js, css, html...
                .addResourceLocations("classpath:META-INF/resources/webjars/");
    }
}
