package by.grsu.lookingforacompanion.configuration.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @@Callback: Описывает набор запросов
 * @@ApiResponse: Представляет ответ в операции
 * @@Hidden: Скрывает ресурс, операцию или свойство
 * @@RequestBody: Представляет тело запроса в операции
 * @@Schema: Позволяет определять входные и выходные данные.
 * @@Parameter: Представляет один параметр в операции OpenAPI.
 * @@Tag: Представляет теги для операции или определения OpenAPI.
 * @@Link: Представляет возможную ссылку времени разработки для ответа.
 * @@Server: Представляет серверы для операции или для определения OpenAPI.
 * @@Content: Предоставляет схему и примеры для определенного типа мультимедиа.
 * @@Operation: Описывает операцию или обычно метод HTTP для определенного пути.
 * @@ArraySchema: Позволяет определять входные и выходные данные для типов массивов.
 */
@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPIDescription(@Value("${swagger.title}") String title,
                                            @Value("${swagger.app-version}") String version,
                                            @Value("${swagger.description}") String description,
                                            @Value("${swagger.contact.backend.name}") String contactName,
                                            @Value("${swagger.contact.backend.email}") String contactEmail) {
        return new OpenAPI().info(
                new Info().title(title)
                        .version(version)
                        .description(description)
                        .contact(new Contact()
                                .name(contactName)
                                .email(contactEmail))
        );
    }

}
