package com.design.helpPlatform.entity;

import java.sql.Timestamp;
import java.util.List;

public class OrderEntity {
    private Integer id;
    private String orderNo;
    private Integer orderOwner;
    private String startPos;
    private String endPos;
    private String startGps;
    private String endGps;
    private String sendName;
    private String sendPhone;
    private String reciveName;
    private String recivePhone;
    private String orderType;
    private Integer orderStatus;
    private Timestamp orderCreateTime;
    private Integer orderRiderId;
    private Timestamp orderStartTime;
    private Timestamp orderRiderSendTime;
    private Timestamp orderUesrCheckTime;
    private Integer orderContentId;
    private String startDetailPos;
    private String endDetailPos;
    private String sendType;
    private String sendRemark;
    private String orderCity;
    private String orderPri;
    private String orderZip;
    private List<OrderDetailEntity> orderDetailList;

    public List<OrderDetailEntity> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetailEntity> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderOwner() {
        return orderOwner;
    }

    public void setOrderOwner(Integer orderOwner) {
        this.orderOwner = orderOwner;
    }

    public String getStartPos() {
        return startPos;
    }

    public void setStartPos(String startPos) {
        this.startPos = startPos;
    }

    public String getEndPos() {
        return endPos;
    }

    public void setEndPos(String endPos) {
        this.endPos = endPos;
    }

    public String getStartGps() {
        return startGps;
    }

    public void setStartGps(String startGps) {
        this.startGps = startGps;
    }

    public String getEndGps() {
        return endGps;
    }

    public void setEndGps(String endGps) {
        this.endGps = endGps;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getSendPhone() {
        return sendPhone;
    }

    public void setSendPhone(String sendPhone) {
        this.sendPhone = sendPhone;
    }

    public String getReciveName() {
        return reciveName;
    }

    public void setReciveName(String reciveName) {
        this.reciveName = reciveName;
    }

    public String getRecivePhone() {
        return recivePhone;
    }

    public void setRecivePhone(String recivePhone) {
        this.recivePhone = recivePhone;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Timestamp getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Timestamp orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public Integer getOrderRiderId() {
        return orderRiderId;
    }

    public void setOrderRiderId(Integer orderRiderId) {
        this.orderRiderId = orderRiderId;
    }

    public Timestamp getOrderStartTime() {
        return orderStartTime;
    }

    public void setOrderStartTime(Timestamp orderStartTime) {
        this.orderStartTime = orderStartTime;
    }

    public Timestamp getOrderRiderSendTime() {
        return orderRiderSendTime;
    }

    public void setOrderRiderSendTime(Timestamp orderRiderSendTime) {
        this.orderRiderSendTime = orderRiderSendTime;
    }

    public Timestamp getOrderUesrCheckTime() {
        return orderUesrCheckTime;
    }

    public void setOrderUesrCheckTime(Timestamp orderUesrCheckTime) {
        this.orderUesrCheckTime = orderUesrCheckTime;
    }

    public Integer getOrderContentId() {
        return orderContentId;
    }

    public void setOrderContentId(Integer orderContentId) {
        this.orderContentId = orderContentId;
    }

    public String getStartDetailPos() {
        return startDetailPos;
    }

    public void setStartDetailPos(String startDetailPos) {
        this.startDetailPos = startDetailPos;
    }

    public String getEndDetailPos() {
        return endDetailPos;
    }

    public void setEndDetailPos(String endDetailPos) {
        this.endDetailPos = endDetailPos;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getSendRemark() {
        return sendRemark;
    }

    public void setSendRemark(String sendRemark) {
        this.sendRemark = sendRemark;
    }

    public String getOrderCity() {
        return orderCity;
    }

    public void setOrderCity(String orderCity) {
        this.orderCity = orderCity;
    }

    public String getOrderPri() {
        return orderPri;
    }

    public void setOrderPri(String orderPri) {
        this.orderPri = orderPri;
    }

    public String getOrderZip() {
        return orderZip;
    }

    public void setOrderZip(String orderZip) {
        this.orderZip = orderZip;
    }
}
