package com.kmzko.configurator.dto.user;

import com.kmzko.configurator.dto.AbstractDto;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto extends AbstractDto implements GrantedAuthority {
    private String name;

    @Override
    public String getAuthority() {
        return getName();
    }
}
