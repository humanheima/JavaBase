package com.hm.pattern.command;

/**
 * Created by dumingwei on 2018/4/24 0024.
 */
public interface Command {

     void execute();

     default void undo(){

     }
}
