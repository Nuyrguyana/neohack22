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
@Table(name = "users_business", schema = "education_platform")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @OneToMany(mappedBy = "user")
    private Set<UserLessonLink> userLessonLinks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "teacher")
    private Set<Lesson> lessonsAsTeacher = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserTaskAnswerLink> userTaskAnswerLinks = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "users_interest_tag_link",
            schema = "education_platform",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_tag_id"))
    private Set<InterestTag> interestTags = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "user_course_link",
            schema = "education_platform",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> coursesAsStudent = new LinkedHashSet<>();


    @OneToMany(mappedBy = "author")
    private Set<Lesson> lessonsAsAuthor = new LinkedHashSet<>();

    @OneToMany(mappedBy = "author")
    private Set<Course> courses = new LinkedHashSet<>();

    @Column(name = "last_visit_date")
    private LocalDate lastVisitDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

}