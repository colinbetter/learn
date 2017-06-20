/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.implement;

/**
 * Created by testuser on 17-4-26.
 */
public class App {

}

class A {

}

class B extends A {

}

class AA {
    protected A doA() {
        return new A();
    }
}

class BB extends AA {
    @Override
    public B doA() {
        return new B();
    }
}