package com.example.demo.reponsitory;

import com.example.demo.modelTable.StudentIdCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentIdCardRepository extends CrudRepository<StudentIdCard,Long> {
}
