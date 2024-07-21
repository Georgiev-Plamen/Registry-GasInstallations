package bg.softuni.Registry_GasInstallations.web;

import bg.softuni.Registry_GasInstallations.model.dto.AddGasInstallationDTO;
import bg.softuni.Registry_GasInstallations.model.dto.GasInstallationDTO;
import bg.softuni.Registry_GasInstallations.service.GasInstallationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/gas")
public class GasInstallationController {

    private final GasInstallationService gasInstallationService;

    public GasInstallationController(GasInstallationService gasInstallationService) {
        this.gasInstallationService = gasInstallationService;
    }
//      Return only long
//    @PostMapping
//    public ResponseEntity<GasInstallationDTO> createdGasInstallation (AddGasInstallationDTO addGasInstallationDTO) {
//        gasInstallationService.addGasInstallation(addGasInstallationDTO);
//        return ResponseEntity.ok().build();
//    }

    @PostMapping
    public ResponseEntity<GasInstallationDTO> createGasInstallation (@RequestBody AddGasInstallationDTO addGasInstallationDTO) {
        GasInstallationDTO gasInstallationDTO = gasInstallationService.addGasInstallation(addGasInstallationDTO);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(gasInstallationDTO.id())
                        .toUri()
        ).body(gasInstallationDTO);
    }

    @GetMapping
    public ResponseEntity<List<GasInstallationDTO>>  getAllGasInstallation() {
        return ResponseEntity.ok(gasInstallationService.getAllGasInstallation());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GasInstallationDTO> getGasInstallationById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(gasInstallationService.getGasInstallationById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GasInstallationDTO> deleteById(@PathVariable("id") Long id) {
        gasInstallationService.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
