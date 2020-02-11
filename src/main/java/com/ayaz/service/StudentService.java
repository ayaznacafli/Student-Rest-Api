package com.ayaz.service;


import com.ayaz.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface StudentService {
    List<Student> findAllStudents();
    Student findStudentById(int id);
    void addStudent(Student student);
    void deleteStudentById(int id);
    List<Student> getAllStudentByFirstName(String firstName);
    List<Student> getStudentsByGender(String gender);
    void updateEmail(int id, String newEmail);
    List<Student> findSortedStudents(String paramForSorting);
    List<Student> getStudentsByGenderAndSort(String gender, String sortingParam);
    Page<Student> getAllStudentsByPages(int pageNumber, int numberOfElementsPerPage);
    Slice<Student> getAllStudentsBySlices(int pageNumber, int numberOfElementsPerPage);

}
