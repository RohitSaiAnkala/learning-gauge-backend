package com.hack.service;

import com.hack.request.LinkedInRequest;
import com.hack.request.UdemyRequest;
import com.hack.request.ParticipantsRequest;
import com.hack.request.ZoomRequest;
import java.util.List;

public interface LearningCommandInterface {
  void createLinkedIn(List<LinkedInRequest> linkedInList);

  void createUdemy(List<UdemyRequest> udemyList);

  void createZoom(ZoomRequest zoomRequest);
}
