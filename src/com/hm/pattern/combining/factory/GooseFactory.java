package com.hm.pattern.combining.factory;

/**
 * Created by dumingwei on 2018/5/14 0014.
 */
public class GooseFactory extends AbstractGooseFactory {

    @Override
    public Quackable createGoose() {
        return new GooseAdapter(new Goose());
    }
}
