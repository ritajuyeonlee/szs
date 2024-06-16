package com.szs.domain.tax.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class ScrapResponseDto {

    @JsonProperty("이름")
    private String name;

    @JsonProperty("종합소득금액")
    private int totalIncome;

    @JsonProperty("소득공제")
    private Deduction deduction;


    // Parameterized constructor
    public ScrapResponseDto(String name, int totalIncome, Deduction deduction) {
        this.name = name;
        this.totalIncome = totalIncome;
        this.deduction = deduction;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(int totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Deduction getDeduction() {
        return deduction;
    }

    public void setDeduction(Deduction deduction) {
        this.deduction = deduction;
    }

    public static class Deduction {

        @JsonProperty("국민연금")
        private List<Pension> pension;

        @JsonProperty("신용카드소득공제")
        private CreditCardDeduction creditCardDeduction;

        @JsonProperty("세액공제")
        private String taxCredit;


        // Parameterized constructor
        public Deduction(List<Pension> pension, CreditCardDeduction creditCardDeduction, String taxCredit) {
            this.pension = pension;
            this.creditCardDeduction = creditCardDeduction;
            this.taxCredit = taxCredit;
        }

        // Getters and setters
        public List<Pension> getPension() {
            return pension;
        }

        public void setPension(List<Pension> pension) {
            this.pension = pension;
        }

        public CreditCardDeduction getCreditCardDeduction() {
            return creditCardDeduction;
        }

        public void setCreditCardDeduction(CreditCardDeduction creditCardDeduction) {
            this.creditCardDeduction = creditCardDeduction;
        }

        public String getTaxCredit() {
            return taxCredit;
        }

        public void setTaxCredit(String taxCredit) {
            this.taxCredit = taxCredit;
        }
    }

    public static class Pension {

        @JsonProperty("월")
        private String month;

        @JsonProperty("공제액")
        private String amount;

        // Default constructor
        public Pension() {
        }

        // Parameterized constructor
        public Pension(String month, String amount) {
            this.month = month;
            this.amount = amount;
        }

        // Getters and setters
        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    public static class CreditCardDeduction {

        @JsonProperty("year")
        private int year;

        @JsonProperty("month")
        private List<Map<String, String>> month;


        // Parameterized constructor
        public CreditCardDeduction(int year, List<Map<String, String>> month) {
            this.year = year;
            this.month = month;
        }

    }


}