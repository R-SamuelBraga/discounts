package com.example.order;

import java.time.LocalDate;

public class Order {
    private String customerName;
    private boolean isLoyaltyMember;
    private int itemCount;
    private double totalAmount;
    private String couponCode;
    private LocalDate dataHoje;

    public Order(String customerName, boolean isLoyaltyMember, int itemCount, double totalAmount, String couponCode, LocalDate dataHoje) {
        this.customerName = customerName;
        this.isLoyaltyMember = isLoyaltyMember;
        this.itemCount = itemCount;
        this.totalAmount = totalAmount;
        this.couponCode = couponCode;
        this.dataHoje = dataHoje;
    }

    public boolean isLoyaltyMember() {
        return isLoyaltyMember;
    }

    public int getItemCount() {
        return itemCount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void applyDiscount(double discount) {
        this.totalAmount -= discount;
    }

    public boolean hasCoupon() {
        return couponCode != null && !couponCode.isEmpty();
    }

    public String getCouponCode() {
        return couponCode;
    }

    public LocalDate getDataHoje() {
        return dataHoje;
    }


}
