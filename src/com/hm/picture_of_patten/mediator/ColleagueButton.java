package com.hm.picture_of_patten.mediator;

import java.awt.Button;

/**
 * Created by dumingwei on 2022/5/9
 * <p>
 * Desc:
 */
public class ColleagueButton extends Button implements Colleague {
    private Mediator mediator;

    public ColleagueButton(String caption) {
        super(caption);
    }

    public void setMediator(Mediator mediator) {            // 保存Mediator
        this.mediator = mediator;
    }

    public void setColleagueEnabled(boolean enabled) {      // Mediator下达启用/禁用的指示
        setEnabled(enabled);
    }
}
