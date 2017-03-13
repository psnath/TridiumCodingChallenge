package com.tridium.challenge.config;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Configuration
@EnableJpaRepositories("com.tridium.challenge.repository")
@EnableTransactionManagement
public class DatabaseConfiguration {

    @SuppressWarnings("unused")
	private final Environment env;

    public DatabaseConfiguration(Environment env) {
        this.env = env;
    }

    /**
     * Open the TCP port for the H2 database, so it is available remotely.
     *
     * @return the H2 database TCP server
     * @throws SQLException if the server failed to start
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    @Profile(Constants.SPRING_PROFILE_DEVELOPMENT)
    public Server h2TCPServer() throws SQLException {
        return Server.createTcpServer("-tcp","-tcpAllowOthers");
    }
 

    @Bean
    public Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }
}
