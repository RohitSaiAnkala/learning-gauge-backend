package com.hack.controller;

import com.hack.request.LinkedInRequest;
import com.hack.request.UdemyRequest;
import com.hack.request.ZoomRequest;
import com.hack.service.LearningCommandInterface;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/learning")
public class LearningCommandController {

  private final LearningCommandInterface learningCommandInterface;

  @PostMapping("/linkedIn/create")
  public void createLinkedIn(@RequestBody List<LinkedInRequest> linkedInRequestList) {
    learningCommandInterface.createLinkedIn(linkedInRequestList);
  }

  @PostMapping("/udemy/create")
  public void createUdemy(@RequestBody List<UdemyRequest> udemyRequestList) {
    learningCommandInterface.createUdemy(udemyRequestList);
  }

  @PostMapping("/zoom/create")
  public void createZoom(@RequestBody ZoomRequest zoomRequest) {
    learningCommandInterface.createZoom(zoomRequest);
  }
}
