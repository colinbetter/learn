/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.pool;

import com.hx.object.SimpleObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * Created by testuser on 17-3-3.
 * 在org.apache.commons.pool2.impl中预设了三个可以直接使用的对象池：GenericObjectPool、GenericKeyedObjectPool和SoftReferenceObjectPool。
 */
public class SimplePool extends GenericObjectPool<SimpleObject> {


    public SimplePool(PooledObjectFactory<SimpleObject> factory) {
        super(factory);
    }

    public SimplePool(PooledObjectFactory<SimpleObject> factory, GenericObjectPoolConfig config) {
        super(factory, config);
    }

    public SimplePool(PooledObjectFactory<SimpleObject> factory, GenericObjectPoolConfig config, AbandonedConfig abandonedConfig) {
        super(factory, config, abandonedConfig);
    }
}
