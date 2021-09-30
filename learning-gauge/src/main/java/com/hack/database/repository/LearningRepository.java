package com.hack.database.repository;

import com.hack.database.entity.LearningEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningRepository extends JpaRepository<LearningEntity, Long> {
  @Query(
      value =
          "select facilitator as category,sum(hours) as totalHours "
              + "from learning_gauge.learning group by facilitator",
      nativeQuery = true)
  List<Object[]> findAllPlatformResponses();

  @Query(
      value =
          "select employee_id,sum(hours) as totalHours from learning_gauge.learning"
              + " group by employee_id having employee_id is not null "
              + "order by totalHours desc limit 10",
      nativeQuery = true)
  List<Object[]> getTopTenPerformers();

  @Query(
      value =
          "select facilitator as category,sum(hours) as totalHours from learning_gauge.learning "
              + "where org=:org group by facilitator",
      nativeQuery = true)
  List<Object[]> findAllPlatformResponsesByOrg(String org);

  @Query(
      value =
          "select employee_id,sum(hours) as totalHours from learning_gauge.learning "
              + "where employee_id is not null and org=:org group by employee_id "
              + "order by totalHours desc limit 10",
      nativeQuery = true)
  List<Object[]> getTopTenPerformersByOrg(String org);

  List<LearningEntity> findAllByFacilitator(String facilitator);

  List<LearningEntity> findAllByFacilitatorAndOrg(String facilitator, String org);
}
