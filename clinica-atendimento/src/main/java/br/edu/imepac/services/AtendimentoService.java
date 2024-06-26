package br.edu.imepac.services;

import br.edu.imepac.dtos.AtendimentoCreateRequest;
import br.edu.imepac.dtos.AtendimentoDto;
import br.edu.imepac.models.AtendimentoModel;
import br.edu.imepac.repository.AtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    public AtendimentoDto createAtendimento(AtendimentoCreateRequest atendimentoCreateRequest) {
        AtendimentoModel atendimentoModel = new AtendimentoModel();
        atendimentoModel.setPacienteId(atendimentoCreateRequest.getPacienteId());
        atendimentoModel.setMedicoId(atendimentoCreateRequest.getMedicoId());
        atendimentoModel.setDataHora(atendimentoCreateRequest.getDataHora());
        atendimentoModel.setDescricao(atendimentoCreateRequest.getDescricao());

        AtendimentoModel saveAtendimento = atendimentoRepository.save(atendimentoModel);
        return toDto(saveAtendimento);
    }

    public List<AtendimentoDto> getAllAtendimentos() {
        return atendimentoRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<AtendimentoDto> getAtendimentoById(Long id) {
        return atendimentoRepository.findById(id).map(this::toDto);
    }

    public AtendimentoDto updateAtendimento(Long id, AtendimentoCreateRequest atendimentoCreateRequest) {
        AtendimentoModel atendimentoModel = atendimentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Atendimento não encontrado"));
        atendimentoModel.setPacienteId(atendimentoCreateRequest.getPacienteId());
        atendimentoModel.setMedicoId(atendimentoCreateRequest.getMedicoId());
        atendimentoModel.setDataHora(atendimentoCreateRequest.getDataHora());
        atendimentoModel.setDescricao(atendimentoCreateRequest.getDescricao());

        AtendimentoModel updateAtendimento =  atendimentoRepository.save(atendimentoModel);
        return toDto(updateAtendimento);
    }

    public void deleteAtendimento(Long id) {
        atendimentoRepository.deleteById(id);
    }



    private AtendimentoDto toDto(AtendimentoModel atendimentoModel) {
        AtendimentoDto atendimentoDto = new AtendimentoDto();
        atendimentoDto.setId(atendimentoModel.getId());
        atendimentoDto.setPacienteId(atendimentoModel.getPacienteId());
        atendimentoDto.setMedicoId(atendimentoModel.getMedicoId());
        atendimentoDto.setDataHora(atendimentoModel.getDataHora());
        atendimentoDto.setDescricao(atendimentoModel.getDescricao());

        return atendimentoDto;
    }
}
