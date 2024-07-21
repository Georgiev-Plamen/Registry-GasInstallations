package bg.softuni.Registry_GasInstallations.model.dto;

import java.time.LocalDate;

public record AddGasInstallationDTO (

        String damtnNumber,
//        LocalDate damtnDate,
//        LocalDate registrationData,
//        String type,
        String manufacturer
//        String model,
//        String pressure,
//        String power,
//        Long ownerId
) {
}
