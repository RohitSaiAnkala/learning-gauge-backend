package com.hack.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "learning")
public class LearningEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "employee_id")
  private Long employeeId;
  private Double hours;
  private String courseName;
  private String category;
  private String courseType;
  private String facilitator;
  private String startDate;
  private String endDate;
  private String platform;
  private String org;
}
