package bg.softuni.Registry_GasInstallations.service.impl;

import bg.softuni.Registry_GasInstallations.model.dto.AddGasInstallationDTO;
import bg.softuni.Registry_GasInstallations.model.dto.GasInstallationDTO;
import bg.softuni.Registry_GasInstallations.model.entity.GasInstallationEntity;
import bg.softuni.Registry_GasInstallations.repository.GasInstallationRepository;
import bg.softuni.Registry_GasInstallations.service.GasInstallationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GasInstallationServiceImpl implements GasInstallationService {
    private final GasInstallationRepository gasInstallationRepository;
    private final ModelMapper modelMapper;


    public GasInstallationServiceImpl(GasInstallationRepository gasInstallationRepository, ModelMapper modelMapper) {
        this.gasInstallationRepository = gasInstallationRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public GasInstallationDTO addGasInstallation(AddGasInstallationDTO addGasInstallationDTO) {
        GasInstallationEntity gasInstallationEntity = gasInstallationRepository.save(map(addGasInstallationDTO));
        return map(gasInstallationEntity);
    }

    @Override
    public List<GasInstallationDTO> getAllGasInstallation() {
        return gasInstallationRepository
                .findAll()
                .stream()
                .map(GasInstallationServiceImpl::map)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        gasInstallationRepository.deleteById(id);
    }

    @Override
    public GasInstallationDTO getGasInstallationById(Long id) {
        return gasInstallationRepository
                .findById(id)
                .map(GasInstallationServiceImpl::map)
                .orElseThrow();

    }

    @Override
    public void editGasInstallation(Long id, GasInstallationDTO gasInstallationDTO) {
//        GasInstallationEntity gasInstallationEntity = gasInstallationRepository.findById(id).get();
        GasInstallationEntity gasInstallationEntity = modelMapper.map(gasInstallationDTO, GasInstallationEntity.class);
//        gasInstallationEntity = modelMapper.map(gasInstallationDTO, GasInstallationEntity.class);

        gasInstallationRepository.save(gasInstallationEntity);
    }

    private static GasInstallationDTO map(GasInstallationEntity gasInstallationEntity) {
        return new GasInstallationDTO(
                gasInstallationEntity.getId(),
                gasInstallationEntity.getDamtnNumber(),
                gasInstallationEntity.getDamtnDate(),
                gasInstallationEntity.getRegistrationData(),
                gasInstallationEntity.getType(),
                gasInstallationEntity.getManufacturer(),
                gasInstallationEntity.getModel(),
                gasInstallationEntity.getPressure(),
                gasInstallationEntity.getPower(),
                gasInstallationEntity.getOwnerId()
        );
    }

    private static GasInstallationEntity map(AddGasInstallationDTO addGasInstallationDTO) {
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
