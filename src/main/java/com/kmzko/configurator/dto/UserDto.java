package com.kmzko.configurator.dto;

import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto extends AbstractDto {
    private String email;
    private String name;
    private Set<RoleDto> roles;
}
