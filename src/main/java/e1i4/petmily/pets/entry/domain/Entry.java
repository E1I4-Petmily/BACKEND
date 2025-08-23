package e1i4.petmily.pets.entry.domain;

import e1i4.petmily.pets.medicalrecord.domain.MedicalRecord;
import e1i4.petmily.pets.medicationintake.domain.MedicationIntake;
import e1i4.petmily.pets.noteimage.domain.NoteImage;
import e1i4.petmily.pets.pet.domain.Pet;
import e1i4.petmily.pets.weightlog.domain.WeightLog;
import e1i4.petmily.tags.tag.domain.Tag;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "entries")
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate entryDate;

    @Enumerated(EnumType.STRING)
    private EntryType entryType;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @OneToMany(mappedBy = "entry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicalRecord> medicalRecords=new ArrayList<>();

    @OneToMany(mappedBy = "entry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicationIntake> medicationIntakes = new ArrayList<>();

    @OneToMany(mappedBy = "entry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WeightLog> weightLogs = new ArrayList<>();

    @OneToMany(mappedBy = "entry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NoteImage> noteImages = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "entry_tags",
            joinColumns = @JoinColumn(name = "entry_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();

    public enum EntryKind {
        MEDICAL, MEDICATION, WEIGHT, NOTE
    }
}
