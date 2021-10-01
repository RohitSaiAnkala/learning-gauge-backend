package com.hack.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LinkedInRequest {

  @JsonProperty("Name")
  private String name;

  @JsonProperty("Email")
  private String email;

  @JsonProperty("Unique User ID")
  private String uniqueUserId;

  @JsonProperty("Content Name")
  private String contentName;

  @JsonProperty("Content Provider")
  private String contentProvider;

  @JsonProperty("Content Type")
  private String contentType;

  @JsonProperty("Content ID")
  private String contentId;

  @JsonProperty("Hours Viewed (LinkedIn Learning and Organization Content Only)")
  private String hoursViewed;

  @JsonProperty("Percent Completed")
  private String percentCompleted;

  @JsonProperty("Started (PST/PDT)")
  private String started;

  @JsonProperty("Last Viewed During Time Range (PST/PDT)")
  private String lastViewedDuringTimeRange;

  @JsonProperty("Completed At (PST/PDT)")
  private String completedAt;

  @JsonProperty("Total Assessments")
  private String totalAssessments;

  @JsonProperty("Number of Assessments Completed")
  private String noOfAssessmentsCompleted;

  @JsonProperty("Skills")
  private String skills;

  @JsonProperty("Course Name (LinkedIn Videos Only)")
  private String courseName;

  @JsonProperty("Course ID (LinkedIn Videos Only)")
  private String courseId;

  @JsonProperty("Groups (At Time Of Engagement)")
  private String groupsAtTheTimeOfEngagement;

  @JsonProperty("Groups (Current Membership)")
  private String groupsCurrentMembership;
}
