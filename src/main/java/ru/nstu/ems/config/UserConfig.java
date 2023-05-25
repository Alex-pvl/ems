package ru.nstu.ems.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "auth.admin")
public class UserConfig {
	public String username;
	public UUID password;
}
