package bank.demo.dto.dto;

public class Insurance {
    private double sumInsured;
    private double insurancePaid;
    private int idInsurance;

    public Insurance(double sumInsured) {
        this.sumInsured = sumInsured;
    }

    public double getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(double sumInsured) {
        this.sumInsured = sumInsured;
    }

    public double getInsurancePaid() {
        return insurancePaid;
    }

    public void setInsurancePaid(double insurancePaid) {
        this.insurancePaid = insurancePaid;
    }

    public int getIdInsurance() {
        return idInsurance;
    }

    public void setIdInsurance(int idInsurance) {
        this.idInsurance = idInsurance;
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "sumInsured=" + sumInsured +
                ", insurancePaid=" + insurancePaid +
                ", idInsurance=" + idInsurance +
                '}';
    }
}
