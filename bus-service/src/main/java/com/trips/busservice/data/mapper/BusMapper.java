package com.trips.busservice.data.mapper;

import com.trips.busservice.data.entity.BusEntity;
import com.trips.busservice.data.dto.BusDetailsDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BusMapper {
    private final ModelMapper modelMapper;

    public BusMapper() {
        modelMapper = new ModelMapper();
    }

    public BusEntity toBusEntity(BusDetailsDto busDetailsDto) {
        return modelMapper.map(busDetailsDto, BusEntity.class);
    }

    public List<BusEntity> toBusEntities(List<BusDetailsDto> busDetailsDtoList) {
        return List.of(modelMapper.map(busDetailsDtoList, BusEntity[].class));
    }

    public List<BusDetailsDto> toBusDetails(List<BusEntity> busEntities) {
        return List.of(modelMapper.map(busEntities, BusDetailsDto[].class));
    }


    public BusDetailsDto toBusDetailsDto(BusEntity busEntity) {
        return modelMapper.map(busEntity, BusDetailsDto.class);
    }
}
