package br.edu.imepac.services;

import br.edu.imepac.dtos.EspecialidadeCreateRequest;
import br.edu.imepac.dtos.EspecialidadeDto;
import br.edu.imepac.models.EspecialidadeModel;
import br.edu.imepac.repositories.EspecialidadeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EspecialidadeService {
    private static final Logger logger = LoggerFactory.getLogger(EspecialidadeService.class);

    @Autowired
    private EspecialidadeRepository especialidadeRepository;
    public EspecialidadeDto save(EspecialidadeCreateRequest especialidadeCreateRequest){
        EspecialidadeModel especialidadeModel = new EspecialidadeModel();
        especialidadeModel.setDesc_especialidade(especialidadeCreateRequest.getDesc_especialidade());

        EspecialidadeModel savedEspecialidade = especialidadeRepository.save(especialidadeModel);
        logger.info("Especialidade {} salvo", savedEspecialidade);

        EspecialidadeDto especialidadeDto = new EspecialidadeDto();
        especialidadeDto.setId(savedEspecialidade.getId());
        especialidadeDto.setDesc_especialidade(savedEspecialidade.getDesc_especialidade());

        return especialidadeDto;
    }

    public EspecialidadeDto findById(Long id){
        logger.info("Buscando especialidade de id: {}", id);
        Optional<EspecialidadeModel> optionalEspecialidadeDto = especialidadeRepository.findById(id);
        if(optionalEspecialidadeDto.isPresent()){
            EspecialidadeModel especialidadeModel = optionalEspecialidadeDto.get();
            EspecialidadeDto especialidadeDto = new EspecialidadeDto();
            especialidadeDto.setId(especialidadeModel.getId());
            especialidadeDto.setDesc_especialidade(especialidadeModel.getDesc_especialidade());
            logger.info("Especialidade de id: {} encontrado:{}", id,especialidadeModel);
            return especialidadeDto;
        }else{
            logger.warn("Especialidade de id:{} não encontrado", id);
            return null;
        }

    }

    public List<EspecialidadeDto> findAll(){
        logger.info("Buscando especialidade");
        List<EspecialidadeModel> listaModel = especialidadeRepository.findAll();
        return listaModel.stream().map(especialidade -> {
            EspecialidadeDto especialidadeDto = new EspecialidadeDto();
            especialidadeDto.setId(especialidade.getId());
            especialidadeDto.setDesc_especialidade(especialidade.getDesc_especialidade());
            return especialidadeDto;
        }).collect(Collectors.toList());

    }

    public void delete(Long id){
        logger.info("Deletando especialidade com ID: {}", id);
        especialidadeRepository.deleteById(id);
        logger.info("Especialidade com ID: {} deletado com sucesso", id);
    }


    public EspecialidadeDto update (Long id, EspecialidadeDto especialidadeData){
        logger.info("Buscando especialidade com id :{}", id);
        Optional<EspecialidadeModel> optionalEspecialidadeDto = especialidadeRepository.findById(id);
        if(optionalEspecialidadeDto.isPresent()){
            EspecialidadeModel especialidadeModel = optionalEspecialidadeDto.get();
            especialidadeModel.setDesc_especialidade(especialidadeData.getDesc_especialidade());

            EspecialidadeModel updatedModel = especialidadeRepository.save(especialidadeModel);

            EspecialidadeDto especialidadeDto = new EspecialidadeDto();
            especialidadeDto.setId(updatedModel.getId());
            especialidadeDto.setDesc_especialidade(updatedModel.getDesc_especialidade());
            logger.info("Especialidade de id: {} atualizado", id);
            return especialidadeDto;

        }else{
            logger.warn("Especialidade de id: {} não encontrado", id);
            return null;
        }

    }


}
