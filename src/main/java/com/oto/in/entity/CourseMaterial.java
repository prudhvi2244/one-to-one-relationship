package com.oto.in.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "course_material")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = "course")
@Builder
public class CourseMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String url;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cid",referencedColumnName = "id")
    @MapsId
    private Course course;
}
