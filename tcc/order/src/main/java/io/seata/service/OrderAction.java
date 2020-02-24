package io.seata.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface OrderAction {

    /**
     * Prepare boolean.
     *
     * @param actionContext the action context
     * @param order             the a
     * @return the boolean
     */
    @TwoPhaseBusinessAction(name = "OrderAction" , commitMethod = "commit", rollbackMethod = "rollback")
    boolean createOrder(BusinessActionContext actionContext, @BusinessActionContextParameter(paramName = "order") String order);

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
