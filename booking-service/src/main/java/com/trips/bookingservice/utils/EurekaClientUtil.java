package com.trips.bookingservice.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class EurekaClientUtil {
    private DiscoveryClient discoveryClient;

    public List<ServiceInstance> getServiceInstances(String serviceName) {
        return discoveryClient.getInstances(serviceName);
    }

    public ServiceInstance getServiceInstance(String serviceName) {
        List<ServiceInstance> instances = getServiceInstances(serviceName);
        return instances.getFirst();
    }

    public String getServiceInstanceUri(ServiceInstance serviceInstance) {
        return serviceInstance.getUri().toString();
    }

    public String getServiceUri(String serviceName) {
        log.info("fetching {} URI from discovery server", serviceName);
        ServiceInstance serviceInstance = getServiceInstance(serviceName);
        String uri = getServiceInstanceUri(serviceInstance);
        log.info("fetched {} URI: {}", serviceName, uri);
        return uri;
    }
}