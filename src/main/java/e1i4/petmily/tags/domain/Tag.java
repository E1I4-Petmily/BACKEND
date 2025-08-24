package e1i4.petmily.tags.domain;

import e1i4.petmily.entries.domain.Entry;
import e1i4.petmily.posts.domain.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_category_id", nullable = false)
    private TagCategory tagCategory;

    @ManyToMany(mappedBy = "tags")
    private List<Entry> entries = new ArrayList<>();

    @ManyToMany(mappedBy = "tags")
    private List<Post> posts = new ArrayList<>();
}
