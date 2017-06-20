

package com.hx.pipeline;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.List;

/**
 * Created by testuser on 17-2-28.
 */
public class PipelineTest {
    public static void txPipeline(Jedis jedis) {
        String key = "pipeline-test";
        String old = jedis.get(key);
        if (old != null) {
            System.out.println("Key:" + key + ",old value:" + old);
        }
        Pipeline p1 = jedis.pipelined();
        //p1.multi();//开启事务
        p1.incr(key);
        System.out.println("Request incr");
        p1.incr(key);
        p1.set("sdf", "1");
        System.out.println("Request incr");
        List<Object> responses = p1.syncAndReturnAll();//提交事务
        p1.sync();//关闭pipeline
        //结束pipeline，并开始从相应中获得数据
        //List<Object> responses = txresult.get();
        if (responses == null || responses.isEmpty()) {
            throw new RuntimeException("Pipeline error: no response...");
        }
        for (Object resp : responses) {
            System.out.println("Response:" + resp.toString());//注意，此处resp的类型为Long
        }
    }

    public static void test(String[] args) {
        Jedis jedis = new Jedis("172.27.12.124", 6379);
        txPipeline(jedis);
    }
}
