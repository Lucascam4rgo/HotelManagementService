package HotelManagementService.Hotel.model;

import java.math.BigDecimal;

public class Quarto {

    private Integer number;
    private Type type;
    private BigDecimal dailyPrice;
    private Status status;

    public Quarto(Integer number, Type type, BigDecimal dailyPrice, Status status) {
        this.number = number;
        this.type = type;
        this.dailyPrice = dailyPrice;
        this.status = status;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public BigDecimal getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(BigDecimal dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "QUARTO: " + number + ", " + type + ", " + dailyPrice
                + ", " + status;
    }
}
