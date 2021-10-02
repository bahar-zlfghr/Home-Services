package ir.maktab.configuration.properties;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class DatabaseProperties {
    private final Environment environment;

    public DatabaseProperties(Environment environment) {
        this.environment = environment;
    }

    private String driverClass;
    private String url;
    private String username;
    private String password;
    private String hbm2ddl;
    private String showSql;
    private String formatSql;

    @PostConstruct
    public void afterPropertiesSet() {
        driverClass = environment.getRequiredProperty("hibernate.connection.driver_class");
        url = environment.getRequiredProperty("hibernate.connection.url");
        username = environment.getRequiredProperty("hibernate.connection.username");
        password = decodePassword();
        hbm2ddl = environment.getRequiredProperty("hibernate.hbm2ddl.auto");
        showSql = environment.getRequiredProperty("hibernate.show_sql");
        formatSql = environment.getRequiredProperty("hibernate.format_sql");
    }

    private String decodePassword() {
        String pass = environment.getRequiredProperty("hibernate.connection.password");
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(pass));
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getHbm2ddl() {
        return hbm2ddl;
    }

    public String getShowSql() {
        return showSql;
    }

    public String getFormatSql() {
        return formatSql;
    }
}
