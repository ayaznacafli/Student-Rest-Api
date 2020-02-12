package com.ayaz.controller;

import com.ayaz.domain.Student;
import com.ayaz.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        logger.info("getting students..");
        return studentService.findAllStudents();
    }
    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable String id){
        return studentService.findStudentById(Integer.parseInt(id));
    }

    @PostMapping("/student")
    public ResponseEntity<Object> addStudent(@RequestBody Student student){
        studentService.addStudent(student);
        URI path = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(student.getId())
                .toUri();
        return ResponseEntity.created(path).build();
    }

    @DeleteMapping("/student/{id}")
    public String deleteStudent(@PathVariable String id){
        studentService.deleteStudentById(Integer.parseInt(id));
        return "User with Id " + id + " deleted";
    }
    @GetMapping("/getAllStudentByFirstName/{firstName}")
    public List<Student>getAllStudentByFirstName(@PathVariable String firstName){
        return studentService.getAllStudentByFirstName(firstName);
    }
    @GetMapping("/getAllStudentByGender/{gender}")
    public List<Student>getAllStudentByGender(@PathVariable String gender){
        return studentService.getStudentsByGender(gender);
    }
    @PutMapping("/updateAddress/{id}/{newAddress}")
    public String updateAddress(@PathVariable String id, @PathVariable String newAddress){
        studentService.updateEmail(Integer.parseInt(id), newAddress);
        return "address updated!!";
    }

    @GetMapping("/getAllSortedStudent/{sortedParam}")
    public List<Student>getAllSortedStudents(@PathVariable String sortedParam){
        return studentService.findSortedStudents(sortedParam);
    }

    @GetMapping("/getAllStudentByGender/{gender}/{sortedParam}")
    public List<Student>getAllStudentsByGenderAndSort(@PathVariable String gender, @PathVariable String sortedParam){
        return studentService.getStudentsByGenderAndSort(gender, sortedParam);
    }

    @GetMapping("/getAllStudentByPages/{pageNumber}/{numberOfElementsPerPage}")
    public Page<Student> getAllUserByPages(@PathVariable String pageNumber, @PathVariable String numberOfElementsPerPage){
        return studentService.getAllStudentsByPages(Integer.parseInt(pageNumber), Integer.parseInt(numberOfElementsPerPage));
    }

}
