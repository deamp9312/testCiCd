package test.cicd.cicd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import test.cicd.cicd.dto.RedisRequest;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplateObject;

    public void saveInteger(RedisRequest request) {
        redisTemplate.opsForValue().set(request.getKey(), request.getValue().toString());
    }

    public Integer getInteger(String key) {
        String value = (String) redisTemplate.opsForValue().get(key);
        return value != null ? Integer.parseInt(value) : null;
    }

    public void saveString(RedisRequest request) {
        try{
            redisTemplate.opsForValue().set(request.getKey(), request.getValue());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getString(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    public void saveObject(String key, Object value) {
        redisTemplateObject.opsForValue().set(key, value);
    }

    public <T> T getObject(String key, Class<T> clazz) {
        return (T) redisTemplateObject.opsForValue().get(key);
    }
}
