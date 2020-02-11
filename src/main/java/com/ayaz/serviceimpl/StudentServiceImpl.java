package com.ayaz.serviceimpl;

import com.ayaz.domain.Student;
import com.ayaz.repository.StudentRepository;
import com.ayaz.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository repository;

    @Override
    public List<Student> findAllStudents() {
        List<Student> studentList = repository.findAll();
        logger.info("Student list from the DB - {} ",studentList);
        return studentList;
    }

    @Override
    public Student findStudentById(int id) {
        Student student = repository.findById(id).get();
        logger.info("Student from the DB - {} ",student);
        return student;
    }

    @Override
    public void addStudent(Student student) {
        try {
            repository.save(student);
            logger.info("Student added - {}",student);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }

    @Override
    public void deleteStudentById(int id) {
        try {
            repository.deleteById(id);
            logger.info("Student with {} deleted from the DB", id);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }

    @Override
    public List<Student> getAllStudentByFirstName(String firstName) {
        return repository.getAllStudentByFirstName(firstName);
    }

    @Override
    public List<Student> getStudentsByGender(String gender) {
        return repository.getStudentsByGender(gender);
    }

    @Override
    public void updateEmail(int id, String newEmail) {
        try {
            repository.updateEmail(id,newEmail);
            logger.info("Updated email {} "+id,newEmail);
        }catch (Exception e){
            logger.error("Didn't Update newEmail {}");
        }
    }

    @Override
    public List<Student> findSortedStudents(String paramForSorting) {
        return repository.findAll(Sort.by(paramForSorting));
    }

    @Override
    public List<Student> getStudentsByGenderAndSort(String gender, String sortingParam) {
        return repository.getStudentsByGenderAndShort(gender,Sort.by(sortingParam));
    }

    @Override
    public Page<Student> getAllStudentsByPages(int pageNumber, int numberOfElementsPerPage) {
        return repository.findAll(PageRequest.of(pageNumber,numberOfElementsPerPage));
    }

    @Override
    public Slice<Student> getAllStudentsBySlices(int pageNumber, int numberOfElementsPerPage) {
        return repository.findAll(PageRequest.of(pageNumber,numberOfElementsPerPage));
    }
}
