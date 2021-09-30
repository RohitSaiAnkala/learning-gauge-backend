package com.hack.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UdemyRequest {

  @JsonProperty("User First Name")
  private String userFirstName;
  @JsonProperty("User Last Name")
  private String userLastName;
  @JsonProperty("User Email")
  private String userEmail;
  @JsonProperty("External ID")
  private String externalId;
  @JsonProperty("User Groups")
  private String userGroups;
  @JsonProperty("Course ID")
  private Long courseId;
  @JsonProperty("Course Title")
  private String courseTitle;
  @JsonProperty("Course Duration")
  private Double courseDuration;
  @JsonProperty("% Marked Completed")
  private Double percentageMarkedCompleted;
  @JsonProperty("Minutes Video Consumed")
  private Double minutesVideoConsumed;
  @JsonProperty("Date Enrolled")
  private String dateEnrolled;
  @JsonProperty("Date Started")
  private String dateStarted;
  @JsonProperty("First Date Completed")
  private String firstDateCompleted;
  @JsonProperty("Date Completed")
  private String dateCompleted;
  @JsonProperty("Date Last Accessed")
  private String dateLastAccessed;
  @JsonProperty("Course Category")
  private String courseCategory;
  @JsonProperty("Assigned")
  private String assigned;
  @JsonProperty("Assigned By")
  private String assignedBy;
  @JsonProperty("User Is Deactivated")
  private String isUserDeactivated;

}
