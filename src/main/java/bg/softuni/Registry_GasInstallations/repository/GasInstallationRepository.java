package bg.softuni.Registry_GasInstallations.repository;

import bg.softuni.Registry_GasInstallations.model.entity.GasInstallationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GasInstallationRepository extends JpaRepository <GasInstallationEntity, Long> {
}
