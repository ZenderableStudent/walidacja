package pl.edu.utp.prog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.edu.utp.prog.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	boolean existsByEmail(String value);
}
