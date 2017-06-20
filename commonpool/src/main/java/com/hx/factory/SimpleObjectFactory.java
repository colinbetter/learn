/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.factory;

import com.hx.object.SimpleObject;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * Created by testuser on 17-3-3.
 * BasePooledObjectFactory
 * KeyedPooledObjectFactory
 */
public class SimpleObjectFactory implements PooledObjectFactory<SimpleObject> {

    @Override
    public PooledObject<SimpleObject> makeObject() throws Exception {
        return new DefaultPooledObject<>(new SimpleObject());
    }

    @Override
    public void destroyObject(PooledObject<SimpleObject> p) throws Exception {
        SimpleObject simpleObject = p.getObject();
        simpleObject.destroy();

    }

    @Override
    public boolean validateObject(PooledObject<SimpleObject> p) {
        p.getObject().validate();
        return true;
    }

    @Override
    public void activateObject(PooledObject<SimpleObject> p) throws Exception {
        p.getObject().active();
    }

    @Override
    public void passivateObject(PooledObject<SimpleObject> p) throws Exception {
        p.getObject().passivate();
    }
}
