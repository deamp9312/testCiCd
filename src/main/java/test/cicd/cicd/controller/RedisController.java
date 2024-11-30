package test.cicd.cicd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.cicd.cicd.dto.RedisRequest;
import test.cicd.cicd.dto.UserDto;
import test.cicd.cicd.service.RedisService;


@RestController
public class RedisController {

    @Autowired
    RedisService redisService;

    @PostMapping("/api/string/insert")
    public void stringSave(@RequestBody RedisRequest request) {
        redisService.saveString(request);
    }

    @PostMapping("/api/string/read")
    public void stringRead(@RequestBody RedisRequest request){
        String value = redisService.getString(request.getKey());
        System.out.println("Retrieved: " + value);
    }

    @PostMapping("/api/integer/insert")
    public void integerSave(@RequestBody RedisRequest request) {
        redisService.saveInteger(request);
    }

    @PostMapping("/api/integer/read")
    public void integerRead(@RequestBody RedisRequest request){
        Integer value = redisService.getInteger(request.getKey());
        System.out.println("Retrieved Integer: " + value);
    }

    @PostMapping("/api/dto/insert")
    public void dtoSave(@RequestBody RedisRequest request) {
        UserDto user = new UserDto("1", "John Doe");
        redisService.saveObject("user:1", user);
    }

    @PostMapping("/api/dto/read")
    public ResponseEntity<?> dtoRead(@RequestBody RedisRequest request){
        UserDto retrievedUser = redisService.getObject("user:1", UserDto.class);
        System.out.println("Retrieved User: " + retrievedUser.getName());
        return ResponseEntity.ok(retrievedUser);
    }
}
