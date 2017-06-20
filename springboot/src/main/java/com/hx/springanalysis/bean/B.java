

package com.hx.springanalysis.bean;

/**
 * Created by testuser on 17-1-3.
 */
public class B {
    private C c;

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }

    public String say() {
        return c.say();
    }
}
