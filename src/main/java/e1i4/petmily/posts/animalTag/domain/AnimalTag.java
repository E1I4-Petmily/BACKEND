package e1i4.petmily.posts.animalTag.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "animal_tags")
public class AnimalTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
