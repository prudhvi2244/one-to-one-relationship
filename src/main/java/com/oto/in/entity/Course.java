package com.oto.in.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "course")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = "courseMaterial")
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String instructorName;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "course")
    private CourseMaterial courseMaterial;

}
