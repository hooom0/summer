package com.example.demo.service;

import com.example.demo.dto.PersonDetailRequestDTO;
import com.example.demo.dto.PersonDetailResponseDTO;
import com.example.demo.entity.PersonDetail;
import com.example.demo.mapper.PersonDetailMapper;
import com.example.demo.repository.PersonDetailRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonDetailService {
    private final PersonDetailRepository repository;
    private final PersonDetailMapper mapper;

    // 创建
    @Transactional
    public PersonDetailResponseDTO create(PersonDetailRequestDTO request) {
        PersonDetail entity = mapper.toEntity(request);
        entity.setRegisterTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        return mapper.toResponseDTO(repository.save(entity));
    }

    // 更新
    @Transactional
    public PersonDetailResponseDTO update(Long personId, PersonDetailRequestDTO request) {
        PersonDetail entity = repository.findById(personId)
                .orElseThrow(() -> new RuntimeException("人员不存在"));

        mapper.toEntity(request, entity); // 局部更新
        entity.setUpdateTime(LocalDateTime.now());
        return mapper.toResponseDTO(repository.save(entity));
    }

    // 查询详情
    public PersonDetailResponseDTO getById(Long personId) {
        return repository.findDetailWithPerson(personId)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("人员不存在"));
    }

    // 分页查询
    public Page<PersonDetailResponseDTO> list(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toResponseDTO);
    }

    // 删除
    @Transactional
    public void delete(Long personId) {
        repository.deleteById(personId);
    }
}