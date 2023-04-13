package idusw.springboot.boardkjsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  //(exclude = DataSourceAutoConfiguration.class)
public class BoardKjshApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardKjshApplication.class, args);
    }

}
