package br.edu.imepac.services;

import br.edu.imepac.dtos.ConvenioDto;
import br.edu.imepac.dtos.MedicoDto;
import br.edu.imepac.models.ConvenioModel;
import br.edu.imepac.models.MedicoModel;
import br.edu.imepac.repositories.ConvenioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConvenioService {

    @Autowired
    private ConvenioRepository convenioRepository;
    private ModelMapper modelMapper;

    public void delete(Long id){
        convenioRepository.deleteById(id);
    }

    public List<ConvenioDto> findAll(){
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

        if(optionalConvenio.isPresent()){
            ConvenioModel convenioModel = optionalConvenio.get();
            convenioModel.setEmpresa(convenioDetails.getEmpresa());
            convenioModel.setCnpj(convenioDetails.getCnpj());
            convenioModel.setTelefone(convenioDetails.getTelefone());

            ConvenioModel updateConvenio = ConvenioRepository.save(convenioModel);

            ConvenioDto convenioDto = new ConvenioDto();
            convenioDto.setId(updateConvenio.getId());
            convenioDto.setCnpj(updateConvenio.getCnpj());
            convenioDto.setTelefone(updateConvenio.getTelefone());
            convenioDto.setEmpresa(updateConvenio.getEmpresa());

            return convenioDto;
        }else{
            return null;
        }
    }

    public ConvenioDto save(ConvenioCreateRequest convenioRequest){
        
    }

    public ConvenioDto findById(Long id){
        Optional<ConvenioModel> optionalConvenio = convenioRepository.findById(id);
    }
}
