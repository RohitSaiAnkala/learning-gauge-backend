package com.hack.service;

import com.hack.database.entity.AssociateEntity;
import com.hack.database.entity.LearningEntity;
import com.hack.database.repository.AssociateRepository;
import com.hack.database.repository.LearningRepository;
import com.hack.request.LinkedInRequest;
import com.hack.request.ParticipantsRequest;
import com.hack.request.UdemyRequest;
import com.hack.request.ZoomRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LearningCommandService implements LearningCommandInterface {

  private final LearningRepository learningRepository;
  private final AssociateRepository associateRepository;
  private static int count = 0;

  @Override
  public void createLinkedIn(List<LinkedInRequest> linkedInList) {
    for (LinkedInRequest request : linkedInList) {
      LearningEntity entity = mapLinkedInToLearningEntity(request);
      learningRepository.save(entity);
    }
  }

  @Override
  public void createUdemy(List<UdemyRequest> udemyList) {
    for (UdemyRequest request : udemyList) {
      LearningEntity entity = mapUdemyToLearningEntity(request);
      learningRepository.save(entity);
    }
  }

  @Override
  public void createZoom(ZoomRequest zoomRequest) {
    for (ParticipantsRequest request : zoomRequest.getParticipantsRequestList()) {
      LearningEntity entity = mapZoomToLearningEntity(request);
      learningRepository.save(entity);
    }
  }

  public LearningEntity mapLinkedInToLearningEntity(LinkedInRequest request) {

    return LearningEntity.builder()
        .employeeId(Long.parseLong(request.getUniqueUserId()))
        .hours(
            request.getHoursViewed() == null ? 0.0 : Double.parseDouble(request.getHoursViewed()))
        .courseName(request.getContentName())
        .category("Online")
        .courseType("Internal")
        .facilitator("LinkedIn")
        .startDate(request.getStarted())
        .platform("LinkedIn")
        .endDate(request.getLastViewedDuringTimeRange())
        .org(getOrg(request.getUniqueUserId()))
        .build();
  }

  public LearningEntity mapUdemyToLearningEntity(UdemyRequest request) {
    AssociateEntity entity = associateRepository.findByAssociateEmail(request.getUserEmail());
    String employeeId = entity != null ? entity.getAssociateId() : "";
    return LearningEntity.builder()
        .employeeId(employeeId != "" ? Long.parseLong(employeeId) : null)
        .hours(minutesToHours(request.getMinutesVideoConsumed()))
        .courseName(request.getCourseTitle())
        .category(request.getCourseCategory())
        .courseType("Internal")
        .facilitator("Udemy")
        .startDate(request.getDateStarted())
        .endDate(request.getDateLastAccessed())
        .platform("Udemy")
        .org(getOrg(employeeId))
        .build();
  }

  public LearningEntity mapZoomToLearningEntity(ParticipantsRequest request) {
    count++;
    AssociateEntity entity = associateRepository.findByAssociateEmail(request.getUserEmail());
    String employeeId = entity != null ? entity.getAssociateId() : "";
    return LearningEntity.builder()
        .employeeId(employeeId != "" ? Long.parseLong(employeeId) : null)
        .hours(minutesToHoursForZoom(request.getDuration()))
        .courseName("Zoom Course-" + count)
        .category("Online")
        .courseType("Internal")
        .facilitator("Zoom")
        .startDate(request.getJoinTime())
        .endDate(request.getLeaveTime())
        .platform("Zoom")
        .org(getOrg(employeeId))
        .build();
  }

  private static Double minutesToHours(Double minutes) {
    if (minutes == null) {
      return 0.0;
    }
    return minutes / 60.0;
  }

  private static Double minutesToHoursForZoom(String formatted) {
    if (formatted.length() == 0) {
      return 0.0;
    }
    String[] splitted = formatted.split(":");
    Double minutes = Double.parseDouble(splitted[0]);
    if (minutes == null) {
      return 0.0;
    }
    return minutes / 60.0;
  }

  private String getOrg(String employeeId) {
    if (employeeId.isEmpty()) {
      return "";
    }
    AssociateEntity entity = associateRepository.findByAssociateId(employeeId);
    return entity != null ? entity.getOrg() : "";
  }
}
