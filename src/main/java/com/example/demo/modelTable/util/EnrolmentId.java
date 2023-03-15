package com.example.demo.modelTable.util;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
/*
    JPA cung cấp annotation @Embeddable để khai báo rằng một lớp sẽ được
    nhúng bởi các entity(Thực thể) khác.Ta tạo ra một class có tên là EnrolmentId
   và đánh annotation @Embeddable cho nó và nó sẽ được nhúng vào entity Enrolment
*/
@Embeddable
public class EnrolmentId implements Serializable {
    @Column(name =  "student_id")
    private  Long studentId;

    @Column(name = "course_id")
    private  Long courseId;

    public EnrolmentId(){}
    public EnrolmentId(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }
    public Long getStudentId() {
        return studentId;
    }
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    public Long getCourseId() {
        return courseId;
    }
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrolmentId that = (EnrolmentId) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}
