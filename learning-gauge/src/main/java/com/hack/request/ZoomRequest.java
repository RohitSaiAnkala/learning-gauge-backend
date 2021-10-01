package com.hack.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ZoomRequest {

  @JsonProperty("participants")
  List<ParticipantsRequest> participantsRequestList;
}
