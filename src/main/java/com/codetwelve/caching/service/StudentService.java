package com.codetwelve.caching.service;

import com.codetwelve.caching.entity.Student;

public interface StudentService {

    Student saveStudentDetails(Student student);
    Student getStudentDetails(Integer id);
    Student updateStudentDetails(Student student);
    void deleteStudentDetails(Integer id);
}
