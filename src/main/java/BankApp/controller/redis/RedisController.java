package BankApp.controller.redis;

import BankApp.dto.redis.RedisRequestDto;
import BankApp.service.redis.RedisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/redis")
@RequiredArgsConstructor
public class RedisController {

    private final RedisService redisService;

    @PostMapping("/set")
    public String save(@Valid @RequestBody RedisRequestDto redisRequestDto) {
        redisService.set(redisRequestDto.getEmail(),redisRequestDto.getFullName());
        return "Saved: " + redisRequestDto.getEmail() + " -> " + redisRequestDto.getFullName();
    }

    @GetMapping("/get/{email}")
    public String get(@PathVariable String email) {
        return "Value -> " + redisService.get(email);
    }

    @GetMapping("/getAll")
    public Map<String, String> getAllValues() {
        return redisService.getAll();
    }

    @DeleteMapping("/delete/{email}")
    public String delete(@PathVariable String email) {
        redisService.delete(email);
        return "Deleted key: " + email;
    }
}
