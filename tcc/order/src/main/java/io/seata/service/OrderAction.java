package io.seata.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author mingdao
 */
@LocalTCC
public interface OrderAction {

    /**
     * Prepare boolean.
     *
     * @param actionContext the action context
     * @param user
     * @return the boolean
     */
    @TwoPhaseBusinessAction(name = "OrderAction", commitMethod = "commit", rollbackMethod = "rollback")
    boolean prepare(BusinessActionContext actionContext, @BusinessActionContextParameter(paramName = "user") String user);

    /**
     * Commit boolean.
     *
     * @param actionContext the action context
     * @return the boolean
     */
    boolean commit(BusinessActionContext actionContext);

    /**
     * Rollback boolean.
     *
     * @param actionContext the action context
     * @return the boolean
     */
    boolean rollback(BusinessActionContext actionContext);
}
