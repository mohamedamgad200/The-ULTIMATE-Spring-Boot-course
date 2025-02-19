package com.amgad.example.student;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto saveStudent(StudentDto student) {
        Student newStudent = studentMapper.toStudent(student);
        Student savedStudent = studentRepository.save(newStudent);
        return studentMapper.toStudentResponseDto(savedStudent);
    }

    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toStudentResponseDto).toList();
    }

    public StudentResponseDto getStudentById(int id) {
        return studentRepository.findById(id)
                .map(studentMapper::toStudentResponseDto).
                orElse(null);
    }

    public List<StudentResponseDto> getStudentByName(String name) {
        return studentRepository.findAllByFirstNameContaining(name).stream().map(studentMapper::toStudentResponseDto).toList();
    }

    public void deleteStudentById(int id) {

        studentRepository.deleteById(id);
    }
}
