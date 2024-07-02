package br.edu.imepac.services;

import br.edu.imepac.dtos.ConvenioCreateRequest;
import br.edu.imepac.dtos.ConvenioDto;
import br.edu.imepac.dtos.FuncionarioCreateRequest;
import br.edu.imepac.dtos.FuncionarioDto;
import br.edu.imepac.models.ConvenioModel;
import br.edu.imepac.models.FuncionarioModel;
import br.edu.imepac.repositories.FuncionarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private static final Logger logger = LoggerFactory.getLogger(FuncionarioService.class);

    private ModelMapper modelMapper;


    public void delete (Long id){
        logger.info("Deletando funcionário com ID: {}", id);
        funcionarioRepository.deleteById(id);
        logger.info("Funcionário com ID: {} deletado com sucesso", id);

    }

    public List<FuncionarioDto> findAll(){
        logger.info("Buscando funcionários");
        List<FuncionarioModel> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream().map(funcionario -> {
            FuncionarioDto funcionarioDto = new FuncionarioDto();

            funcionarioDto.setNome_Funcionario(funcionario.getNome_Funcionario());
            funcionarioDto.setNumero_Rg(funcionario.getNumero_Rg());
            funcionarioDto.setOrgao_Emissor(funcionario.getOrgao_Emissor());
            funcionarioDto.setNumero_Cpf(funcionario.getNumero_Cpf());
            funcionarioDto.setEndereco(funcionario.getEndereco());
            funcionarioDto.setNumero(funcionario.getNumero());
            funcionarioDto.setComplemento(funcionario.getComplemento());
            funcionarioDto.setBairro(funcionario.getBairro());
            funcionarioDto.setCidade(funcionario.getCidade());
            funcionarioDto.setEstado(funcionario.getEstado());
            funcionarioDto.setTelefone(funcionario.getTelefone());
            funcionarioDto.setCelular(funcionario.getCelular());
            funcionarioDto.setNumero_Ctps(funcionario.getNumero_Ctps());
            funcionarioDto.setNumero_Pis(funcionario.getNumero_Pis());
            funcionarioDto.setData_de_nascimento(funcionario.getData_de_nascimento());

            return funcionarioDto;
        }).collect(Collectors.toList());
    }

    public FuncionarioDto update (Long id, FuncionarioDto funcionarioData){
        Optional<FuncionarioModel> optionalFuncionarioDto = funcionarioRepository.findById(id);

        logger.info("Buscando funcionário com id :{}", id);
        if(optionalFuncionarioDto.isPresent()){
            FuncionarioModel funcionarioModel = optionalFuncionarioDto.get();

            funcionarioModel.setNome_Funcionario(funcionarioData.getNome_Funcionario());
            funcionarioModel.setNumero_Rg(funcionarioData.getNumero_Rg());
            funcionarioModel.setOrgao_Emissor(funcionarioData.getOrgao_Emissor());
            funcionarioModel.setNumero_Cpf(funcionarioData.getNumero_Cpf());
            funcionarioModel.setEndereco(funcionarioData.getEndereco());
            funcionarioModel.setNumero(funcionarioData.getNumero());
            funcionarioModel.setComplemento(funcionarioData.getComplemento());
            funcionarioModel.setBairro(funcionarioData.getBairro());
            funcionarioModel.setCidade(funcionarioData.getCidade());
            funcionarioModel.setEstado(funcionarioData.getEstado());
            funcionarioModel.setTelefone(funcionarioData.getTelefone());
            funcionarioModel.setCelular(funcionarioData.getCelular());
            funcionarioModel.setNumero_Ctps(funcionarioData.getNumero_Ctps());
            funcionarioModel.setNumero_Pis(funcionarioData.getNumero_Pis());
            funcionarioModel.setData_de_nascimento(funcionarioData.getData_de_nascimento());

            FuncionarioModel updatedModel = funcionarioRepository.save(funcionarioModel);

            FuncionarioDto funcionarioDto = new FuncionarioDto();
            funcionarioDto.setId(updatedModel.getId());
            funcionarioDto.setNome_Funcionario(updatedModel.getNome_Funcionario());
            funcionarioDto.setNumero_Rg(updatedModel.getNumero_Rg());
            funcionarioDto.setOrgao_Emissor(updatedModel.getOrgao_Emissor());
            funcionarioDto.setNumero_Cpf(updatedModel.getNumero_Cpf());
            funcionarioDto.setEndereco(updatedModel.getEndereco());
            funcionarioDto.setNumero(updatedModel.getNumero());
            funcionarioDto.setComplemento(updatedModel.getComplemento());
            funcionarioDto.setBairro(updatedModel.getBairro());
            funcionarioDto.setCidade(updatedModel.getCidade());
            funcionarioDto.setEstado(updatedModel.getEstado());
            funcionarioDto.setTelefone(updatedModel.getTelefone());
            funcionarioDto.setCelular(updatedModel.getCelular());
            funcionarioDto.setNumero_Ctps(updatedModel.getNumero_Ctps());
            funcionarioDto.setNumero_Pis(updatedModel.getNumero_Pis());
            funcionarioDto.setData_de_nascimento(updatedModel.getData_de_nascimento());
            logger.info("Funcionário de id: {} atualizado", id);
            return funcionarioDto;

        }else{
            logger.warn("Funcionário de id: {} não encontrado", id);
            return null;
        }
    }

    public FuncionarioDto save(FuncionarioCreateRequest funcionarioCreateRequest){

        FuncionarioModel funcionarioModel = new FuncionarioModel();
        funcionarioModel.setNome_Funcionario(funcionarioCreateRequest.getNome_Funcionario());
        funcionarioModel.setNumero_Rg(funcionarioCreateRequest.getNumero_Rg());
        funcionarioModel.setOrgao_Emissor(funcionarioCreateRequest.getOrgao_Emissor());
        funcionarioModel.setNumero_Cpf(funcionarioCreateRequest.getNumero_Cpf());
        funcionarioModel.setEndereco(funcionarioCreateRequest.getEndereco());
        funcionarioModel.setNumero(funcionarioCreateRequest.getNumero());
        funcionarioModel.setComplemento(funcionarioCreateRequest.getComplemento());
        funcionarioModel.setBairro(funcionarioCreateRequest.getBairro());
        funcionarioModel.setCidade(funcionarioCreateRequest.getCidade());
        funcionarioModel.setEstado(funcionarioCreateRequest.getEstado());
        funcionarioModel.setTelefone(funcionarioCreateRequest.getTelefone());
        funcionarioModel.setCelular(funcionarioCreateRequest.getCelular());
        funcionarioModel.setNumero_Ctps(funcionarioCreateRequest.getNumero_Ctps());
        funcionarioModel.setNumero_Pis(funcionarioCreateRequest.getNumero_Pis());
        funcionarioModel.setData_de_nascimento(funcionarioCreateRequest.getData_de_nascimento());

        FuncionarioModel savedFuncionario = funcionarioRepository.save(funcionarioModel);
        logger.info("Convenio {} salvo", savedFuncionario);

        FuncionarioDto funcionarioDto = new FuncionarioDto();
        funcionarioDto.setId(savedFuncionario.getId());
        funcionarioDto.setNome_Funcionario(savedFuncionario.getNome_Funcionario());
        funcionarioDto.setNumero_Rg(savedFuncionario.getNumero_Rg());
        funcionarioDto.setOrgao_Emissor(savedFuncionario.getOrgao_Emissor());
        funcionarioDto.setNumero_Cpf(savedFuncionario.getNumero_Cpf());
        funcionarioDto.setEndereco(savedFuncionario.getEndereco());
        funcionarioDto.setNumero(savedFuncionario.getNumero());
        funcionarioDto.setComplemento(savedFuncionario.getComplemento());
        funcionarioDto.setBairro(savedFuncionario.getBairro());
        funcionarioDto.setCidade(savedFuncionario.getCidade());
        funcionarioDto.setEstado(savedFuncionario.getEstado());
        funcionarioDto.setTelefone(savedFuncionario.getTelefone());
        funcionarioDto.setCelular(savedFuncionario.getCelular());
        funcionarioDto.setNumero_Ctps(savedFuncionario.getNumero_Ctps());
        funcionarioDto.setNumero_Pis(savedFuncionario.getNumero_Pis());
        funcionarioDto.setData_de_nascimento(savedFuncionario.getData_de_nascimento());

        return funcionarioDto;
    }


    public FuncionarioDto findById(Long id){
            logger.info("Buscando funcionário de id: {}", id);
            Optional<FuncionarioModel> funcionarioSearch = funcionarioRepository.findById(id);
            if(funcionarioSearch.isPresent()){

            FuncionarioModel funcionarioModel = funcionarioSearch.get();
            FuncionarioDto funcionarioDto = new FuncionarioDto();
            funcionarioDto.setId(funcionarioModel.getId());
            funcionarioDto.setNome_Funcionario(funcionarioModel.getNome_Funcionario());
            funcionarioDto.setNumero_Rg(funcionarioModel.getNumero_Rg());
            funcionarioDto.setOrgao_Emissor(funcionarioModel.getOrgao_Emissor());
            funcionarioDto.setNumero_Cpf(funcionarioModel.getNumero_Cpf());
            funcionarioDto.setEndereco(funcionarioModel.getEndereco());
            funcionarioDto.setNumero(funcionarioModel.getNumero());
            funcionarioDto.setComplemento(funcionarioModel.getComplemento());
            funcionarioDto.setBairro(funcionarioModel.getBairro());
            funcionarioDto.setCidade(funcionarioModel.getCidade());
            funcionarioDto.setEstado(funcionarioModel.getEstado());
            funcionarioDto.setTelefone(funcionarioModel.getTelefone());
            funcionarioDto.setCelular(funcionarioModel.getCelular());
            funcionarioDto.setNumero_Ctps(funcionarioModel.getNumero_Ctps());
            funcionarioDto.setNumero_Pis(funcionarioModel.getNumero_Pis());
            funcionarioDto.setData_de_nascimento(funcionarioModel.getData_de_nascimento());
            logger.info("Funcionario de id: {} encontrado:{}", id,funcionarioModel);

            return funcionarioDto;

        }else{
                logger.warn("Funcionário de id:{} não encontrado", id);
            return null;
        }
    }
    }


