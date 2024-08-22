package com.example.order;

import java.time.LocalDate;

public class DiscountService {

    public void applyDiscount(Order order) {
        if (order.getItemCount() >= 20) {  // 1ª decisão
            order.applyDiscount(order.getTotalAmount() * 0.15);
        } else {
            if (order.isLoyaltyMember()) {  // 2ª decisão
                if (order.getItemCount() > 10) {  // 3ª decisão
                    order.applyDiscount(order.getTotalAmount() * 0.10);
                }
            }
        }

        // Verificação de data promocional
        //LocalDate ;
        LocalDate today = order.getDataHoje();
        LocalDate promoStart = LocalDate.of(today.getYear(), 12, 1);
        LocalDate promoEnd = LocalDate.of(today.getYear(), 12, 31);
        VerificarDataPromocional(order, today, promoStart, promoEnd);

        // Verificação de código de cupom
        if (order.hasCoupon()) {  // 7ª decisão
            String coupon = order.getCouponCode();
            if ("BLACKFRIDAY".equals(coupon)) {  // 8ª decisão
                order.applyDiscount(order.getTotalAmount() * 0.20);
            } else if ("NEWYEAR".equals(coupon)) {  // 9ª decisão
                order.applyDiscount(order.getTotalAmount() * 0.10);
            }
        }
    }

    private static void VerificarDataPromocional(Order order, LocalDate today, LocalDate promoStart, LocalDate promoEnd) {
        if (!today.isBefore(promoStart) && !today.isAfter(promoEnd)) {  // 4ª decisão
            if (order.getItemCount() > 5 && !order.isLoyaltyMember()) {  // 5ª e 6ª decisões (&&)
                order.applyDiscount(order.getTotalAmount() * 0.05);
            }
        }
    }
}
