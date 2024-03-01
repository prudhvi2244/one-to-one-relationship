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
import java.util.Optional;

@DataJpaTest
@Rollback(value = false)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    @Order(1)
    public void testSaveCourse(){

        CourseMaterial courseMaterial=CourseMaterial.builder().url("www.java.com").build();
        Course course=Course.builder().name("Java-8").instructorName("Raj Prudhvi").build();
        Course savedCourse=courseRepository.save(course);
        courseMaterial.setCourse(savedCourse);
        CourseMaterial savedCourseMaterial= courseMaterialRepository.save(courseMaterial);
        Assertions.assertThat(savedCourse).isNotNull();
        System.out.println(savedCourse);
        System.out.println(savedCourseMaterial);
    }

    @Test
    @Order(2)
    public void testGetCourse(){
        Course course=courseRepository.findById(1l).get();
        System.out.println(course);
        CourseMaterial courseMaterial=course.getCourseMaterial();
        System.out.println(courseMaterial);
        Assertions.assertThat(course).isNotNull();
        Assertions.assertThat(courseMaterial).isNotNull();

    }

    @Test
    @Order(3)
    public void testGetAllCourses(){

        List<Course> allCourses=courseRepository.findAll();

        Assertions.assertThat(allCourses).isNotEmpty();

        allCourses.stream().forEach(course->{
            System.out.println("Course ID:"+course.getId());
            System.out.println("Course Name:"+course.getName());
            System.out.println("Instructor Name:"+course.getInstructorName());
            CourseMaterial cm=course.getCourseMaterial();
            System.out.println("Course Material  ID:"+cm.getId());
            System.out.println("Course Material URL:"+cm.getUrl());
        });


    }

    @Test
    @Order(4)
    public void testUpdateCourse(){

      Optional<Course> opt= courseRepository.findById(1l);

      if(opt.isPresent()){
          Course course=opt.get();
          course.setInstructorName("Pavan");
          Course updatedCourse=courseRepository.save(course);
          System.out.println(updatedCourse);
          Assertions.assertThat(updatedCourse.getInstructorName()).isEqualTo("Pavan");
      }

    }


    @Test
    @Order(5)
    public void testDeleteCourse(){

       Course course=courseRepository.findById(1l).get();
       courseRepository.delete(course);

       Course course1=null;

       Optional<Course> opt=courseRepository.findById(course.getId());
       if(opt.isPresent()){
           course1=opt.get();
       }

       Assertions.assertThat(course1).isNull();

    }


}
