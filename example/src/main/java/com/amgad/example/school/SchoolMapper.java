package com.amgad.example.school;

import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {
    public School toSchool(SchoolDto schoolDto)
    {
        School school=new School();
        school.setName(schoolDto.getName());
        return school;
    }
    public SchoolDto toSchoolDto (School school)
    {
        SchoolDto schoolDto=new SchoolDto();
        schoolDto.setName(school.getName());
        return schoolDto;
    }
}
