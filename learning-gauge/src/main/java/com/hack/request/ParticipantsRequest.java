package com.hack.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParticipantsRequest {

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("user_email")
  private String userEmail;

  @JsonProperty("join_time")
  private String joinTime;

  @JsonProperty("leave_time")
  private String leaveTime;

  @JsonProperty("duration")
  private String duration;
}
