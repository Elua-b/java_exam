package rw.ac.rca.spring_boot_template.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.rca.spring_boot_template.models.Saving;

import java.util.UUID;
@Repository
public interface ISavingRepository  extends JpaRepository<Saving, UUID>{
    Saving findByCustomerId(UUID from);
}
