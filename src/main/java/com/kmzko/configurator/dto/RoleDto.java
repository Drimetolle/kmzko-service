package com.kmzko.configurator.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleDto extends AbstractDto implements GrantedAuthority {
    private String name;

    @Override
    public String getAuthority() {
        return getName();
    }
}
