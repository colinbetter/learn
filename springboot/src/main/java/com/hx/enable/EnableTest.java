

package com.hx.enable;

import com.hx.springanalysis.bean.A;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(MySelector.class)
@interface EnableA {
    String value() default "";
}

/**
 * Created by testuser on 17-2-6.
 */
@EnableA("a")
@SpringBootApplication
public class EnableTest {
    public static void main(String args[]) {
        SpringApplication.run(EnableTest.class, args);
    }
}

class MySelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map map = importingClassMetadata.getAnnotationAttributes(EnableA.class.getName(), true);
        System.out.println(map);
        return new String[]{A.class.getName()};
    }
}