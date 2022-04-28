package bank.demo.dto.services;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal(100);
        BigDecimal bigDecimal1 = new BigDecimal("10");
        BigDecimal bigDecimal2 = bigDecimal.divide(bigDecimal1);
        System.out.println(bigDecimal2);
    }
}
