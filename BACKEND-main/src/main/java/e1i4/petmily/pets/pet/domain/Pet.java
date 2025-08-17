package e1i4.petmily.pets.pet.domain;

import e1i4.petmily.pets.entry.domain.Entry;
import e1i4.petmily.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer age;

    private LocalDate birthDate;

    private LocalDate meetDate;

    private String healthIssue;

    @Column(length = 500)
    private String petImageUrl;

    @Column(length = 7)
    private String colorHex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Entry> entries = new ArrayList<>();

    public enum Gender{
        MALE, FEMALE, UNKNOWN
    }
}
