package bg.softuni.Registry_GasInstallations.service;

import bg.softuni.Registry_GasInstallations.model.dto.AddGasInstallationDTO;
import bg.softuni.Registry_GasInstallations.model.dto.GasInstallationDTO;

import java.util.List;

public interface GasInstallationService {
    GasInstallationDTO addGasInstallation (AddGasInstallationDTO addGasInstallationDTO);

    List<GasInstallationDTO> getAllGasInstallation();

    void deleteById(Long id);

    GasInstallationDTO getGasInstallationById (Long id);
}
