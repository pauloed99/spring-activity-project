package com.activity_group.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class ActivityGroup {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
	private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "O Título não pode ser vazio")
    private String title;
}
