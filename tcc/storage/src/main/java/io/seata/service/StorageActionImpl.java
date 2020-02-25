package io.seata.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author mingdao
 */
@Service
public class StorageActionImpl implements StorageAction {

    private final static Logger LOGGER = LoggerFactory.getLogger(StorageActionImpl.class);

    @Override
    public boolean prepare(BusinessActionContext actionContext, String order) {
        if (null == actionContext) {
            return false;
        }
        String xid = actionContext.getXid();
        LOGGER.error("StorageAction prepare, xid:" + xid);

        return true;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {

        String xid = actionContext.getXid();
        LOGGER.error("StorageAction commit, xid:" + xid);

        String order = actionContext.getActionContext("order").toString();
        LOGGER.error("StorageAction commit, order:" + order);

        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {

        String xid = actionContext.getXid();
        LOGGER.error("StorageAction rollback, xid:" + xid);
        return true;
    }
}
