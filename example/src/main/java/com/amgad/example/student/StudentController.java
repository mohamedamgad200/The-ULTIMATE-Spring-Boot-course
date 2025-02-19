package com.amgad.example.student;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {

    private final StudentService studentService;
    public StudentController(StudentService studentService)
    {
        this.studentService=studentService;
    }
    @PostMapping("/students")
    public StudentResponseDto saveStudent( @Valid @RequestBody StudentDto student) {
        return studentService.saveStudent(student);
    }
    @GetMapping("/students")
    public List<StudentResponseDto> getAllStudents() {

        return studentService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public StudentResponseDto getStudentById(@PathVariable int id) {

        return studentService.getStudentById(id);
    }

    @GetMapping("/students/search/{student-name}")
    public List<StudentResponseDto> getStudentById(@PathVariable("student-name") String name) {
        return studentService.getStudentByName(name);
    }

    @DeleteMapping("/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudentById(@PathVariable int id) {
        studentService.deleteStudentById(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handelMethodArgumentNotValidException(MethodArgumentNotValidException exp)
    {
        Map<String,String> errors=new HashMap<>();
        exp.getBindingResult().getAllErrors().forEach(error->{
            String fieldName=((FieldError)error).getField();
            String errorMessage=error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
