package com.trips.busservice.utils.generators;

import com.trips.busservice.data.entity.BusEntity;
import com.trips.busservice.utils.Util;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomBusIdGenerator implements IdentifierGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        BusEntity busEntity = (BusEntity) object;
        return Util.generateBusId(busEntity.getOperator().getOperatorName(), busEntity.getBusName());
    }
}
