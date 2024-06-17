package com.szs.domain.member.entity.value;

import jakarta.persistence.Embeddable;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Embeddable
public class Tax {
    @Comment(value = "과세표준")
    private BigDecimal taxBase;

    @Comment(value = "세액공제")
    private BigDecimal taxCredit;

    public Tax() {
    }

    public Tax(BigDecimal taxBase, BigDecimal taxCredit) {
        this.taxBase = taxBase;
        this.taxCredit = taxCredit;
    }

    public static Tax of(BigDecimal totalIncome, BigDecimal deduction, BigDecimal taxCredit) {
        return new Tax(totalIncome.subtract(deduction), taxCredit);
    }


    public BigDecimal getRefund() {
        if (taxBase.compareTo(BigDecimal.valueOf(140000)) <= 0) {
            return taxBase.multiply(BigDecimal.valueOf(0.06)).subtract(taxCredit);
        } else if (taxBase.compareTo(BigDecimal.valueOf(14000000)) > 0 && taxBase.compareTo(BigDecimal.valueOf(50000000)) <= 0) {
            return tax(14000000.0, 0.15, 840000.0);

        } else if (taxBase.compareTo(BigDecimal.valueOf(50000000)) > 0 && taxBase.compareTo(BigDecimal.valueOf(88000000)) <= 0) {
            return tax(50000000.0, 0.24, 6240000.0);

        } else if (taxBase.compareTo(BigDecimal.valueOf(88000000)) > 0 && taxBase.compareTo(BigDecimal.valueOf(150000000)) <= 0) {
            return tax(88000000.0, 0.35, 15360000.0);

        } else if (taxBase.compareTo(BigDecimal.valueOf(150000000)) > 0 && taxBase.compareTo(BigDecimal.valueOf(300000000)) <= 0) {
            return tax(150000000.0, 0.38, 37060000.0);

        } else if (taxBase.compareTo(BigDecimal.valueOf(300000000)) > 0 && taxBase.compareTo(BigDecimal.valueOf(500000000)) <= 0) {
            return tax(300000000.0, 0.4, 94060000.0);

        } else if (taxBase.compareTo(BigDecimal.valueOf(500000000)) > 0 && taxBase.compareTo(BigDecimal.valueOf(1000000000)) <= 0) {
            return tax(500000000.0, 0.42, 174060000.0);

        } else {
            return tax(1000000000.0, 0.45, 384060000.0);
        }
    }

    private BigDecimal tax(Double min, Double percent, Double additional) {
        return taxBase
                .subtract(BigDecimal.valueOf(min))
                .multiply(BigDecimal.valueOf(percent))
                .add(BigDecimal.valueOf(additional))
                .subtract(taxCredit)
                .setScale(0, RoundingMode.HALF_UP)
                ;
    }

}
