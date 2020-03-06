package com.hexing.forecast;

import com.hexing.forecast.entity.HolidayDateEntity;
import com.hexing.forecast.repository.HolidayDateRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
class ForecastTests {
    @Autowired
    HolidayDateRepository holidayDateRepository;
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void redisTest() {
        redisTemplate.opsForValue().set("abc", "123");
    }

    @Test
    public void readRedis() {
        Object o = redisTemplate.opsForValue().get("abc");
        System.out.println(o.toString());
    }

    @Test
    public void contextLoads() {
        System.out.println("111");
    }

    @Test
    public void createHoliday() {
        HolidayDateEntity holidayDateEntity = new HolidayDateEntity();
        Date date = new Date();
        holidayDateEntity.setDate(new Timestamp(date.getTime()));
        holidayDateRepository.save(holidayDateEntity);
    }
}
