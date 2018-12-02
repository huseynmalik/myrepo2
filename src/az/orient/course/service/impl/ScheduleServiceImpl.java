package az.orient.course.service.impl;

import az.orient.course.dao.ScheduleDao;
import az.orient.course.model.AdvancedSearch;
import az.orient.course.model.Schedule;
import az.orient.course.model.Student;
import az.orient.course.service.ScheduleService;

import java.util.List;

public class ScheduleServiceImpl implements ScheduleService {


    private ScheduleDao scheduleDao;

    public ScheduleServiceImpl(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    @Override
    public List<Schedule> getScheduleList() throws Exception {
        return scheduleDao.getScheduleList();
    }

    @Override
    public boolean addSchedule(Schedule schedule) throws Exception {
        return scheduleDao.addSchedule(schedule);
    }

    @Override
    public Schedule getScheduleById(long scheduleId) throws Exception {
        return scheduleDao.getScheduleById(scheduleId);
    }

    @Override
    public boolean updateSchedule(Schedule schedule, long scheduleId) throws Exception {
        return scheduleDao.updateSchedule(schedule,scheduleId);
    }

    @Override
    public List<Schedule> searchScheduleData(String keyword) throws Exception {
        return scheduleDao.searchScheduleData(keyword);
    }

    @Override
    public List<Schedule> advancedSearchSchedule(AdvancedSearch advancedSearch) throws Exception {
        return scheduleDao.advancedSearchSchedule(advancedSearch);
    }
}
