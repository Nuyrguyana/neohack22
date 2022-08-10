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
import javax.persistence.Table;


@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "answers", schema = "education_platform")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "answer_text", nullable = false)
    private String answerText;

    @Column(name = "correct", nullable = false)
    private Boolean correct = false;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

}