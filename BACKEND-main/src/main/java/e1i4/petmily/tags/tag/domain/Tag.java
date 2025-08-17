package e1i4.petmily.tags.tag.domain;

import e1i4.petmily.pets.entry.domain.Entry;
import e1i4.petmily.posts.post.domain.Post;
import e1i4.petmily.tags.tagCategory.domain.TagCategory;
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
