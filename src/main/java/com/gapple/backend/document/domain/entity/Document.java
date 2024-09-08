package com.gapple.backend.document.domain.entity;

import com.gapple.backend.authentication.domain.entity.User;
import com.gapple.backend.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "document")
@Getter
@Builder
@AllArgsConstructor
public class Document extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "class_plan_id")
    private ClassPlan classPlan;

    @OneToOne
    @JoinColumn(name = "class_log_id")
    private ClassLog classLog;

    public Document() {
    }
}
