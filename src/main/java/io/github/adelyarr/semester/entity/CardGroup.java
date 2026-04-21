package io.github.adelyarr.semester.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Objects;

@Entity
@Table(name = "card_groups")
@NoArgsConstructor
@Getter
@Setter
public class CardGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_groups_seq")
    @SequenceGenerator(name = "card_groups_seq", sequenceName = "card_groups_sequence")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User author;

    @Column(nullable = false, length = 100)
    private String name;

    private String description;

    @Column(name = "is_public")
    private boolean isPublic = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardGroup that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
