package bg.softuni.Registry_GasInstallations.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class GasInstallationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String damtnNumber;
    private LocalDate damtnDate;
    private LocalDate registrationData;
    private String type;
    private String manufacturer;
    private String model;
    private String pressure;
    private String power;
    private Long ownerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDamtnNumber() {
        return damtnNumber;
    }

    public GasInstallationEntity setDamtnNumber(String damtnNumber) {
        this.damtnNumber = damtnNumber;
        return this;
    }

    public LocalDate getDamtnDate() {
        return damtnDate;
    }

    public GasInstallationEntity setDamtnDate(LocalDate damtnDate) {
        this.damtnDate = damtnDate;
        return this;
    }

    public LocalDate getRegistrationData() {
        return registrationData;
    }

    public GasInstallationEntity setRegistrationData(LocalDate registrationData) {
        this.registrationData = registrationData;
        return this;
    }

    public String getType() {
        return type;
    }

    public GasInstallationEntity setType(String type) {
        this.type = type;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public GasInstallationEntity setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public String getModel() {
        return model;
    }

    public GasInstallationEntity setModel(String model) {
        this.model = model;
        return this;
    }

    public String getPressure() {
        return pressure;
    }

    public GasInstallationEntity setPressure(String pressure) {
        this.pressure = pressure;
        return this;
    }

    public String getPower() {
        return power;
    }

    public GasInstallationEntity setPower(String power) {
        this.power = power;
        return this;

    }

    public Long getOwnerId() {
        return ownerId;
    }

    public GasInstallationEntity setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }
}
