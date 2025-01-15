package com.trips.bookingservice.utils.generator;

import com.trips.bookingservice.utils.Utils;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomBookingRequestIdGeneratorImpl implements IdentifierGenerator{
    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        String prefix = "bk_req_";
        String randomId = Utils.generateRandomId(20);
        return prefix + randomId;
    }
}
