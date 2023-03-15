package com.example.demo.reponsitory;

import com.example.demo.modelTable.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    /*
        sử dụng @Query, để sử dụng truy vấn JPQL(Hibernate) hoặc raw SQL
        ở đây sử dụng Native SQL vì câu lệnh JPQL thì bị lỗi => chưa có giải pháp
        Cách truyền tham số là gọi theo thứ tự các tham số của method bên dưới ?1, ?2 => là kiểu ?{number}
        nếu dùng không quen thì có thể đặt tên theo tham số
        VD : có 1 tham số String email thì chỗ ?1 thay bằng :email

        Ở dưới là các câu lệnh truy vấn khá đơn giản nên tự đọc hiểu lại nếu quên(trường hợp quên - Hương đần)
    */
    @Query(value = "SELECT s FROM Student s WHERE s.email = ?1",
            nativeQuery = true
    )
    Optional<Student> findStudentByEmail(String email);
    /*
        nếu như StudentRepository extends với PagingAndSortingRepository thì có thể thêm

        @Query(value = "SELECT s FROM student s WHERE s.id = ?1",
                nativeQuery = true
        )
        Optional<Student> findStudentById(Long id);
    */

    @Query(value = "SELECT s FROM Student s WHERE s.firstName = ?1 AND s.age >= ?2",
            nativeQuery = true
    )
    List<Student> selectStudentWhereFirstNameAndAgeGreaterOrEqual(
            String firstName,
            Integer age
    );
    @Query(
            value = "SELECT * FROM Student WHERE first_name = :firstName AND age >= :age",
            nativeQuery = true
    )
    List<Student> selectStudentWhereFirstNameAndAgeGreaterOrEqualNative(
            @Param("firstName") String firstName,
            @Param("age") Integer age
    );
    /*
       Transactionallà một tiến trình xử lý có xác định điểm đầu và điểm cuối, được chia nhỏ thành các operation
      (phép thực thi) , tiến trình được thực thi một cách tuần tự và độc lập các operation đó theo
      nguyên tắc hoặc tất cả đều thành công hoặc một operation thất bại thì toàn bộ tiến trình
      thất bại.
       Chú thích @Modifying được sử dụng để tăng cường chú thích
       @Query để chúng tôi có thể thực hiện không chỉ các truy vấn CHỌN mà còn cả các truy vấn INSERT , UPDATE , DELETE và thậm chí cả DDL .
    */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Student u WHERE u.id = ?1",
            nativeQuery = true
    )
    int deleteStudentById(Long Id);
}
