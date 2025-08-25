package e1i4.petmily.pets.service;

import e1i4.petmily.pets.domain.Pet;
import e1i4.petmily.pets.dto.PetRegisterRequest;
import e1i4.petmily.pets.repository.PetRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;
//    private final UserRepository userRepository;

    @Transactional
    public Pet registerPet(Long userId, PetRegisterRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        //색상 중복 체크
        if(petRepository.existsByColorHex(request.getColorHex())) {
            throw new IllegalArgumentException("이미 사용 중인 색상입니다!");
        }

        Pet pet = Pet.builder()
                .name(request.getName())
                .gender(request.getGender())
                .age(request.getAge())
                .birthDate(request.getBirthDate())
                .meetDate(request.getMeetDate())
                .healthIssue(request.getHealthIssue())
                .petImageUrl(request.getPetImageUrl())
                .colorHex(request.getColorHex())
                .user(user)
                .build();

        return petRepository.save(pet);
    }
}
