package bg.softuni.Registry_GasInstallations.service.impl;

import bg.softuni.Registry_GasInstallations.model.dto.AddGasInstallationDTO;
import bg.softuni.Registry_GasInstallations.model.dto.GasInstallationDTO;
import bg.softuni.Registry_GasInstallations.model.entity.GasInstallationEntity;
import bg.softuni.Registry_GasInstallations.repository.GasInstallationRepository;
import bg.softuni.Registry_GasInstallations.service.GasInstallationService;
import org.springframework.stereotype.Service;

@Service
public class GasInstallationServiceImpl implements GasInstallationService {
    private final GasInstallationRepository gasInstallationRepository;

    public GasInstallationServiceImpl(GasInstallationRepository gasInstallationRepository) {
        this.gasInstallationRepository = gasInstallationRepository;
    }

    @Override
    public GasInstallationDTO addGasInstallation(AddGasInstallationDTO addGasInstallationDTO) {
       GasInstallationEntity gasInstallationEntity = gasInstallationRepository.save(map(addGasInstallationDTO));
       return map(gasInstallationEntity);
    }

    private static GasInstallationEntity map (AddGasInstallationDTO addGasInstallationDTO) {
        return new GasInstallationEntity()
                .setDamtnNumber(addGasInstallationDTO.damtnNumber())
                .setDamtnDate(addGasInstallationDTO.damtnDate())
                .setRegistrationData(addGasInstallationDTO.registrationData())
                .setType(addGasInstallationDTO.type())
                .setManufacturer(addGasInstallationDTO.manufacturer())
                .setModel(addGasInstallationDTO.model())
                .setPressure(addGasInstallationDTO.pressure())
                .setPower(addGasInstallationDTO.power())
                .setOwnerId(addGasInstallationDTO.ownerId());


    }
}
