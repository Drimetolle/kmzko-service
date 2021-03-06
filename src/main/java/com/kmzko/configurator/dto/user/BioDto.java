package com.kmzko.configurator.dto.user;

import com.kmzko.configurator.dto.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BioDto extends AbstractDto {
    private String email;
    private String name;
    private String username;
    private String phone;
}
