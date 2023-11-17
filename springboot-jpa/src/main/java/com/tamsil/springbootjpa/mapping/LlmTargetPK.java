package com.tamsil.springbootjpa.mapping;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class LlmTargetPK {

    @Column(name = "SEQ")
    private String seq;

    @ManyToOne(fetch = FetchType.LAZY)
    private Llm llm;

}
