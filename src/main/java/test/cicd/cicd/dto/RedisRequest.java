package test.cicd.cicd.dto;


import lombok.Data;

@Data
public class RedisRequest {

    private String key;
    private Object value;
}
