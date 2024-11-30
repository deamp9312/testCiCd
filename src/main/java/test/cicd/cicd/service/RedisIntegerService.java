package test.cicd.cicd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisIntegerService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void saveInteger(String key, Integer value) {
        redisTemplate.opsForValue().set(key, value.toString());
    }

    public Integer getInteger(String key) {
        String value = (String) redisTemplate.opsForValue().get(key);
        return value != null ? Integer.parseInt(value) : null;
    }
}
