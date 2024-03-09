package org.rail.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
@RedisHash("user")
public class User {
    @Id
    private String id;
    private String name;
    private String username;
    private String email;
    private String addresses;
    private String phone;
    private String website;
}
