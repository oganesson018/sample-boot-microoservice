package lab.microservice.hello;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;

@Configuration
// @LoadBalancerClient(name = "user-service")
public class LoadBalancerConfig {

    /**
     * Configure round-robin load balancer that only forwards to running services
     * Spring Cloud LoadBalancer automatically handles health checking and only routes to healthy instances
     */
    @Bean
	public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(
			ConfigurableApplicationContext context) {
		System.out.println("Configuring Load balancer to prefer same instance");
		return ServiceInstanceListSupplier.builder().withBlockingDiscoveryClient()
				.build(context);
	}
    
}
