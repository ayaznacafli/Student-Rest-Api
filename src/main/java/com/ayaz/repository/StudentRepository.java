package com.ayaz.repository;

import com.ayaz.domain.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import javax.persistence.NamedQuery;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    // Indexed Query
    @Query(value = "select s from Student s where s.firstname=?1")
    List<Student> getAllStudentByFirstName(String firstName);

    // Named Query
    @Query(value = "select s from Student s where s.gender=:gender")
    List<Student> getStudentsByGender(@Param("gender") String gender1);

    //@Lock(LockModeType.WRITE)
    @Transactional
    @Modifying
    @Query(value = "update Student s set s.email=?2 where s.id=?1")
    void updateEmail(int id, String newEmail);

    //@Lock(LockModeType.READ)
    @Query(value = "select s from Student s where s.gender=:gender")
    List<Student> getStudentsByGenderAndShort(@Param("gender") String gender1, Sort sort);
}
