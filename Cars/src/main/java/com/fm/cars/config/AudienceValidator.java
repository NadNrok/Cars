package com.fm.cars.config;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public class AudienceValidator implements OAuth2TokenValidator<Jwt> {
	private final List<String> audiences;

	AudienceValidator(List<String> audiences) {
		this.audiences = audiences;
	}

	public OAuth2TokenValidatorResult validate(Jwt jwt) {
		List<String> audiences = jwt.getAudience();
		if (audiences.stream().anyMatch(this.audiences::contains)) {
			return OAuth2TokenValidatorResult.success();
		}

		OAuth2Error err = new OAuth2Error(OAuth2ErrorCodes.INVALID_TOKEN);
		return OAuth2TokenValidatorResult.failure(err);
	}

}
