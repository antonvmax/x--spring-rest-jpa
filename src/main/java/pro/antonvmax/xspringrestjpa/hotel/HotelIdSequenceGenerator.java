package pro.antonvmax.xspringrestjpa.hotel;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

public class HotelIdSequenceGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        // проще-быстрее
        return UUID.randomUUID().toString()
                .replace("-", "")
                .substring(0, 12);
    }
}
