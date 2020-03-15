import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories
@PropertySource("application.properties")
public class DataSourceConfigTest {
    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(env.getProperty("jdbc.driverClassName"));
        dataSourceBuilder.url(env.getProperty("jdbc.url"));
        dataSourceBuilder.username(env.getProperty("jdbc.username"));
        dataSourceBuilder.password(env.getProperty("jdbc.password"));
        return dataSourceBuilder.build();
    }
}
