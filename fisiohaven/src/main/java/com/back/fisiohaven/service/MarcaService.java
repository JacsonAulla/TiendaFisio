package com.back.fisiohaven.service;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.back.fisiohaven.dtos.marca.MarcaResponseDTO;
import com.back.fisiohaven.repository.MarcaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarcaService {

    private final MarcaRepository marcaRepository;
    private final ModelMapper modelMapper;

    public List<MarcaResponseDTO> listarTodas() {
        return marcaRepository.findAll().stream()
                .map(marca -> modelMapper.map(marca, MarcaResponseDTO.class))
                .toList();
    }
}