

package com.hx.springanalysis.bean;

/**
 * Created by testuser on 17-1-3.
 */
public class A {
    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public String say() {
        return b.say();
    }
}
