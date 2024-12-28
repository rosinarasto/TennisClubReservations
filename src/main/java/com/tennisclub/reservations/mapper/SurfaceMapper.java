package com.tennisclub.reservations.mapper;

import com.tennisclub.reservations.dto.create.SurfaceCreateDto;
import com.tennisclub.reservations.dto.SurfaceDto;
import com.tennisclub.reservations.model.Surface;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SurfaceMapper extends GenericMapper<Surface, SurfaceDto, SurfaceCreateDto, SurfaceDto> {
}
