package com.gapple.backend.document.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "class_plan")
@Getter
@Builder
@AllArgsConstructor
public class ClassPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String subject;

    @Column(columnDefinition = "json")
    private String activityGoal;

    @Column(columnDefinition = "json")
    private String activityContent;

    @Column(columnDefinition = "json")
    private String evaluation;

    @OneToMany(mappedBy = "classPlan")
    private List<ClassPlanActivityType> activityTypes = new ArrayList<>();

    @OneToMany(mappedBy = "classPlan")
    private List<ClassPlanNuriElement> nuriElements = new ArrayList<>();

    public ClassPlan() {
    }
}
