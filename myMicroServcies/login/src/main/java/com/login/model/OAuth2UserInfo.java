package com.login.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class OAuth2UserInfo {
	protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes1) {
        this.attributes = attributes1;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();

    public abstract String getImageUrl();
}
