package com.venn.message.entity;

import java.util.Date;

public class MobileMessageEntity {
    private Long id;

    private String eventName;

    private String messageText;

    private String recipient;

    private Long countRecipient;

    private String failRecipient;

    private Long countFail;

    private String storeCode;

    private Byte status;

    private String author;

    private String reviewer;

    private String reasonRejection;

    private Date pushMessageTime;

    private Date reviewTime;

    private Date createAt;

    private Date updateAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName == null ? null : eventName.trim();
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText == null ? null : messageText.trim();
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient == null ? null : recipient.trim();
    }

    public Long getCountRecipient() {
        return countRecipient;
    }

    public void setCountRecipient(Long countRecipient) {
        this.countRecipient = countRecipient;
    }

    public String getFailRecipient() {
        return failRecipient;
    }

    public void setFailRecipient(String failRecipient) {
        this.failRecipient = failRecipient == null ? null : failRecipient.trim();
    }

    public Long getCountFail() {
        return countFail;
    }

    public void setCountFail(Long countFail) {
        this.countFail = countFail;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode == null ? null : storeCode.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer == null ? null : reviewer.trim();
    }

    public String getReasonRejection() {
        return reasonRejection;
    }

    public void setReasonRejection(String reasonRejection) {
        this.reasonRejection = reasonRejection == null ? null : reasonRejection.trim();
    }

    public Date getPushMessageTime() {
        return pushMessageTime;
    }

    public void setPushMessageTime(Date pushMessageTime) {
        this.pushMessageTime = pushMessageTime;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}