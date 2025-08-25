package e1i4.petmily.pets.repository;

import e1i4.petmily.pets.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    boolean existsByColorHex(String colorHex);
}
