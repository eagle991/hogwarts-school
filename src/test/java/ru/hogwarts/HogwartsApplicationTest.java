package ru.hogwarts.school;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HogwartsApplicationTest {

    @Test
    void contextLoads(ApplicationContext context) {
        // Проверяем, что контекст Spring успешно загружается
        assertThat(context).isNotNull();
    }

    @Test
    void main_shouldStartApplication() {
        // Проверяем, что метод main запускает приложение без ошибок
        HogwartsApplication.main(new String[]{});
    }
}