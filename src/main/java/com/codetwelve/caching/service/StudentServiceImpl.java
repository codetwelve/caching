package com.codetwelve.caching.service;

import com.codetwelve.caching.entity.Student;
import com.codetwelve.caching.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudentDetails(Student student) {
        return studentRepository.saveAndFlush(student);
    }

    @Override
    @Cacheable(value = "students", key = "#id")
    public Student getStudentDetails(Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }

    @Override
    @CachePut(value = "students", key = "#student.id")
    public Student updateStudentDetails(Student student) {
        return studentRepository.saveAndFlush(student);
    }

    @Override
    @CacheEvict(value = "students", key = "#id")
    public void deleteStudentDetails(Integer id) {
        studentRepository.deleteById(id);
    }
}
