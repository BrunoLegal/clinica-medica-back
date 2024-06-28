package br.edu.imepac.services;

import br.edu.imepac.dtos.ConvenioCreateRequest;
import br.edu.imepac.dtos.ConvenioDto;
import br.edu.imepac.models.ConvenioModel;
import br.edu.imepac.repositories.ConvenioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConvenioService {

    private static final Logger logger = LoggerFactory.getLogger(ConvenioService.class);

    @Autowired
    private ConvenioRepository convenioRepository;
    private ModelMapper modelMapper;

    public void delete(Long id){
        logger.info("Deletando convênio com ID: {}", id);
        convenioRepository.deleteById(id);
        logger.info("Convênio com ID: {} deletado com sucesso", id);    }

    public List<ConvenioDto> findAll(){
        logger.info("Buscando convênios");
        List<ConvenioModel> convenios = convenioRepository.findAll();
        return convenios.stream().map(convenio ->{
            ConvenioDto convenioDto = new ConvenioDto();
            convenioDto.setId(convenio.getId());
            convenioDto.setEmpresa(convenio.getEmpresa());
            convenioDto.setCnpj(convenio.getCnpj());
            convenioDto.setTelefone(convenio.getTelefone());
            return convenioDto;
        }).collect(Collectors.toList());
    }

    public ConvenioDto update(Long id, ConvenioDto convenioDetails){
        Optional<ConvenioModel> optionalConvenio = convenioRepository.findById(id);

        logger.info("Buscando convênio com id :{}", id);
        if(optionalConvenio.isPresent()){
            ConvenioModel convenioModel = optionalConvenio.get();
            convenioModel.setEmpresa(convenioDetails.getEmpresa());
            convenioModel.setCnpj(convenioDetails.getCnpj());
            convenioModel.setTelefone(convenioDetails.getTelefone());

            ConvenioModel updateConvenio = convenioRepository.save(convenioModel);

            ConvenioDto convenioDto = new ConvenioDto();
            convenioDto.setId(updateConvenio.getId());
            convenioDto.setCnpj(updateConvenio.getCnpj());
            convenioDto.setTelefone(updateConvenio.getTelefone());
            convenioDto.setEmpresa(updateConvenio.getEmpresa());
            logger.info("Convenio de id: {} atualizado", id);
            return convenioDto;

        }else{
            logger.warn("Convênio de id: {} não encontrado", id);
            return null;
        }
    }

    public ConvenioDto save(ConvenioCreateRequest convenioCreateRequest){
        //ConvenioModel convenioModel = modelMapper.map(convenioCreateRequest, ConvenioModel.class);
        ConvenioModel convenioModel = new ConvenioModel();
        convenioModel.setEmpresa(convenioCreateRequest.getEmpresa());
        convenioModel.setCnpj(convenioCreateRequest.getCnpj());
        convenioModel.setTelefone(convenioCreateRequest.getTelefone());

        ConvenioModel savedConvenio = convenioRepository.save(convenioModel);
        logger.info("Convenio {} salvo", savedConvenio);

        ConvenioDto convenioDto = new ConvenioDto();
        convenioDto.setId(savedConvenio.getId());
        convenioDto.setEmpresa(savedConvenio.getEmpresa());
        convenioDto.setCnpj(savedConvenio.getCnpj());
        convenioDto.setTelefone(savedConvenio.getTelefone());

        //ConvenioDto convenioDto = modelMapper.map(savedConvenio, ConvenioDto.class);
        return convenioDto;
    }

    public ConvenioDto findById(Long id){
        logger.info("Buscando convênio de id: {}", id);
        Optional<ConvenioModel> optionalConvenio = convenioRepository.findById(id);
        if(optionalConvenio.isPresent()){
            ConvenioModel convenioModel = optionalConvenio.get();
            ConvenioDto convenioDto = new ConvenioDto();
            convenioDto.setId(convenioModel.getId());
            convenioDto.setCnpj(convenioModel.getCnpj());
            convenioDto.setEmpresa(convenioModel.getEmpresa());
            convenioDto.setTelefone(convenioModel.getTelefone());
            logger.info("Convenio de id: {} encontrado:{}", id,convenioModel);
            return convenioDto;
        }else{
            logger.warn("Convênio de id:{} não encontrado", id);
            return null;
        }
    }
}
