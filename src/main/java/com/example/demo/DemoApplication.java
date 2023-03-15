package com.example.demo;

import com.example.demo.modelTable.*;
import com.example.demo.modelTable.util.EnrolmentId;
import com.example.demo.reponsitory.StudentIdCardRepository;
import com.example.demo.reponsitory.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner (
			StudentRepository studentRepository,
			StudentIdCardRepository studentIdCardRepository
	){
		// trả về thông số
		return args -> {
			//khởi tạo tên demo để demo cho database
			Student demo = new Student(
					"dan",
					"blcw",
					"diodaiyota@donothing.com",
					20
			);
			//thêm 1 vài quyển sách
			demo.addBookToList(new Book("Who Am I", LocalDateTime.now().minusDays(4)));
			demo.addBookToList(new Book("Just do it", LocalDateTime.now().minusDays(4)));
			demo.addBookToList(new Book("for what", LocalDateTime.now().minusDays(4)));
			//mã thẻ của student id
			StudentIdCard studentIdCard = new StudentIdCard(
					demo,
					"123456789"
			);
			//cài đặt thẻ StudentIdCard
			demo.setStudentIdCard(studentIdCard);
			// thêm đăng ký học, phòng, time
			demo.addEnrolmentFromList(new Enrolment(
					new EnrolmentId(1L, 1L),
					demo,
					new Course("Computer Science", "A702"),
					LocalDateTime.now()
			));
			//tương tự
			demo.addEnrolmentFromList(new Enrolment(
					new EnrolmentId(1L, 2L),
					demo,
					new Course("Spring Data JPA", "A706"),
					LocalDateTime.now().minusDays(18)
			));
			//tương tự
			demo.addEnrolmentFromList(new Enrolment(
					new EnrolmentId(1L, 2L),
					demo,
					new Course("Data Structure", "A705"),
					LocalDateTime.now().minusDays(18)
			));
			//lưu demo vào reponsity - lưu ý demo = 1 object student
			studentRepository.save(demo);
			//tìm - truy vấn 1L - tức là tất cả các sách dựa vào id và từ từ Book in ra đối tượng mượn sách
			studentRepository.findById(1L)
					.ifPresent(s -> {
						System.out.println("fetch book lazy...");
						List<Book> books = demo.getBooks();
						books.forEach(book -> {
							System.out.println(
									s.getFirstName()
									+ " borrowed "
									+ book.getBookName()
							);
						});
					});

		};
	}
}
/*
	test nếu thích
 			studentRepository.findStudentById(1L)
					.ifPresent(s -> {
						System.out.println("fetch book lazy...");
						List<Book> books = demo.getBooks();
						books.forEach(book -> {
							System.out.println(
									s.getFirstName() + " borrowed " + book.getBookName());
						});
					});

			studentIdCardRepository.findById(1L)
					.ifPresent(System.out::println);

            studentRepository.deleteById(1L);
* */