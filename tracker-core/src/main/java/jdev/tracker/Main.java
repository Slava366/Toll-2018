package jdev.tracker;

import jdev.dto.PointDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String... args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(InjectionContext.class);
    }
}
