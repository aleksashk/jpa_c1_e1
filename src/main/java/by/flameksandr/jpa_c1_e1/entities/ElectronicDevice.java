package by.flameksandr.jpa_c1_e1.entities;

import jakarta.persistence.Entity;

@Entity
public class ElectronicDevice extends Product {
    private int voltage;

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    @Override
    public String toString() {
        return "ElectronicDevice{" +
                "voltage=" + voltage +
                '}';
    }
}
