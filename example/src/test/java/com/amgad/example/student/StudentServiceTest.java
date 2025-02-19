package com.amgad.example.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {
    //which service we want to test
    @InjectMocks
    private StudentService studentService;

    //declare the dependencies
    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfuly_save_student() {
        //Give
        StudentDto studentDto = new StudentDto(
                "John",
                "Doe",
                "johan@gmail.com",
                1);
        Student student = new Student(
                "John",
                "Doe",
                "johan@gmail.com",
                20);
        Student savedstudent = new Student("John",
                "Doe",
                "johan@gmail.com",
                20);
        savedstudent.setId(1);
        //Mock
        when(studentMapper.toStudent(studentDto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(savedstudent);
        when(studentMapper.toStudentResponseDto(savedstudent)).thenReturn(
                new StudentResponseDto("John",
                        "Doe",
                        "johan@gmail.com"));
        //When
        StudentResponseDto studentResponseDto = studentService.saveStudent(studentDto);
        //then
        assertEquals(studentDto.getFirstName(), studentResponseDto.getFirstName());
        assertEquals(studentDto.getLastName(), studentResponseDto.getLastName());
        assertEquals(studentDto.getEmail(), studentResponseDto.getEmail());
        verify(studentMapper,times(1)).toStudent(studentDto);
        verify(studentRepository,times(1)).save(student);
        verify(studentMapper,times(1)).toStudentResponseDto(savedstudent);

    }

    @Test
    public void should_get_all_students(){
     //Given
     List<Student> students=new ArrayList<>();
     students.add(new Student("John","Doe","johan@gmail.com",20));
     //Mock
        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class))).thenReturn(
                new StudentResponseDto("John","Doe","johan@gmail.com"));
     //When
     List<StudentResponseDto> studentResponseDtos = studentService.getAllStudents();
     //then
     assertEquals(students.size(),studentResponseDtos.size());
     verify(studentRepository,times(1)).findAll();
    }
    @Test
    public void should_get_student_by_id(){
        //Given
        Integer id=1;
        Student student=new Student("John","Doe","johan@gmail.com",20);
        //Mock
         when(studentRepository.findById(id)).thenReturn(Optional.of(student));
         when(studentMapper.toStudentResponseDto(student)).thenReturn(
                 new StudentResponseDto("John",
                 "Doe",
                 "johan@gmail.com"));
        //when
        StudentResponseDto studentResponseDto = studentService.getStudentById(id);
        //then
        assertEquals(studentResponseDto.getFirstName(), student.getFirstName());
        assertEquals(studentResponseDto.getLastName(), student.getLastName());
        assertEquals(studentResponseDto.getEmail(), student.getEmail());
        verify(studentRepository,times(1)).findById(id);
    }
    @Test
    public void should_get_student_by_name(){
        //Given
        String name="John";
        List<Student>students=new ArrayList<>();
        students.add(new Student("John","Doe","johan@gmail.com",20));
        //Mock
        when(studentRepository.findAllByFirstNameContaining(name)).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class))).thenReturn(
                new StudentResponseDto(
                        "John",
                        "Doe",
                        "johan@gmail.com"));
        //when
        List<StudentResponseDto>studentResponseDtos = studentService.getStudentByName(name);
        //then
        assertEquals(studentResponseDtos.size(), students.size());
        verify(studentRepository,times(1)).findAllByFirstNameContaining(name);
    }
}