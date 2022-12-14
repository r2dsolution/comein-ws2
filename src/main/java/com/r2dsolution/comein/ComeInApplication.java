package com.r2dsolution.comein;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/** Simple class to start up the application.
 *
 * @SpringBootApplication adds:
 *  @Configuration
 *  @EnableAutoConfiguration
 *  @ComponentScan
 */
@SpringBootApplication
public class ComeInApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ComeInApplication.class, args);
    }

}
