package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "current_report")
public class CurrentReport {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "car")
    private Car car;

    @Column(name = "price")
    private Long price;

    public CurrentReport() {

    }

    public CurrentReport(Car car, Long price) {
        this.car = car;
        this.price = price;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}