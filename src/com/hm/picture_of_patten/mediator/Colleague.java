package com.hm.picture_of_patten.mediator;

/**
 * Created by dumingwei on 2022/5/9
 *
 * Desc:
 */
public interface Colleague {
    public abstract void setMediator(Mediator mediator);
    public abstract void setColleagueEnabled(boolean enabled);
}
