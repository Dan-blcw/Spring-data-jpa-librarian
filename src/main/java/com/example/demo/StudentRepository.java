package com.example.demo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student,Long> {
    @Query(value = "SELECT s FROM Student s WHERE s.email = ?1",nativeQuery = true)
    Optional<Student> findStudentByEmail(String email);
    @Query("")
    List<Student> selectStudentWhereFirstNameAndAgeGreaterOrEqual(
            String firstName,
            Integer age
    );
    @Query(
            value = "SELECT * FROM student WHERE first_name = :firstName AND age >= :age",
            nativeQuery = true
    )
    List<Student> selectStudentWhereFirstNameAndAgeGreaterOrEqualNative(
            @Param("firstName") String firstName,
            @Param("age") Integer age
    );
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Student u WHERE u.id = ?1",nativeQuery = true)
    int deleteStudentById(Long Id);
}
