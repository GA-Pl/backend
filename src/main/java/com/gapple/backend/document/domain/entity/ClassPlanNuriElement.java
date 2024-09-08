package com.gapple.backend.document.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "class_plan_nurri_element")
@Getter
@Builder
@AllArgsConstructor
public class ClassPlanNuriElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_plan_id")
    private ClassPlan classPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nuri_element_id")
    private NuriElement nuriElement;

    public ClassPlanNuriElement() {
    }
}
