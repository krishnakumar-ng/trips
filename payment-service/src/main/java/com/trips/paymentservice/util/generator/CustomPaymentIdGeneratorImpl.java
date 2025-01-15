package com.trips.paymentservice.util.generator;

import com.trips.paymentservice.util.Utils;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomPaymentIdGeneratorImpl implements IdentifierGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        String prefix = "pay_";
        String randomId = Utils.generateRandomId(20);
        return prefix + randomId;
    }
}