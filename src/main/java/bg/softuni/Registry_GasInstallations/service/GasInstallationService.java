package bg.softuni.Registry_GasInstallations.service;

import bg.softuni.Registry_GasInstallations.model.dto.AddGasInstallationDTO;
import bg.softuni.Registry_GasInstallations.model.dto.GasInstallationDTO;

public interface GasInstallationService {
    GasInstallationDTO addGasInstallation (AddGasInstallationDTO addGasInstallationDTO);
}
