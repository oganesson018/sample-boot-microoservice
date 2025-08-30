package lab.microservice.hello;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;

@FeignClient(name = "user-service")
@LoadBalancerClient(name = "user-service", configuration = LoadBalancerConfig.class)
public interface UserServiceProxy {
    @GetMapping("/api/users/{id}")
    User retrieveUsername(@PathVariable Long id);
}
