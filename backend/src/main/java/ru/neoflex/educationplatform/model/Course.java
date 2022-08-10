package ru.neoflex.educationplatform.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courses", schema = "education_platform")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "private", nullable = false)
    private Boolean isPrivate = false;

    @Column(name = "cover")
    private String cover;

    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(mappedBy = "course")
    private Set<Lesson> lessons = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "user_course_link",
            schema = "education_platform",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "course_interest_tag_link",
            schema = "education_platform",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_tag_id"))
    private Set<InterestTag> interestTags = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private User author;

    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

}