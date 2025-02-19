package com.amgad.example.school;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;
    public SchoolService(SchoolRepository schoolRepository,SchoolMapper schoolMapper)
    {
        this.schoolRepository=schoolRepository;
        this.schoolMapper=schoolMapper;
    }
    public SchoolDto createSchool(@RequestBody SchoolDto school){
        School newSchool=schoolMapper.toSchool(school);
        School savedSchool=schoolRepository.save(newSchool);
        return schoolMapper.toSchoolDto(savedSchool);
    }
    public List<SchoolDto> getAllSchools()
    {
        List <School>schoolList=schoolRepository.findAll();
        return  schoolList.stream().map(schoolMapper::toSchoolDto).toList();
    }
}
