package br.edu.imepac.services;

import br.edu.imepac.dtos.MedicoCreateRequest;
import br.edu.imepac.dtos.MedicoDto;
import br.edu.imepac.models.MedicoModel;
import br.edu.imepac.repositories.MedicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicoService {
    private static final Logger logger = LoggerFactory.getLogger(MedicoService.class);

    @Autowired
    private MedicoRepository medicoRepository;

    public MedicoDto save(MedicoCreateRequest medicoCreateRequest){
        MedicoModel medicoModel = new MedicoModel();
        medicoModel.setCRM(medicoCreateRequest.getCrm());
        medicoModel.setNome(medicoCreateRequest.getNome());
        medicoModel.setEspecialidade(medicoCreateRequest.getEspecialidade());

        MedicoModel savedMedico = medicoRepository.save(medicoModel);
        logger.info("Medico {} salvo", savedMedico);

        MedicoDto medicoDto = new MedicoDto();
        medicoDto.setId(savedMedico.getId());
        medicoDto.setNome(savedMedico.getNome());
        medicoDto.setCrm(savedMedico.getCRM());
        medicoDto.setEspecialidade(savedMedico.getEspecialidade());

        return medicoDto;
    }

    public MedicoDto findById(Long id){
        logger.info("Buscando medico de id: {}", id);
        Optional<MedicoModel> optionalMedicoDto = medicoRepository.findById(id);
        if(optionalMedicoDto.isPresent()){
            MedicoModel medicoModel = optionalMedicoDto.get();
            MedicoDto medicoDto = new MedicoDto();
            medicoDto.setId(medicoModel.getId());
            medicoDto.setNome(medicoModel.getNome());
            medicoDto.setCrm(medicoModel.getCRM());
            medicoDto.setEspecialidade(medicoModel.getEspecialidade());
            logger.info("Medico de id: {} encontrado:{}", id,optionalMedicoDto);
            return medicoDto;
        }else{
            logger.warn("Medico de id:{} não encontrado", id);
            return null;
        }

    }

    public List<MedicoDto> findAll(){
        logger.info("Buscando medico");
        List<MedicoModel> listaModel = medicoRepository.findAll();
        return listaModel.stream().map(medico -> {
            MedicoDto medicoDto = new MedicoDto();
            medicoDto.setNome(medico.getNome());
            medicoDto.setCrm(medico.getCRM());
            medicoDto.setId(medico.getId());
            medicoDto.setEspecialidade(medico.getEspecialidade());
            return medicoDto;
        }).collect(Collectors.toList());

    }

    public void delete(Long id){
        logger.info("Deletando medico com ID: {}", id);

        medicoRepository.deleteById(id);

        logger.info("Medico com ID: {} deletado com sucesso", id);

    }

    public MedicoDto update (Long id, MedicoDto medicoData){
        logger.info("Buscando medico com id :{}", id);
        Optional<MedicoModel> optionalMedicoDto = medicoRepository.findById(id);
        if(optionalMedicoDto.isPresent()){
            MedicoModel medicoModel = optionalMedicoDto.get();
            medicoModel.setNome(medicoData.getNome());
            medicoModel.setCRM(medicoData.getCrm());
            medicoModel.setEspecialidade(medicoData.getEspecialidade());

            MedicoModel updatedModel = medicoRepository.save(medicoModel);

            MedicoDto medicoDto = new MedicoDto();
            medicoDto.setId(updatedModel.getId());
            medicoDto.setNome(updatedModel.getNome());
            medicoDto.setCrm(updatedModel.getCRM());
            medicoDto.setEspecialidade(updatedModel.getEspecialidade());
            logger.info("Medico de id: {} atualizado", id);
            return medicoDto;

        }else{
            logger.warn("Medico de id: {} não encontrado", id);
            return null;
        }

    }


}
