package com.example.order;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountServiceTest {
    private final DiscountService discountService = new DiscountService();

    @Test
    void testApplyDiscount_Over20Items() {
        Order order = new Order("John Doe", true, 21, 100.0, null);
        discountService.applyDiscount(order);
        assertEquals(85.0, order.getTotalAmount());  // 15% desconto
    }

    @Test
    void testApplyDiscount_LoyaltyMember_Over10Items() {
        Order order = new Order("Jane Doe", true, 12, 100.0, null);
        discountService.applyDiscount(order);
        assertEquals(90.0, order.getTotalAmount());  // 10% desconto
    }

    @Test
    void testApplyDiscount_LoyaltyMember_Under10Items() {
        Order order = new Order("Jane Doe", true, 8, 100.0, null);
        discountService.applyDiscount(order);
        assertEquals(100.0, order.getTotalAmount());  // Sem desconto
    }

    @Test
    void testApplyDiscount_NonLoyaltyMember_Over20Items() {
        Order order = new Order("Jake Doe", false, 20, 200.0, null);
        discountService.applyDiscount(order);
        assertEquals(170.0, order.getTotalAmount());  // 15% desconto
    }

    @Test
    void testApplyDiscount_DuringPromotionPeriod() {
        Order order = new Order("Mary Doe", false, 6, 100.0, null);
        LocalDate today = LocalDate.now();
        if (!today.isBefore(LocalDate.of(today.getYear(), 12, 1)) && !today.isAfter(LocalDate.of(today.getYear(), 12, 31))) {
            discountService.applyDiscount(order);
            assertEquals(95.0, order.getTotalAmount());  // 5% desconto durante promoção
        }
    }

    @Test
    void testApplyDiscount_WithBlackFridayCoupon() {
        Order order = new Order("Lucy Doe", false, 5, 100.0, "BLACKFRIDAY");
        discountService.applyDiscount(order);
        assertEquals(80.0, order.getTotalAmount());  // 20% desconto com cupom BLACKFRIDAY
    }

    @Test
    void testApplyDiscount_WithNewYearCoupon() {
        Order order = new Order("Lucy Doe", false, 5, 100.0, "NEWYEAR");
        discountService.applyDiscount(order);
        assertEquals(90.0, order.getTotalAmount());  // 10% desconto com cupom NEWYEAR
    }

    @Test
    void testApplyDiscount_NoCoupon() {
        Order order = new Order("Lucy Doe", false, 5, 100.0, null);
        discountService.applyDiscount(order);
        assertEquals(100.0, order.getTotalAmount());  // Sem desconto
    }
}