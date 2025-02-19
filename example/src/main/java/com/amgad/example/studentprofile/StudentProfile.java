package com.amgad.example.studentprofile;

import com.amgad.example.student.Student;
import jakarta.persistence.*;

@Entity
@Table(name="student-profile")
public class StudentProfile {
    @Id
    @GeneratedValue
    @Column(name="profile-id")
    private Integer id;
    @Column(name = "profile-bio")
    private String bio;
    @OneToOne
    @JoinColumn(name="student-id")
    private Student student;
    public StudentProfile() {
    }

    public StudentProfile(String bio) {
        this.bio = bio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
