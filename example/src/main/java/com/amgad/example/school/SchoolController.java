package com.amgad.example.school;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchoolController {
    private  SchoolService schoolService;
    public SchoolController(SchoolService schoolService)
    {
        this.schoolService=schoolService;
    }
    @PostMapping("/schools")
    public SchoolDto createSchool(@RequestBody SchoolDto school){
        return schoolService.createSchool(school);
    }
    @GetMapping("/schools")
    public List<SchoolDto> getAllSchools()
    {
        return  schoolService.getAllSchools();
    }
}
