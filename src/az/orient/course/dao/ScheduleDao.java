package az.orient.course.dao;

import az.orient.course.model.AdvancedSearch;
import az.orient.course.model.Schedule;
import az.orient.course.model.Student;

import java.util.List;

public interface ScheduleDao {

    List<Schedule> getScheduleList() throws Exception;

    boolean addSchedule(Schedule schedule) throws Exception;

    Schedule getScheduleById(long scheduleId) throws Exception;

    boolean updateSchedule(Schedule schedule, long scheduleId) throws Exception;

    List<Schedule> searchScheduleData(String keyword) throws Exception;

    List<Schedule> advancedSearchSchedule(AdvancedSearch advancedSearch) throws Exception;

}
