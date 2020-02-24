package io.seata.service;

import com.alibaba.fastjson.JSONObject;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderActionImpl implements OrderAction {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderActionImpl.class);

    @Override
    public boolean createOrder(BusinessActionContext actionContext, String order) {
        if (null == actionContext) {
            return false;
        }
        String xid = actionContext.getXid();
        LOGGER.info("TccActionOne prepare, xid:" + xid);

        return true;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {

        String xid = actionContext.getXid();
        LOGGER.info("TccActionOne commit, xid:" + xid);

        return false;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {

        String xid = actionContext.getXid();
        String orderNo = ((JSONObject) actionContext.getActionContext("order")).getString("orderNo");
        LOGGER.info("TccActionOne rollback, xid:" + xid);
        return false;
    }
}
