package edu.utn.APIRestElectricDistribution.Config;

import edu.utn.APIRestElectricDistribution.Session.ClientSession;
import edu.utn.APIRestElectricDistribution.Session.EmployeeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@org.springframework.context.annotation.Configuration
@PropertySource("application.properties")
@EnableScheduling
@EnableCaching
public class Configuration {

    @Autowired
    ClientSession clientSession;

    @Autowired
    EmployeeSession employeeSession;

    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(clientSession);
        registration.addUrlPatterns("/api/web/*");
        return registration;
    }

    @Bean
    public FilterRegistrationBean myFilterBis() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(employeeSession);
        registration.addUrlPatterns("/api/backoffice/*");
        return registration;
    }
}
