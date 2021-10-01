package com.hack.service;

import com.hack.database.entity.LearningEntity;
import com.hack.response.LeaderBoardResponse;
import com.hack.response.MonthResponse;
import com.hack.response.PlatformResponse;
import java.util.List;

public interface LearningQueryInterface {

  List<LearningEntity> readAll();

  List<PlatformResponse> getTotalHoursByPlatform();

  List<LeaderBoardResponse> getTopTenPerformers();

  List<PlatformResponse> getTotalHoursByPlatformAndOrg(String org);

  List<LeaderBoardResponse> getTopTenPerformersByOrg(String org);

  List<MonthResponse> getTotalHoursByMonth();

  List<MonthResponse> getTotalHoursByMonthByOrg(String org);
}
