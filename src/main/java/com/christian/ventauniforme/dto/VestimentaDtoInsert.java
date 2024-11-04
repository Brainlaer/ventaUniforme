package com.christian.ventauniforme.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VestimentaDtoInsert {
    private VestimentaDto vestimentaDto;
    private List<TallaDto> tallaDtoList;
}
