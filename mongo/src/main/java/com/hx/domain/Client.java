/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.domain;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

/**
 * Created by testuser on 17-3-21.
 * {
 * acSN: "210235A0VSB148000079",
 * updateTime: new Date(1489918297763),
 * update: true,
 * portalAuthTypeEn: "Portal",
 * portalAuthType: "portal 认证",
 * portalOnlineTime: "2017-03-19 17:00:59",
 * portalUserName: "201400450227@student",
 * clientVendorEn: "OPPO",
 * clientVendor: "OPPO",
 * upLineTime: new Date(1489918297206),
 * timestamp: 1937948224,
 * clientBssID: "3891-d5b4-08a0",
 * clientRadioMode: 64,
 * clientRxPackets: 0,
 * clientTxPackets: 0,
 * clientRxBytes: 0,
 * clientTxBytes: 0,
 * NegoMaxRate: 86.7,
 * ApName: "tsg01sw",
 * clientChannel: 149,
 * clientRxRate: 0,
 * clientTxRate: 0,
 * clientSTName: "3",
 * clientSSID: "student",
 * signalStrength: 0,
 * clientName: null,
 * clientIP: "10.31.41.121",
 * clientMode: "802.11ac",
 * clientMAC: "6c5c-14be-d8a9",
 * radioID: 1,
 * apSN: "219801A0REM15A000798"
 * }
 */
@Document(collection = "clientonline")
@CompoundIndexes({@CompoundIndex(name = "acSN_clientMAC", def = "{acSN : 1, clientMAC : 1}")})
public class Client {
    public static final int AC_SN_LENGTH = 4;
    @Field("acSN")
    private String acSN;
    @Field("updateTime")
    private LocalDateTime updateTime;
    @Field("update")
    private boolean update;
    @Field("portalAuthTypeEn")
    private String portalAuthTypeEn;
    @Field("portalAuthType")
    private String portalAuthType;
    @Field("portalOnlineTime")
    private LocalDateTime portalOnlineTime;
    @Field("portalUserName")
    private String portalUserName;
    @Field("clientVendorEn")
    private String clientVendorEn;
    @Field("clientVendor")
    private String clientVendor;
    @Field("upLineTime")
    @Indexed
    private LocalDateTime upLineTime;
    @Field("timestamp")
    private LocalDateTime timestamp;
    @Field("clientBssID")
    private String clientBssID;
    @Field("clientRadioMode")
    private int clientRadioMode;
    @Field("clientRxPackets")
    private int clientRxPackets;
    @Field("clientTxPackets")
    private int clientTxPackets;
    @Field("clientRxBytes")
    private int clientRxBytes;
    @Field("clientTxBytes")
    private int clientTxBytes;
    @Field("negoMaxRate")
    private double negoMaxRate;
    @Field("apName")
    private String apName;
    @Field("clientChannel")
    private int clientChannel;
    @Field("clientRxRate")
    private double clientRxRate;
    @Field("clientTxRate")
    private double clientTxRate;
    @Field("clientSTName")
    private String clientSTName;
    @Field("clientSSID")
    private String clientSSID;
    @Field("signalStrength")
    private int signalStrength;
    @Field("clientName")

    private String clientName;
    @Field("clientIP")
    private String clientIP;
    @Field("clientMode")
    private String clientMode;
    @Field("clientMAC")
    @Indexed
    private String clientMAC;
    @Field("radioID")
    private int radioID;
    @Field("apSN")
    private String apSN;

    public String getAcSN() {
        return acSN;
    }

