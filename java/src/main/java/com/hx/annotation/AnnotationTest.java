/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.reflect.Method;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)  //可以通过反射读取注解
@Inherited
@interface MyAnnotation {
    String value();
}

/**
 * Created by testuser on 17-2-6.
 */
public class AnnotationTest {
    public static void main(String[] args) throws SecurityException,
            NoSuchMethodException {

        Class<SubClass> clazz = SubClass.class;

        if (clazz.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation cla = clazz
                    .getAnnotation(MyAnnotation.class);
            System.out.println("子类继承到父类类上Annotation,其信息如下：" + cla.value());
        } else {
            System.out.println("子类没有继承到父类类上Annotation");
        }

        // 实现抽象方法测试
        Method method = clazz.getMethod("abstractMethod", new Class[]{});
        if (method.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation ma = method
                    .getAnnotation(MyAnnotation.class);
            System.out.println("子类实现父类的abstractMethod抽象方法，继承到父类抽象方法中的Annotation,其信息如下：" + ma.value());
        } else {
            System.out.println("子类实现父类的abstractMethod抽象方法，没有继承到父类抽象方法中的Annotation");
        }

        //覆盖测试
        Method methodOverride = clazz.getMethod("doExtends", new Class[]{});
        if (methodOverride.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation ma = methodOverride
                    .getAnnotation(MyAnnotation.class);
            System.out
                    .println("子类继承父类的doExtends方法，继承到父类doExtends方法中的Annotation,其信息如下：" + ma.value());
        } else {
            System.out.println("子类继承父类的doExtends方法，没有继承到父类doExtends方法中的Annotation");
        }

        //继承测试
        Method method3 = clazz.getMethod("doHandle", new Class[]{});
        if (method3.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation ma = method3
                    .getAnnotation(MyAnnotation.class);
            System.out
                    .println("子类覆盖父类的doHandle方法，继承到父类doHandle方法中的Annotation,其信息如下：" + ma.value());
        } else {
            System.out.println("子类覆盖父类的doHandle方法，没有继承到父类doHandle方法中的Annotation");
        }
    }
}

@MyAnnotation(value = "类名上的注解")
abstract class ParentClass {

    @MyAnnotation(value = "父类的abstractMethod方法")
    public abstract void abstractMethod();

    @MyAnnotation(value = "父类的doExtends方法")
    public void doExtends() {
        System.out.println(" ParentClass doExtends ...");
    }

    @MyAnnotation(value = "父类的doHandle方法")
    public void doHandle() {
        System.out.println(" ParentClass doHandle ...");
    }
}

class SubClass extends ParentClass {

    //子类实现父类的抽象方法
    @Override
    public void abstractMethod() {
        System.out.println("子类实现父类的abstractMethod抽象方法");
    }

    //子类继承父类的doExtends方法

    //子类覆盖父类的doHandle方法
    @Override
    public void doHandle() {
        System.out.println("子类覆盖父类的doHandle方法");
    }
}