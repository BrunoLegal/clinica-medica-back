package br.edu.imepac.services;

import br.edu.imepac.dtos.AtendimentoCreateRequest;
import br.edu.imepac.dtos.AtendimentoDto;
import br.edu.imepac.models.AtendimentoModel;
import br.edu.imepac.repository.AtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
