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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lessons", schema = "education_platform")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher", nullable = false)
    private User teacher;

    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(mappedBy = "lesson")
    private Set<UserLessonLink> userLessonLinks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "lesson")
    private Set<Task> tasks = new LinkedHashSet<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private User author;

}