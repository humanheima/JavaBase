package com.hm.picture_of_patten.visitor;

/**
 * Created by dumingwei on 2022/5/8
 * <p>
 * Desc:
 */
public abstract class Visitor {

    public abstract void visit(File file);

    public abstract void visit(Directory directory);
}
