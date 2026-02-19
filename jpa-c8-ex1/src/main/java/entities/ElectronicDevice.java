package entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Edevice")   // for giving custom name for the discriminator column  value.


public class ElectronicDevice extends Product {  //we get the id through inheritance

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
                ", id=" + id +
                '}';
    }
}
