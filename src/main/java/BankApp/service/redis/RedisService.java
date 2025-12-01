package BankApp.service.redis;

import BankApp.dto.redis.RedisRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public void set(String email,String fullName) {
        redisTemplate.opsForValue().set(email, fullName,10, TimeUnit.MINUTES);
    }

    public String get(String email) {
        Object value = redisTemplate.opsForValue().get(email);
        return value != null ? value.toString() : "Key not found";
    }

    public Map<String, String> getAll() {
        Set<String> keys = redisTemplate.keys("*");
        Map<String, String> result = new HashMap<>();
        if (keys != null) {
            for (String key : keys) {
                Object value = redisTemplate.opsForValue().get(key);
                result.put(key, value != null ? value.toString() : "null");
            }
        }
        return result;
    }

    public void delete(String email) {
        redisTemplate.delete(email);
    }
}
