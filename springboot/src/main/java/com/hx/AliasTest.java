package com.hx;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by testuser on 17-2-6.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@interface MainBean {
    @AliasFor(annotation = Component.class, attribute = "value")
    String value() default "";

    @AliasFor(annotation = Component.class, attribute = "value")
    String beanName() default "";
}

@MainBean(beanName = "mainBean")
public class AliasTest {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AliasTest.class);

        String[] beanNames = context.getBeanNamesForType(AliasTest.class);

        //当加上@AliasFor时, 输出"mainbean"
        //当去掉@AliasFor注解后, 输出"main"
        System.out.println(beanNames[0]);

        context.close();
    }


}
