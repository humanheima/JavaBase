package com.hm.anno;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Set;

/**
 * Created by dumingwei on 2017/8/6.
 */
public class HibernateAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        PrintStream ps = null;
        try {
            for (Element element : roundEnv.getElementsAnnotatedWith(Persistent.class)) {
                Name clazzName = element.getSimpleName();
                Persistent per = element.getAnnotation(Persistent.class);
                ps = new PrintStream(new FileOutputStream(clazzName + "hbm.xml"));
                ps.println("class name=" + element);
                ps.println("table=" + per.table());
                for (Element f : element.getEnclosedElements()) {
                    //只处理成员变量上的Annotation
                    if (f.getKind() == ElementKind.FIELD) {
                        Id id = f.getAnnotation(Id.class);
                        if (id != null) {
                            ps.println(f.getSimpleName() + "column=" + id.column() + ",type=" + id.type() + ",generator=" + id.generator());
                        }
                        Property p = f.getAnnotation(Property.class);
                        if (p != null) {
                            ps.println(f.getSimpleName() + "column=" + p.column() + ",type=" + p.type());
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return true;
    }
}
