package bg.softuni.Registry_GasInstallations.web;

import bg.softuni.Registry_GasInstallations.model.dto.AddGasInstallationDTO;
import bg.softuni.Registry_GasInstallations.model.dto.GasInstallationDTO;
import bg.softuni.Registry_GasInstallations.service.GasInstallationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gas")
public class GasInstallationController {

    private final GasInstallationService gasInstallationService;

    public GasInstallationController(GasInstallationService gasInstallationService) {
        this.gasInstallationService = gasInstallationService;
    }

    @PostMapping
    public ResponseEntity<GasInstallationDTO> createdGasInstallation (AddGasInstallationDTO addGasInstallationDTO) {
        gasInstallationService.addGasInstallation(addGasInstallationDTO);
        return ResponseEntity.ok().build();
    }
}
