package br.edu.imepac.services;

import br.edu.imepac.dtos.AtendimentoCreateRequest;
import br.edu.imepac.dtos.AtendimentoDto;
import br.edu.imepac.models.AtendimentoModel;
import br.edu.imepac.repository.AtendimentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;
    private final static Logger logger = LoggerFactory.getLogger(AtendimentoService.class);

    public AtendimentoDto createAtendimento(AtendimentoCreateRequest atendimentoCreateRequest) {
        AtendimentoModel atendimentoModel = new AtendimentoModel();
        atendimentoModel.setIdAgenda(atendimentoCreateRequest.getIdAgenda());
        atendimentoModel.setHistorico(atendimentoCreateRequest.getHistorico());
        atendimentoModel.setReceituario(atendimentoCreateRequest.getReceituario());
        atendimentoModel.setExames(atendimentoCreateRequest.getExames());

        AtendimentoModel saveAtendimento = atendimentoRepository.save(atendimentoModel);
        return toDto(saveAtendimento);
    }

    public List<AtendimentoDto> getAllAtendimentos() {
        logger.info("Buscando todos os atendimentos");
        return atendimentoRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
        /*List<AtendimentoModel> atendimentos = atendimentoRepository.findAll();
        return atendimentos.stream().map(atendimento -> {
            AtendimentoDto atendimentoDto = toDto(atendimento);
            return atendimentoDto;
        }).collect(Collectors.toList());*/
    }

    public Optional<AtendimentoDto> getAtendimentoById(Long id) {
        logger.info("Buscando atendimento de id {}", id);
        return atendimentoRepository.findById(id).map(this::toDto);
    }

    public AtendimentoDto updateAtendimento(Long id, AtendimentoCreateRequest atendimentoCreateRequest) {
        logger.info("Buscando atendimento para atualizar de id {}", id);
        AtendimentoModel atendimentoModel = atendimentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Atendimento não encontrado"));
        logger.info("Atendimento encontrado");
        atendimentoModel.setIdAgenda(atendimentoCreateRequest.getIdAgenda());
        atendimentoModel.setHistorico(atendimentoCreateRequest.getHistorico());
        atendimentoModel.setReceituario(atendimentoCreateRequest.getReceituario());
        atendimentoModel.setExames(atendimentoCreateRequest.getExames());

        AtendimentoModel updateAtendimento =  atendimentoRepository.save(atendimentoModel);
        logger.info("Atendimento Realizado");
        return toDto(updateAtendimento);
    }

    public void deleteAtendimento(Long id) {
        logger.info("Procurando atendimendo para remover de id {}", id);
        atendimentoRepository.deleteById(id);
        logger.info("Atendimento removido");
    }



    private AtendimentoDto toDto(AtendimentoModel atendimentoModel) {
        AtendimentoDto atendimentoDto = new AtendimentoDto();
        atendimentoDto.setId(atendimentoModel.getId());
        atendimentoDto.setIdAgenda(atendimentoModel.getIdAgenda());
        atendimentoDto.setHistorico(atendimentoModel.getHistorico());
        atendimentoDto.setReceituario(atendimentoModel.getReceituario());
        atendimentoDto.setExames(atendimentoModel.getExames());

        return atendimentoDto;
    }
}
