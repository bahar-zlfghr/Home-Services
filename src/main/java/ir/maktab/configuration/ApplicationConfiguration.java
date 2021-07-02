package ir.maktab.configuration;

import org.springframework.context.annotation.*;

/**
 * @author : Bahar Zolfaghari
 **/
@Configuration
@ComponentScan(value = "ir.maktab")
@PropertySource({"classpath:database.properties", "classpath:messages.properties", "classpath:mail.properties"})
public class ApplicationConfiguration {
}
