package com.hm.picture_of_patten.abstract_factory.Sample.factory;

/**
 * Created by dumingwei on 2022/5/3.
 * <p>
 * Desc:扮演的角色：抽象工厂
 */
public abstract class Factory {

    public static Factory getFactory(String classname) {
        Factory factory = null;
        try {
            factory = (Factory) Class.forName(classname).newInstance();
        } catch (ClassNotFoundException e) {
            System.err.println("没有找到 " + classname + "类。");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factory;
    }

    //生成零件的抽象方法
    public abstract Link createLink(String caption, String url);

    //生成零件的抽象方法
    public abstract Tray createTray(String caption);

    //生成产品的抽象方法
    public abstract Page createPage(String title, String author);

}
