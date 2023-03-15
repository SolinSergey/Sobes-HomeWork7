package ru.gb.homework7.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Поле обязательно для заполнения")
    @Size(min = 5, max = 40, message = "Мин 5, макс 40 символов!")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Поле возраст - обязательно для заполнения")
    @Min(value = 17, message = "Возраст должен быть не меньше 17")
    @Max(value = 60, message = "Куда Вы прётесь - дедуля/бабуля!!!")
    @Column(name = "age")
    private Integer age;

}
