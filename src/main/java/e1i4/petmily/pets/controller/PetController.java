package e1i4.petmily.pets.controller;

import e1i4.petmily.pets.domain.Pet;
import e1i4.petmily.pets.dto.PetRegisterRequest;
import e1i4.petmily.pets.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    public ResponseEntity<Pet> registerPet(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                           @RequestBody @Valid PetRegisterRequest request,
                                           BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        Pet pet = petService.registerPet(userPrincipal.getId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(pet);
    }
}
