package com.codetwelve.caching.controller;

import com.codetwelve.caching.entity.Student;
import com.codetwelve.caching.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> saveStudentDetails(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.saveStudentDetails(student), HttpStatus.CREATED);
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getStudentDetails(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.getStudentDetails(id));
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> updateStudentDetails(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudentDetails(student));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Integer> deleteStudentDetails(@PathVariable Integer id) {
        studentService.deleteStudentDetails(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
