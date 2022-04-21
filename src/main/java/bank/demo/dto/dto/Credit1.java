package bank.demo.dto.dto;

import org.springframework.stereotype.Component;

@Component
public class Credit1 {

    private int idCredit;
    private double howMuchToPay; //надо выплатить ещё
    private double percentRate; // процентная ставка
    private double paid;//выплачено
    private double theTotalAmountYouPay;//сколько в общем надо выплатить
    private double countMonthsToPay; //количество месяцев ещё платить
    private double bankProfit; //прибыль банка
    private double howMuchIsTheLoan;// сколько берёт кредита человек
    private double paymentPerMonth;//сколько в месяц платить


    public double getPaymentPerMonth() {
        return paymentPerMonth;
    }

    public void setPaymentPerMonth(double paymentPerMonth) {
        this.paymentPerMonth = paymentPerMonth;
    }

    public double getHowMuchIsTheLoan() {
        return howMuchIsTheLoan;
    }

    public void setHowMuchIsTheLoan(double howMuchIsTheLoan) {
        this.howMuchIsTheLoan = howMuchIsTheLoan;
    }

    public double getCountMonthsToPay() {
        return countMonthsToPay;
    }

    public double getBankProfit() {
        return bankProfit;
    }

    public void setBankProfit(double bankProfit) {
        this.bankProfit = bankProfit;
    }

    public void setCountMonthsToPay(double countMonthsToPay) {
        this.countMonthsToPay = countMonthsToPay;
    }

    public double getHowMuchToPay() {
        return howMuchToPay;
    }

    public void setHowMuchToPay(double howMuchToPay) {
        this.howMuchToPay = howMuchToPay;
    }

    public double getPercentRate() {
        return percentRate;
    }

    public void setPercentRate(double percentRate) {
        this.percentRate = percentRate;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public double getTheTotalAmountYouPay() {
        return theTotalAmountYouPay;
    }

    public void setTheTotalAmountYouPay(double theTotalAmountYouPay) {
        this.theTotalAmountYouPay = theTotalAmountYouPay;
    }

    public int getIdCredit() {
        return idCredit;
    }

    public void setIdCredit(int idCredit) {
        this.idCredit = idCredit;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "idCredit=" + idCredit +
                ", howMuchToPay=" + howMuchToPay +
                ", percentRate=" + percentRate +
                ", paid=" + paid +
                ", theTotalAmountYouPay=" + theTotalAmountYouPay +
                ", countMonthsToPay=" + countMonthsToPay +
                ", bankProfit=" + bankProfit +
                ", howMuchIsTheLoan=" + howMuchIsTheLoan +
                ", paymentPerMonth=" + paymentPerMonth +
                '}';
    }
}
