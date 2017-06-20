/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.controller;

import com.hx.dao.ClientDao;
import com.hx.domain.Client;
import com.hx.response.CodeAndMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by testuser on 17-3-23.
 */
@RestController("clientController")
@RequestMapping("/client")
public class ClientController {
    private static final char[] BYTES = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890".toCharArray();
    private static final int BYTES_LENGTH = BYTES.length;
    private static final int BASE = 10000;
    private static Logger LOG = LoggerFactory.getLogger(ClientController.class);
    private static ForkJoinPool insertExecutor = (ForkJoinPool) Executors.newWorkStealingPool(20);
    private static ForkJoinPool searchExecutor = (ForkJoinPool) Executors.newWorkStealingPool(20);
    private static ForkJoinPool updateExecutor = (ForkJoinPool) Executors.newWorkStealingPool(20);
    private static ExecutorService poolExecutor = Executors.newFixedThreadPool(10);


    @Autowired
    private ClientDao clientDao;

    @RequestMapping(value = "/add/{number}", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public CodeAndMessage addClient(@PathVariable("number") int number) {
        if (number > 0) {
            poolExecutor.execute(() -> {
                long startTime = System.currentTimeMillis();
                InsertTask task = new InsertTask(0, number * BASE);
                ForkJoinTask forkJoinTask = insertExecutor.submit(task);
                forkJoinTask.join();
                long endTime = System.currentTimeMillis();
                LOG.info("insert " + number * BASE + ",start time is " + startTime + ",end time is " + endTime + ",total time is " + (endTime - startTime));
            });
        }
        return new CodeAndMessage(0, "success");
    }

    @RequestMapping(value = "/addByTime/{number}/{day}", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public CodeAndMessage addClientByTime(@PathVariable("number") final int number, @PathVariable("day") final String day) {
        if (number > 0) {
            poolExecutor.execute(() -> {
                long startTime = System.currentTimeMillis();
                InsertByTimeTask task = new InsertByTimeTask(0, number * BASE, day);
                ForkJoinTask forkJoinTask = insertExecutor.submit(task);
                forkJoinTask.join();
                long endTime = System.currentTimeMillis();
                LOG.info("insert by day " + number * BASE + ",start time is " + startTime + ",end time is " + endTime + ",total time is " + (endTime - startTime));
            });
        }
        return new CodeAndMessage(0, "success");
    }

    @RequestMapping(value = "/addByBatch/{count}/{batchCount}", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public CodeAndMessage addClients(@PathVariable("count") int count, @PathVariable("batchCount") int batchCount) {
        if (count > 0) {
            poolExecutor.execute(() -> {
                long startTime = System.currentTimeMillis();
                BatchInsertTask task = new BatchInsertTask(0, count * BASE, batchCount);
                ForkJoinTask forkJoinTask = insertExecutor.submit(task);
                forkJoinTask.join();
                long endTime = System.currentTimeMillis();
                LOG.info("batch insert " + count * BASE + "and batch is " + batchCount + ",start time is " + startTime + ",end time is " + endTime + ",total time is " + (endTime - startTime));
            });
        }
        return new CodeAndMessage(0, "success");
    }

    @RequestMapping(value = "/get/{number}", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public CodeAndMessage getClient(@PathVariable("number") final int number) {
        if (number > 0) {
            poolExecutor.execute(() -> {
                long startTime = System.currentTimeMillis();
                QueryTask task = new QueryTask(0, number * BASE);
                ForkJoinTask forkJoinTask = searchExecutor.submit(task);
                forkJoinTask.join();
                long endTime = System.currentTimeMillis();
                LOG.info("query " + number * BASE + ",start time is " + startTime + ",end time is " + endTime + ",total time is " + (endTime - startTime));
            });
        }
        return new CodeAndMessage(0, "success");
    }

    @RequestMapping(value = "/getAll/{number}", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public CodeAndMessage getAllClient(@PathVariable("number") final int number) {
        if (number > 0) {
            poolExecutor.execute(() -> {
                long startTime = System.currentTimeMillis();
                QueryAllTask task = new QueryAllTask(0, number * BASE);
                ForkJoinTask forkJoinTask = searchExecutor.submit(task);
                forkJoinTask.join();
                long endTime = System.currentTimeMillis();
                LOG.info("query all" + number * BASE + ",start time is " + startTime + ",end time is " + endTime + ",total time is " + (endTime - startTime));
            });
        }
        return new CodeAndMessage(0, "success");
    }

    @RequestMapping(value = "/update/{number}", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public CodeAndMessage updateClient(@PathVariable("number") final int number) {
        if (number > 0) {
            updateExecutor.execute(() -> {
                long startTime = System.currentTimeMillis();
                UpdateTask task = new UpdateTask(0, number * BASE);
                ForkJoinTask forkJoinTask = searchExecutor.submit(task);
                forkJoinTask.join();
                long endTime = System.currentTimeMillis();
                LOG.info("update " + number * BASE + ",start time is " + startTime + ",end time is " + endTime + ",total time is " + (endTime - startTime));
            });
        }
        return new CodeAndMessage(0, "success");
    }

    private Client getClient() {
        Client client = new Client();
        client.setAcSN(getRandomString(Client.AC_SN_LENGTH));
        client.setApName(getRandomString(12));
        client.setApSN(getRandomString(10));
        client.setClientBssID(getRandomString(10));
        client.setClientChannel(ThreadLocalRandom.current().nextInt(100));
        client.setClientIP(getRandomIP());
        client.setClientMAC(getRandomString(22));
        client.setClientMode(getRandomString(5));
        client.setClientName(getRandomString(10));
        client.setClientRadioMode(ThreadLocalRandom.current().nextInt(10));
        client.setClientRxBytes(ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE));
        client.setClientRxPackets(ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE >> 2));
        client.setClientRxRate(ThreadLocalRandom.current().nextDouble(100));
        client.setClientSSID(getRandomString(16));
        client.setClientSTName(getRandomString(10));
        client.setClientTxBytes(ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE));
        client.setClientTxPackets(ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE >> 2));
        client.setClientTxRate(ThreadLocalRandom.current().nextDouble(100));
        client.setClientVendor(getRandomString(10));
        client.setClientVendorEn(getRandomString(8));
        client.setNegoMaxRate(ThreadLocalRandom.current().nextDouble(100));
        client.setPortalOnlineTime(LocalDateTime.now());
        client.setPortalAuthType(getRandomString(5));
        client.setPortalAuthTypeEn(getRandomString(8));
        client.setPortalUserName(getRandomString(6));
        client.setUpdate(ThreadLocalRandom.current().nextBoolean());
        client.setUpdateTime(LocalDateTime.now());
        client.setUpLineTime(LocalDateTime.now());
        client.setRadioID(ThreadLocalRandom.current().nextInt(100));
        client.setNegoMaxRate(ThreadLocalRandom.current().nextDouble(100));
        client.setTimestamp(LocalDateTime.now());
        return client;
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

    private String getRandomIP() {
        StringBuilder sb = new StringBuilder();
        sb.append(ThreadLocalRandom.current().nextInt(256));
        for (int i = 0; i < 3; i++) {
            sb.append(".").append(ThreadLocalRandom.current().nextInt(256));
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
                    clientDao.insert(getClient());
                }
            }
        }
    }

    private class BatchInsertTask extends RecursiveAction {
        private int left;
        private int right;
        private int batchCount;

        public BatchInsertTask(int left, int right, int batchCount) {
            this.left = left;
            this.right = right;
            this.batchCount = batchCount;
        }

        @Override
        public void compute() {
            if (right - left > batchCount) {
                int middleNum = (right + left) / 2;
                BatchInsertTask leftTask = new BatchInsertTask(left, middleNum, batchCount);
                BatchInsertTask rightTask = new BatchInsertTask(middleNum, right, batchCount);
                this.invokeAll(leftTask, rightTask);
            } else {
                List<Client> clientList = new ArrayList<>(batchCount);
                for (int i = left; i < right; i++) {
                    clientList.add(getClient());
                }
                clientDao.insert(clientList);
            }
        }
    }

    private class InsertByTimeTask extends RecursiveAction {
        private int left;
        private int right;
        private String day;

        public InsertByTimeTask(int left, int right, final String day) {
            this.left = left;
            this.right = right;
            this.day = day;
        }

        @Override
        public void compute() {
            if (right - left > 100) {
                int middleNum = (right + left) / 2;
                InsertByTimeTask leftTask = new InsertByTimeTask(left, middleNum, day);
                InsertByTimeTask rightTask = new InsertByTimeTask(middleNum, right, day);
                this.invokeAll(leftTask, rightTask);
            } else {
                for (int i = left; i < right; i++) {
                    clientDao.insertByTime(getClient(), day);
                }
            }
        }
    }

    private class QueryTask extends RecursiveAction {
        private int left;
        private int right;

        public QueryTask(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public void compute() {
            if (right - left > 100) {
                int middleNum = (right + left) / 2;
                QueryTask leftTask = new QueryTask(left, middleNum);
                QueryTask rightTask = new QueryTask(middleNum, right);
                this.invokeAll(leftTask, rightTask);
            } else {
                for (int i = left; i < right; i++) {
                    clientDao.getSomeFields(getRandomString(Client.AC_SN_LENGTH));
                }
            }
        }
    }

    private class QueryAllTask extends RecursiveAction {
        private int left;
        private int right;

        public QueryAllTask(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public void compute() {
            if (right - left > 100) {
                int middleNum = (right + left) / 2;
                QueryAllTask leftTask = new QueryAllTask(left, middleNum);
                QueryAllTask rightTask = new QueryAllTask(middleNum, right);
                this.invokeAll(leftTask, rightTask);
            } else {
                for (int i = left; i < right; i++) {
                    clientDao.getAllFields(getRandomString(Client.AC_SN_LENGTH));
                }
            }
        }
    }

    private class UpdateTask extends RecursiveAction {
        private int left;
        private int right;

        public UpdateTask(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public void compute() {
            if (right - left > 100) {
                int middleNum = (right + left) / 2;
                UpdateTask leftTask = new UpdateTask(left, middleNum);
                UpdateTask rightTask = new UpdateTask(middleNum, right);
                this.invokeAll(leftTask, rightTask);
            } else {
                for (int i = left; i < right; i++) {
                    clientDao.update(getRandomString(Client.AC_SN_LENGTH));
                }
            }
        }
    }
}
