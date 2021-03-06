package jdev.tracker;

import jdev.dto.repo.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("jdev.dto")
@EntityScan(basePackageClasses = jdev.dto.State.class)
public class Main implements CommandLineRunner {

    private final StateRepository stateRepository;

    @Autowired
    public Main(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public static void main(String... args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Очищаем таблицу
        stateRepository.deleteAll();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
