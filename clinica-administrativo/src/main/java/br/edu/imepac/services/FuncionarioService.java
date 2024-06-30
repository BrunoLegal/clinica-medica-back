package br.edu.imepac.services;

import br.edu.imepac.dtos.FuncionarioCreateRequest;
import br.edu.imepac.dtos.FuncionarioDto;
import br.edu.imepac.models.FuncionarioModel;
import br.edu.imepac.repositories.FuncionarioRepository;
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

    private ModelMapper modelMapper;

    public void delete (Long id){
        funcionarioRepository.deleteById(id);
    }

    public List<FuncionarioDto> findAll(){
        List<FuncionarioModel> funcionarioModels = funcionarioRepository.findAll();
        return funcionarioModels.stream().map(funcionarios -> {
            FuncionarioDto funcionarioDto = new FuncionarioDto();

            funcionarioDto.setNome_Funcionario(funcionarios.getNome_Funcionario());
            funcionarioDto.setNumero_Rg(funcionarios.getNumero_Rg());
            funcionarioDto.setOrgao_Emissor(funcionarios.getOrgao_Emissor());
            funcionarioDto.setNumero_Cpf(funcionarios.getNumero_Cpf());
            funcionarioDto.setEndereco(funcionarios.getEndereco());
            funcionarioDto.setNumero(funcionarios.getNumero());
            funcionarioDto.setComplemento(funcionarios.getComplemento());
            funcionarioDto.setBairro(funcionarios.getBairro());
            funcionarioDto.setCidade(funcionarios.getCidade());
            funcionarioDto.setEstado(funcionarios.getEstado());
            funcionarioDto.setTelefone(funcionarios.getTelefone());
            funcionarioDto.setCelular(funcionarios.getCelular());
            funcionarioDto.setNumero_Ctps(funcionarios.getNumero_Ctps());
            funcionarioDto.setNumero_Pis(funcionarios.getNumero_Pis());
            funcionarioDto.setData(funcionarios.getData());

            return funcionarioDto;
        }).collect(Collectors.toList());
    }

    public FuncionarioDto update (Long id, FuncionarioDto funcionarioData){
        Optional<FuncionarioModel> optionalFuncionarioDto = funcionarioRepository.findById(id);
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
            funcionarioModel.setData(funcionarioData.getData());

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
            funcionarioDto.setData(updatedModel.getData());
            return funcionarioDto;

        }else {
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
        funcionarioModel.setData(funcionarioCreateRequest.getData());

        FuncionarioModel savedFuncionario = funcionarioRepository.save(funcionarioModel);

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
        funcionarioDto.setData(savedFuncionario.getData());

        return funcionarioDto;
    }


    public FuncionarioDto findById(Long id){
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
            funcionarioDto.setData(funcionarioModel.getData());

            return funcionarioDto;

        }else{
            return null;
        }
    }
    }