    public void setAcSN(String acSN) {
        this.acSN = acSN;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public String getPortalAuthTypeEn() {
        return portalAuthTypeEn;
    }

    public void setPortalAuthTypeEn(String portalAuthTypeEn) {
        this.portalAuthTypeEn = portalAuthTypeEn;
    }

    public String getPortalAuthType() {
        return portalAuthType;
    }

    public void setPortalAuthType(String portalAuthType) {
        this.portalAuthType = portalAuthType;
    }

    public LocalDateTime getPortalOnlineTime() {
        return portalOnlineTime;
    }

    public void setPortalOnlineTime(LocalDateTime portalOnlineTime) {
        this.portalOnlineTime = portalOnlineTime;
    }

    public String getPortalUserName() {
        return portalUserName;
    }

    public void setPortalUserName(String portalUserName) {
        this.portalUserName = portalUserName;
    }

    public String getClientVendorEn() {
        return clientVendorEn;
    }

    public void setClientVendorEn(String clientVendorEn) {
        this.clientVendorEn = clientVendorEn;
    }

    public String getClientVendor() {
        return clientVendor;
    }

    public void setClientVendor(String clientVendor) {
        this.clientVendor = clientVendor;
    }

    public LocalDateTime getUpLineTime() {
        return upLineTime;
    }

    public void setUpLineTime(LocalDateTime upLineTime) {
        this.upLineTime = upLineTime;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getClientBssID() {
        return clientBssID;
    }

    public void setClientBssID(String clientBssID) {
        this.clientBssID = clientBssID;
    }

    public int getClientRadioMode() {
        return clientRadioMode;
    }

    public void setClientRadioMode(int clientRadioMode) {
        this.clientRadioMode = clientRadioMode;
    }

    public int getClientRxPackets() {
        return clientRxPackets;
    }

    public void setClientRxPackets(int clientRxPackets) {
        this.clientRxPackets = clientRxPackets;
    }

    public int getClientTxPackets() {
        return clientTxPackets;
    }

    public void setClientTxPackets(int clientTxPackets) {
        this.clientTxPackets = clientTxPackets;
    }

    public int getClientRxBytes() {
        return clientRxBytes;
    }

    public void setClientRxBytes(int clientRxBytes) {
        this.clientRxBytes = clientRxBytes;
    }

    public int getClientTxBytes() {
        return clientTxBytes;
    }

    public void setClientTxBytes(int clientTxBytes) {
        this.clientTxBytes = clientTxBytes;
    }

    public double getNegoMaxRate() {
        return negoMaxRate;
    }

    public void setNegoMaxRate(double negoMaxRate) {
        this.negoMaxRate = negoMaxRate;
    }

    public String getApName() {
        return apName;
    }

    public void setApName(String apName) {
        this.apName = apName;
    }

    public int getClientChannel() {
        return clientChannel;
    }

    public void setClientChannel(int clientChannel) {
        this.clientChannel = clientChannel;
    }

    public double getClientRxRate() {
        return clientRxRate;
    }

    public void setClientRxRate(double clientRxRate) {
        this.clientRxRate = clientRxRate;
    }

    public double getClientTxRate() {
        return clientTxRate;
    }

    public void setClientTxRate(double clientTxRate) {
        this.clientTxRate = clientTxRate;
    }

    public String getClientSTName() {
        return clientSTName;
    }

    public void setClientSTName(String clientSTName) {
        this.clientSTName = clientSTName;
    }

    public String getClientSSID() {
        return clientSSID;
    }

    public void setClientSSID(String clientSSID) {
        this.clientSSID = clientSSID;
    }

    public int getSignalStrength() {
        return signalStrength;
    }

    public void setSignalStrength(int signalStrength) {
        this.signalStrength = signalStrength;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public String getClientMode() {
        return clientMode;
    }

    public void setClientMode(String clientMode) {
        this.clientMode = clientMode;
    }

    public String getClientMAC() {
        return clientMAC;
    }

    public void setClientMAC(String clientMAC) {
        this.clientMAC = clientMAC;
    }

    public int getRadioID() {
        return radioID;
    }

    public void setRadioID(int radioID) {
        this.radioID = radioID;
    }

    public String getApSN() {
        return apSN;
    }

    public void setApSN(String apSN) {
        this.apSN = apSN;
    }
}
