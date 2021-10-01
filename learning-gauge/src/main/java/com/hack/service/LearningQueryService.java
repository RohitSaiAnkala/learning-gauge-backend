package com.hack.service;

import com.hack.database.entity.AssociateEntity;
import com.hack.database.entity.LearningEntity;
import com.hack.database.repository.AssociateRepository;
import com.hack.database.repository.LearningRepository;
import com.hack.response.LeaderBoardResponse;
import com.hack.response.MonthResponse;
import com.hack.response.PlatformResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LearningQueryService implements LearningQueryInterface {

  private final LearningRepository learningRepository;
  private final AssociateRepository associateRepository;

  @Override
  public List<LearningEntity> readAll() {
    return learningRepository.findAll();
  }

  @Override
  public List<PlatformResponse> getTotalHoursByPlatform() {
    List<Object[]> list = learningRepository.findAllPlatformResponses();
    return getPlatformResponses(list);
  }

  @Override
  public List<LeaderBoardResponse> getTopTenPerformers() {
    List<Object[]> list = learningRepository.getTopTenPerformers();
    return getLeaderBoardResponses(list);
  }

  @Override
  public List<PlatformResponse> getTotalHoursByPlatformAndOrg(String org) {
    List<Object[]> list = learningRepository.findAllPlatformResponsesByOrg(org);
    return getPlatformResponses(list);
  }

  @Override
  public List<LeaderBoardResponse> getTopTenPerformersByOrg(String org) {
    List<Object[]> list = learningRepository.getTopTenPerformersByOrg(org);
    return getLeaderBoardResponses(list);
  }

  @Override
  public List<MonthResponse> getTotalHoursByMonth() {
    Map<String, Double> map = new HashMap<>();
    fillMap(map);
    fillLinkedIn(map, false, "");
    fillUdemy(map, false, "");
    fillZoom(map, false, "");
    return helperTotalHoursByMonth(map);
  }

  @Override
  public List<MonthResponse> getTotalHoursByMonthByOrg(String org) {
    Map<String, Double> map = new HashMap<>();
    fillMap(map);
    fillLinkedIn(map, true, org);
    fillUdemy(map, true, org);
    fillZoom(map, true, org);
    return helperTotalHoursByMonth(map);
  }

  private List<MonthResponse> helperTotalHoursByMonth(Map<String, Double> map) {
    List<MonthResponse> result = new ArrayList<>();
    String[] months = {
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    };
    for (String month : months) {
      Double value = map.get(month);
      MonthResponse response = new MonthResponse(month, value);
      result.add(response);
    }
    return result;
  }

  private List<PlatformResponse> getPlatformResponses(List<Object[]> list) {
    List<PlatformResponse> result = new ArrayList<>();
    for (Object[] obj : list) {
      PlatformResponse response = new PlatformResponse((String) obj[0], (Double) obj[1]);
      result.add(response);
    }
    return result;
  }

  private List<LeaderBoardResponse> getLeaderBoardResponses(List<Object[]> list) {
    List<LeaderBoardResponse> result = new ArrayList<>();
    for (Object[] obj : list) {
      AssociateEntity entity = associateRepository.findByAssociateId(obj[0].toString());
      String employeeName = entity != null ? entity.getName() : "";
      LeaderBoardResponse response = new LeaderBoardResponse(employeeName, (Double) obj[1]);
      result.add(response);
    }
    return result;
  }

  private void fillMap(Map<String, Double> map) {
    map.put("January", 0.0);
    map.put("February", 0.0);
    map.put("March", 0.0);
    map.put("April", 0.0);
    map.put("May", 0.0);
    map.put("June", 0.0);
    map.put("July", 0.0);
    map.put("August", 0.0);
    map.put("September", 0.0);
    map.put("October", 0.0);
    map.put("November", 0.0);
    map.put("December", 0.0);
  }

  private void fillLinkedIn(Map<String, Double> map, Boolean isOrgRequired, String org) {
    List<LearningEntity> list;
    if (Boolean.TRUE.equals(isOrgRequired)) {
      list = learningRepository.findAllByFacilitatorAndOrg("LinkedIn", org);
    } else {
      list = learningRepository.findAllByFacilitator("LinkedIn");
    }
    for (LearningEntity entity : list) {
      String date = entity.getStartDate();
      //  8/13/21, 8:56 AM
      if (date.isEmpty()) {
        continue;
      }
      String[] splitted = date.split("/");
      if (splitted.length == 0) {
        continue;
      }
      calculateHoursByMonth(map, splitted[0], entity.getHours());
    }
  }

  private void fillUdemy(Map<String, Double> map, Boolean isOrgRequired, String org) {
    List<LearningEntity> list;
    if (Boolean.TRUE.equals(isOrgRequired)) {
      list = learningRepository.findAllByFacilitatorAndOrg("Udemy", org);
    } else {
      list = learningRepository.findAllByFacilitator("Udemy");
    }
    fill(map, list);
  }

  private void fillZoom(Map<String, Double> map, Boolean isOrgRequired, String org) {
    List<LearningEntity> list;
    if (Boolean.TRUE.equals(isOrgRequired)) {
      list = learningRepository.findAllByFacilitatorAndOrg("Zoom", org);
    } else {
      list = learningRepository.findAllByFacilitator("Zoom");
    }
    fill(map, list);
  }

  private void fill(Map<String, Double> map, List<LearningEntity> list) {
    for (LearningEntity entity : list) {
      String date = entity.getStartDate();
      if (date.isEmpty()) {
        continue;
      }
      // 2019-03-01T12:34:12.660Z
      calculateHoursByMonth(map, date.substring(5, 7), entity.getHours());
    }
  }

  private void calculateHoursByMonth(Map<String, Double> map, String month, double hours) {
    switch (month) {
      case "01":
      case "1":
        map.put("January", map.get("January") + hours);
        break;
      case "02":
      case "2":
        map.put("February", map.get("February") + hours);
        break;
      case "03":
      case "3":
        map.put("March", map.get("March") + hours);
        break;
      case "04":
      case "4":
        map.put("April", map.get("April") + hours);
        break;
      case "05":
      case "5":
        map.put("May", map.get("May") + hours);
        break;
      case "06":
      case "6":
        map.put("June", map.get("June") + hours);
        break;
      case "07":
      case "7":
        map.put("July", map.get("July") + hours);
        break;
      case "08":
      case "8":
        map.put("August", map.get("August") + hours);
        break;
      case "09":
      case "9":
        map.put("September", map.get("September") + hours);
        break;
      case "10":
        map.put("October", map.get("October") + hours);
        break;
      case "11":
        map.put("November", map.get("November") + hours);
        break;
      case "12":
        map.put("December", map.get("December") + hours);
        break;
      default:
        break;
    }
  }
}
