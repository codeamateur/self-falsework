package com.tianqian.self.model.dto.user;

import com.tianqian.self.model.entity.user.SysUser;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysUserMapper {

    SysUserMapper INSTANCE = Mappers.getMapper(SysUserMapper.class);

    @Mappings({
            @Mapping(target = "localAddress",source = "address"),
            @Mapping(target = "test",ignore = true)
     })
    SysUserDto entityToDto(SysUser user);

    @InheritInverseConfiguration
    SysUser fromDto(SysUserDto dto);
}
