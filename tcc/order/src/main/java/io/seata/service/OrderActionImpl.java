package io.seata.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author mingdao
 */
@Service
public class OrderActionImpl implements OrderAction {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderActionImpl.class);

    @Override
    public boolean prepare(BusinessActionContext actionContext, String user) {
        if (null == actionContext) {
            return false;
        }
        String xid = actionContext.getXid();
        LOGGER.error("OrderAction prepare, xid:" + xid);
        LOGGER.error("OrderAction prepare, user:" + user);

        return true;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {

        String xid = actionContext.getXid();
        LOGGER.error("OrderAction commit, xid:" + xid);
        String user = actionContext.getActionContext("user").toString();
        LOGGER.error("OrderAction commit, user:" + user);
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {

        String xid = actionContext.getXid();
        LOGGER.error("OrderAction rollback, xid:" + xid);
        String user = actionContext.getActionContext("user").toString();
        LOGGER.error("OrderAction rollback, user:" + user);
        return true;
    }
}
