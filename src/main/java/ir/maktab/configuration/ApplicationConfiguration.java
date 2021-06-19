package ir.maktab.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author : Bahar Zolfaghari
 **/
@Configuration
@ComponentScan("ir.maktab")
@PropertySource("classpath:database.properties")
@Import({SpringDataConfiguration.class, SpringDataConfiguration.class})
@EnableWebMvc
public class ApplicationConfiguration {
}
