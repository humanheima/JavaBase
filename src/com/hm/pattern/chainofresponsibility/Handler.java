package com.hm.pattern.chainofresponsibility;

/**
 * Created by dumingwei on 2018/2/23 0023.
 * 抽象处理者
 */
public abstract class Handler {

    /**
     * 持有后继的责任对象
     */
    protected Handler successor;

    /**
     * 处理聚餐费用的申请
     *
     * @param user 申请人
     * @param fee  申请的钱数
     * @return 成功或失败的具体通知
     */
    public abstract String handleFeeRequest(String user, double fee);

    /**
     * 取值方法
     */
    public Handler getSuccessor() {
        return successor;
    }

    /**
     * 赋值方法，设置后继的责任对象
     */
    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }
}
