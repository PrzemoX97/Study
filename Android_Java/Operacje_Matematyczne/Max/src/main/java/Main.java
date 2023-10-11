import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import Rest.*;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackageClasses = Controler.class)
public class Main {
    public static void main(String[]args){
        SpringApplication.run(Main.class, args);
    }

}
