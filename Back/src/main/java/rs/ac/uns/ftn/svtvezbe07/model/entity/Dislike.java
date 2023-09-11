package rs.ac.uns.ftn.svtvezbe07.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Getter
@Setter
@Table(name = "dislike")
public class Dislike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "postId")
    @JsonIgnore
    private Post post;

    @Column
    private Long userId;
}
