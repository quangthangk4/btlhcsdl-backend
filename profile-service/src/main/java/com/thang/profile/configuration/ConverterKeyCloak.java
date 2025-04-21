package com.thang.profile.configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class ConverterKeyCloak implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final String REALM_ACCESS_CLAIM = "realm_access";
    private final String ROLES_CLAIM = "roles";
    private final String ROLE_PREFIX = "ROLE_"; // Tiền tố chuẩn của Spring Security

    @Override
    @SuppressWarnings("unchecked") // Bỏ qua cảnh báo unchecked cast
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaimAsMap(REALM_ACCESS_CLAIM);

        if (realmAccess == null || realmAccess.isEmpty()) {
            return Collections.emptyList(); // Không có claim realm_access
        }

        Object rolesObject = realmAccess.get(ROLES_CLAIM);

        if (!(rolesObject instanceof Collection)) {
            return Collections.emptyList(); // Claim roles không phải là Collection
        }

        Collection<String> roles = (Collection<String>) rolesObject;

        return roles.stream()
                .map(roleName -> ROLE_PREFIX + roleName.toUpperCase()) // Thêm tiền tố ROLE_ và chuyển thành chữ hoa (thường lệ)
                .map(SimpleGrantedAuthority::new) // Tạo SimpleGrantedAuthority
                .collect(Collectors.toList());
    }
}
