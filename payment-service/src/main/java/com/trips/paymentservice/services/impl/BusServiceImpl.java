package com.trips.paymentservice.services.impl;

import com.trips.paymentservice.data.dto.UpdateBusStatusPayload;
import com.trips.paymentservice.exception.PaymentServiceRuntimeException;
import com.trips.paymentservice.feign.clients.BusServiceClient;
import com.trips.paymentservice.services.BusService;
import com.trips.paymentservice.util.EurekaClientUtil;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BusServiceImpl implements BusService {
    @Autowired
    EurekaClientUtil eurekaClientUtil;

    @Autowired
    BusServiceClient busServiceClient;

    @Value("${services.bus-service.id}")
    String busServiceID;

    @Value("${services.bus-service.v1.api}")
    String busServiceV1;

    @Value("${services.bus-service.v1.name}")
    String busServiceV1Name;


    @Override
    public void updateBusStatus(UpdateBusStatusPayload updateBusStatusPayload) {
        log.info("calling {} Service for updating bus details for busId: {}", busServiceV1Name, updateBusStatusPayload.getId());

        // Construct URL
        String busServiceUri = eurekaClientUtil.getServiceUri(busServiceID);
        String url = busServiceUri + busServiceV1 + "/" + updateBusStatusPayload.getId() + "/booking";

        try {
            String response = busServiceClient.updateBusStatus(updateBusStatusPayload);

            log.info("response: {}", response);

            log.info("Room update successfully");
        } catch (FeignException feignException) {
            handleFeignException(feignException);
        }
    }

    private void handleFeignException(FeignException feignException) {
        log.info("Handling WebClientException");

        // Convert error response to JSONObject
        JSONObject response = new JSONObject(feignException.getMessage());

        log.info("Error response from {} Service is: {}", busServiceV1Name, response);

        String errorCode = response.getString("errorCode");
        String message = response.getString("message");
        String details = response.has("details") ? response.getString("details") : "";

        throw new PaymentServiceRuntimeException(message);
    }
}