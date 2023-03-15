package ru.gb.homework7.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.homework7.entities.Student;
import ru.gb.homework7.repositories.StudentRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> getAllStudent() {
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }

    public void removeStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public void addNewStudent(Student student) {
        studentRepository.save(student);
    }

    public Student findStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void updateStudent(Student student) {
        studentRepository.save(student);
    }
}
