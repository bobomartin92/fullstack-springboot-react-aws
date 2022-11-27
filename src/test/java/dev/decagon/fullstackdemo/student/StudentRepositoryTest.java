package dev.decagon.fullstackdemo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void testFindStudentByEmailTrue() {
        Student student = new Student(
                "Emma Ben",
                "emmaben@email.com",
                Gender.MALE
        );

        underTest.save(student);

        boolean expected = underTest.existsStudentByEmail(student.getEmail());

        assertThat(expected).isTrue();

    }

    @Test
    void testFindStudentByEmailFalse() {
        Student student = new Student(
                "Emma Ben",
                "emmaben@email.com",
                Gender.MALE
        );

        boolean expected = underTest.existsStudentByEmail(student.getEmail());

        assertThat(expected).isFalse();

    }
}