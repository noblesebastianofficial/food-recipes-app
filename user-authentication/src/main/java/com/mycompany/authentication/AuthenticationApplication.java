
package com.mycompany.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

/**
 * @author Noble Sebastian.
 * @version 1.0.1.0
 */
@SpringBootApplication
@EntityScan(basePackageClasses = {
        AuthenticationApplication.class,
        Jsr310JpaConverters.class
})
@EnableZuulProxy
@EnableEurekaServer
public class AuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }


}
