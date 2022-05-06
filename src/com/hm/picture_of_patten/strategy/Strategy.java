package com.hm.picture_of_patten.strategy;

/**
 * Created by dumingwei on 2022/5/6
 * <p>
 * Desc:
 */
public interface Strategy {

    public abstract Hand nextHand();

    public abstract void study(boolean win);
}
