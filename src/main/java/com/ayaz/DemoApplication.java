package com.ayaz;

import com.ayaz.domain.Student;
import com.ayaz.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext contex = SpringApplication.run(DemoApplication.class, args);
        StudentService service = contex.getBean(StudentService.class);

    }

}
