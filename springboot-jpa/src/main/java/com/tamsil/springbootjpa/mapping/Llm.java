package com.tamsil.springbootjpa.mapping;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Llm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String modelName;
    private String llmType;
    private String description;
}
