package com.example.routingandfilteringgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.exaample.filter.ErrorFilter;
import com.exaample.filter.Postfilter;
import com.exaample.filter.PreFilter;
import com.exaample.filter.RouteFilter;

@EnableZuulProxy
@SpringBootApplication
public class RoutingAndFilteringGatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(RoutingAndFilteringGatewayApplication.class, args);
  }
@Bean
 public PreFilter prefilter()
 {
	return new PreFilter();
 }
@Bean
public Postfilter postfilter()
{
	return new Postfilter();
}
@Bean
public RouteFilter routefilter()
{
	return new RouteFilter();
}
@Bean
public ErrorFilter errorfilter()
{
	return new ErrorFilter();
}
 

}
