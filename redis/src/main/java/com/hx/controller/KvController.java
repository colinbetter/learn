/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.controller;

import com.hx.dao.KvDao;
import com.hx.response.CodeAndMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by testuser on 17-4-5.
 */
@RestController
@RequestMapping("/kv")
public class KvController implements ApplicationContextAware {
    private static final char[] BYTES = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890".toCharArray();
    private static final int BYTES_LENGTH = BYTES.length;
    private static final int BASE = 10000;
    private static final Logger LOG = LoggerFactory.getLogger(KvController.class);
    private static ForkJoinPool insertExecutor = (ForkJoinPool) Executors.newWorkStealingPool(20);
    @Autowired
    private KvDao kvDao;
    private ExecutorService startService = Executors.newFixedThreadPool(20);

    @RequestMapping(path = "/add/{num}", method = RequestMethod.GET, produces = {"application/json"})
    public CodeAndMessage add(@PathVariable("num") final int number) {
        startService.submit(() -> {
            long startTime = System.currentTimeMillis();
            InsertTask task = new InsertTask(0, number * BASE);
            ForkJoinTask forkJoinTask = insertExecutor.submit(task);
            forkJoinTask.join();
            long endTime = System.currentTimeMillis();
            LOG.info("insert " + number * BASE + ",start time is " + startTime + ",end time is " + endTime + ",total time is " + (endTime - startTime));
        });
        return new CodeAndMessage(0, "success");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        LOG.info(applicationContext.toString());
    }

    private String getRandomString(int length) {
        if (length <= 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(BYTES[ThreadLocalRandom.current().nextInt(BYTES_LENGTH)]);
        }
        return sb.toString();
    }

    private class InsertTask extends RecursiveAction {
        private int left;
        private int right;

        public InsertTask(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public void compute() {
            if (right - left > 100) {
                int middleNum = (right + left) / 2;
                InsertTask leftTask = new InsertTask(left, middleNum);
                InsertTask rightTask = new InsertTask(middleNum, right);
                this.invokeAll(leftTask, rightTask);
            } else {
                for (int i = left; i < right; i++) {
                    kvDao.insert("test:" + getRandomString(5), getRandomString(5));
                }
            }
        }
    }
}
