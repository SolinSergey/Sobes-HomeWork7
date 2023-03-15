package ru.gb.homework7.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.homework7.entities.Student;
import ru.gb.homework7.services.StudentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final StudentService studentService;

    @GetMapping("/")
    public String index() {
        return "redirect:/allStudent";
    }

    @GetMapping("/allStudent")
    public String getAllStudents(Model model) {
        List<Student> studentList = studentService.getAllStudent();
        Student newStudent = new Student();
        model.addAttribute("newStudent", newStudent);
        model.addAttribute("studentsList", studentList);
        return "findAllStudent";
    }

    @GetMapping("/removeStudent")
    public String removeStudent(@RequestParam Long id) {
        studentService.removeStudent(id);
        return "redirect:/allStudent";
    }

    @PostMapping("/addStudent")
    public String addStudent(@Valid @ModelAttribute("newStudent") Student student, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            studentService.addNewStudent(student);
            student = new Student();
        }
        List<Student> studentList = studentService.getAllStudent();
        model.addAttribute("newStudent", student);
        model.addAttribute("studentsList", studentList);
        return "findAllStudent";
    }

    @GetMapping("/editStudent")
    public String editStudent(@RequestParam Long id, Model model) {
        model.addAttribute("student", studentService.findStudentById(id));
        return "editStudentForm";
    }

    @PostMapping("/editStudent")
    public String editStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            studentService.updateStudent(student);
            return "redirect:/allStudent";
        }
        model.addAttribute("student", student);
        return "editStudentFormWithError";
    }
}
