package e1i4.petmily.pets.dto;

import e1i4.petmily.pets.domain.GenderType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetRegisterRequest {

    @NotBlank(message = "이름은 필수입니다!")
    @Size(max = 50)
    private String name;

    @NotNull(message = "성별은 필수입니다!")
    private GenderType gender;

    @NotNull(message = "나이는 필수입니다!")
    @Min(value = 0, message = "나이는 0 이상이어야 합니다!")
    private Integer age;

    private LocalDate birthDate;
    private LocalDate meetDate;
    private String healthIssue;
    private String petImageUrl;

    @NotBlank(message = "색상은 필수입니다!")
    private String colorHex;
}
