package com.oto.in.repository;

import com.oto.in.entity.Course;
import com.oto.in.entity.CourseMaterial;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@Rollback(value = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    @Order(1)
    public void testSaveCourseMaterial(){

        Course course=Course.builder().name("Python").instructorName("Lynn").build();
        CourseMaterial courseMaterial=CourseMaterial.builder().url("www.python.org").course(course).build();

        CourseMaterial savedCourseMaterial=courseMaterialRepository.save(courseMaterial);
        System.out.println("Saved Course Material: "+savedCourseMaterial);
    }


    @Test
    @Order(2)
    public void testGetCourseMaterial(){
        CourseMaterial courseMaterial=courseMaterialRepository.findById(1l).get();
        System.out.println(courseMaterial);
        Assertions.assertThat(courseMaterial).isNotNull();

        Course course=courseMaterial.getCourse();
        System.out.println(course);
        Assertions.assertThat(course).isNotNull();
    }


    @Test
    @Order(3)
    public void testGetAllCourses(){
       List<CourseMaterial> allCourseMaterials=courseMaterialRepository.findAll();
        System.out.println(allCourseMaterials);
        Assertions.assertThat(allCourseMaterials).isNotNull();
        allCourseMaterials.stream().forEach(courseMaterial -> {
            System.out.println("Course Material ID:"+courseMaterial.getId());
            System.out.println("Course Material URL:"+courseMaterial.getUrl());
            Course course=courseMaterial.getCourse();
            System.out.println("Course ID:"+course.getId());
            System.out.println("Course Name:"+course.getName());
            System.out.println("Course Instructor Name:"+course.getInstructorName());
       });

    }


    @Test
    @Order(4)
    public void testUpdateCourseMaterial(){

        CourseMaterial courseMaterial=courseMaterialRepository.findById(1l).get();
        Course course=courseMaterial.getCourse();
        course.setInstructorName("Raj Prudhvi");

        courseMaterial.setCourse(course);

        CourseMaterial updatedCourseMaterial1=courseMaterialRepository.save(courseMaterial);

        Assertions.assertThat(updatedCourseMaterial1.getCourse().getInstructorName()).isEqualTo("Raj Prudhvi");



    }


}
