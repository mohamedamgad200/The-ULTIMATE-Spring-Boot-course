package com.amgad.example.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        studentMapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent() {
        //Given
        StudentDto studentDto = new StudentDto("Johan", "Doe", "john@gmail.com", 1);

        //When
        Student student = studentMapper.toStudent(studentDto);

        //Then
        assertEquals(studentDto.getFirstName(), student.getFirstName());
        assertEquals(studentDto.getLastName(), student.getLastName());
        assertEquals(studentDto.getEmail(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(studentDto.getSchoolId(), student.getSchool().getId());
    }

    @Test
    public void shouldMapStudentToStudentResponseDto() {
        //Given
        Student student = new Student("Johan", "Doe", "john@gmail.com", 20);
        //When
        StudentResponseDto StudentResponseDto = studentMapper.toStudentResponseDto(student);

        //Then
        assertEquals(student.getFirstName(), StudentResponseDto.getFirstName());
        assertEquals(student.getLastName(), StudentResponseDto.getLastName());
        assertEquals(student.getEmail(), StudentResponseDto.getEmail());
    }

    @Test
    public void should_throw_Null_pointer_exception_when_studentDto_is_null() {
        Exception exe = assertThrows(NullPointerException.class, () -> studentMapper.toStudent(null));
        assertEquals("studentDto cannot be null", exe.getMessage());
    }
}