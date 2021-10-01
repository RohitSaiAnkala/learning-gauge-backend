package com.hack.controller;

import com.hack.database.entity.LearningEntity;
import com.hack.response.LeaderBoardResponse;
import com.hack.response.MonthResponse;
import com.hack.response.PlatformResponse;
import com.hack.service.LearningQueryInterface;
import com.hack.service.LearningQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/learning")
public class LearningQueryController {

  private final LearningQueryInterface learningQueryInterface;

  @GetMapping("/list")
  public List<LearningEntity> readInvoice() {
    return learningQueryInterface.readAll();
  }

  @GetMapping("/platform")
  public List<PlatformResponse> getTotalHoursByPlatform() {
    return learningQueryInterface.getTotalHoursByPlatform();
  }

  @GetMapping("/leaderboard")
  public List<LeaderBoardResponse> getLeaderBoard() {
    return learningQueryInterface.getTopTenPerformers();
  }

  @GetMapping("/{org}/platform")
  public List<PlatformResponse> getTotalHoursByPlatformAndOrg(@PathVariable String org) {
    return learningQueryInterface.getTotalHoursByPlatformAndOrg(org);
  }

  @GetMapping("/{org}/leaderboard")
  public List<LeaderBoardResponse> getLeaderBoardByOrg(@PathVariable String org) {
    return learningQueryInterface.getTopTenPerformersByOrg(org);
  }

  @GetMapping("/month-hours")
  public List<MonthResponse> getMonthResponse() {
    return learningQueryInterface.getTotalHoursByMonth();
  }

  @GetMapping("/{org}/month-hours")
  public List<MonthResponse> getMonthResponse(@PathVariable String org) {
    return learningQueryInterface.getTotalHoursByMonthByOrg(org);
  }

}
