package br.edu.imepac.services;

import br.edu.imepac.dtos.PacienteCreateRequest;
import br.edu.imepac.dtos.PacienteDto;
import br.edu.imepac.models.PacienteModel;
import br.edu.imepac.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public PacienteDto save(PacienteCreateRequest pacienteCreateRequest){
        PacienteModel pacienteModel = new PacienteModel();
        pacienteModel.setNome(pacienteCreateRequest.getNome());
        pacienteModel.setNumero_RG(pacienteCreateRequest.getNumero_RG());
        pacienteModel.setOrgao_Emissor(pacienteCreateRequest.getOrgao_Emissor());
        pacienteModel.setNumero_CPF(pacienteCreateRequest.getNumero_CPF());
        pacienteModel.setEndereco(pacienteCreateRequest.getEndereco());
        pacienteModel.setNumero(pacienteCreateRequest.getNumero());
        pacienteModel.setComplemento(pacienteCreateRequest.getComplemento());
        pacienteModel.setBairro(pacienteCreateRequest.getBairro());
        pacienteModel.setCidade(pacienteCreateRequest.getCidade());
        pacienteModel.setEstado(pacienteCreateRequest.getEstado());
        pacienteModel.setTelefone(pacienteCreateRequest.getTelefone());
        pacienteModel.setCelular(pacienteCreateRequest.getCelular());
        pacienteModel.setData_de_nascimento(pacienteCreateRequest.getData_de_nascimento());
        pacienteModel.setSexo(pacienteCreateRequest.getSexo());
        pacienteModel.setTem_convenio(pacienteCreateRequest.getTem_convenio());
        pacienteModel.setConvenio(pacienteCreateRequest.getConvenio());
        pacienteModel.setSenha_acesso(pacienteCreateRequest.getSenha_acesso());

        PacienteModel savedPaciente = pacienteRepository.save(pacienteModel);

        PacienteDto pacienteDto = new PacienteDto();
        pacienteDto.setId(savedPaciente.getId());
        pacienteDto.setNome(savedPaciente.getNome());
        pacienteDto.setNumero_RG(savedPaciente.getNumero_RG());
        pacienteDto.setOrgao_Emissor(savedPaciente.getOrgao_Emissor());
        pacienteDto.setNumero_CPF(savedPaciente.getNumero_CPF());
        pacienteDto.setEndereco(savedPaciente.getEndereco());
        pacienteDto.setNumero(savedPaciente.getNumero());
        pacienteDto.setComplemento(savedPaciente.getComplemento());
        pacienteDto.setBairro(savedPaciente.getBairro());
        pacienteDto.setCidade(savedPaciente.getCidade());
        pacienteDto.setEstado(savedPaciente.getEstado());
        pacienteDto.setTelefone(savedPaciente.getTelefone());
        pacienteDto.setCelular(savedPaciente.getCelular());
        pacienteDto.setData_de_nascimento(savedPaciente.getData_de_nascimento());
        pacienteDto.setSexo(savedPaciente.getSexo());
        pacienteDto.setTem_convenio(savedPaciente.getTem_convenio());
        pacienteDto.setConvenio(savedPaciente.getConvenio());
        pacienteDto.setSenha_acesso(savedPaciente.getSenha_acesso());

        return pacienteDto;
    }

    public PacienteDto findById(Long id){
        Optional<PacienteModel> optionalMedicoDto = pacienteRepository.findById(id);
        if(optionalMedicoDto.isPresent()){
            PacienteModel pacienteModel = optionalMedicoDto.get();
            PacienteDto pacienteDto = new PacienteDto();
            pacienteDto.setId(pacienteModel.getId());
            pacienteDto.setNome(pacienteModel.getNome());
            pacienteDto.setNumero_RG(pacienteModel.getNumero_RG());
            pacienteDto.setOrgao_Emissor(pacienteModel.getOrgao_Emissor());
            pacienteDto.setNumero_CPF(pacienteModel.getNumero_CPF());
            pacienteDto.setEndereco(pacienteModel.getEndereco());
            pacienteDto.setNumero(pacienteModel.getNumero());
            pacienteDto.setComplemento(pacienteModel.getComplemento());
            pacienteDto.setBairro(pacienteModel.getBairro());
            pacienteDto.setCidade(pacienteModel.getCidade());
            pacienteDto.setEstado(pacienteModel.getEstado());
            pacienteDto.setTelefone(pacienteModel.getTelefone());
            pacienteDto.setCelular(pacienteModel.getCelular());
            pacienteDto.setData_de_nascimento(pacienteModel.getData_de_nascimento());
            pacienteDto.setSexo(pacienteModel.getSexo());
            pacienteDto.setTem_convenio(pacienteModel.getTem_convenio());
            pacienteDto.setConvenio(pacienteModel.getConvenio());
            pacienteDto.setSenha_acesso(pacienteModel.getSenha_acesso());
            return pacienteDto;
        }else{
            return null;
        }

    }

    public List<PacienteDto> findAll(){
        List<PacienteModel> listaModel = pacienteRepository.findAll();
        return listaModel.stream().map(paciente -> {
            PacienteDto pacienteDto = new PacienteDto();
            pacienteDto.setId(paciente.getId());
            pacienteDto.setNome(paciente.getNome());
            pacienteDto.setNumero_RG(paciente.getNumero_RG());
            pacienteDto.setOrgao_Emissor(paciente.getOrgao_Emissor());
            pacienteDto.setNumero_CPF(paciente.getNumero_CPF());
            pacienteDto.setEndereco(paciente.getEndereco());
            pacienteDto.setNumero(paciente.getNumero());
            pacienteDto.setComplemento(paciente.getComplemento());
            pacienteDto.setBairro(paciente.getBairro());
            pacienteDto.setCidade(paciente.getCidade());
            pacienteDto.setEstado(paciente.getEstado());
            pacienteDto.setTelefone(paciente.getTelefone());
            pacienteDto.setCelular(paciente.getCelular());
            pacienteDto.setData_de_nascimento(paciente.getData_de_nascimento());
            pacienteDto.setSexo(paciente.getSexo());
            pacienteDto.setTem_convenio(paciente.getTem_convenio());
            pacienteDto.setConvenio(paciente.getConvenio());
            pacienteDto.setSenha_acesso(paciente.getSenha_acesso());
            return pacienteDto;
        }).collect(Collectors.toList());

    }

    public void delete(Long id){
        pacienteRepository.deleteById(id);
    }

    public PacienteDto update (Long id, PacienteDto pacienteData){
        Optional<PacienteModel> optionalPacienteDto = pacienteRepository.findById(id);
        if(optionalPacienteDto.isPresent()){
            PacienteModel pacienteModel = optionalPacienteDto.get();
            pacienteModel.setNome(pacienteData.getNome());
            pacienteModel.setNumero_RG(pacienteData.getNumero_RG());
            pacienteModel.setOrgao_Emissor(pacienteData.getOrgao_Emissor());
            pacienteModel.setNumero_CPF(pacienteData.getNumero_CPF());
            pacienteModel.setEndereco(pacienteData.getEndereco());
            pacienteModel.setNumero(pacienteData.getNumero());
            pacienteModel.setComplemento(pacienteData.getComplemento());
            pacienteModel.setBairro(pacienteData.getBairro());
            pacienteModel.setCidade(pacienteData.getCidade());
            pacienteModel.setEstado(pacienteData.getEstado());
            pacienteModel.setTelefone(pacienteData.getTelefone());
            pacienteModel.setCelular(pacienteData.getCelular());
            pacienteModel.setData_de_nascimento(pacienteData.getData_de_nascimento());
            pacienteModel.setSexo(pacienteData.getSexo());
            pacienteModel.setTem_convenio(pacienteData.getTem_convenio());
            pacienteModel.setConvenio(pacienteData.getConvenio());
            pacienteModel.setSenha_acesso(pacienteData.getSenha_acesso());

            PacienteModel updatedModel = pacienteRepository.save(pacienteModel);

            PacienteDto pacienteDto = new PacienteDto();
            pacienteDto.setId(updatedModel.getId());
            pacienteDto.setNome(updatedModel.getNome());
            pacienteDto.setNumero_RG(updatedModel.getNumero_RG());
            pacienteDto.setOrgao_Emissor(updatedModel.getOrgao_Emissor());
            pacienteDto.setNumero_CPF(updatedModel.getNumero_CPF());
            pacienteDto.setEndereco(updatedModel.getEndereco());
            pacienteDto.setNumero(updatedModel.getNumero());
            pacienteDto.setComplemento(updatedModel.getComplemento());
            pacienteDto.setBairro(updatedModel.getBairro());
            pacienteDto.setCidade(updatedModel.getCidade());
            pacienteDto.setEstado(updatedModel.getEstado());
            pacienteDto.setTelefone(updatedModel.getTelefone());
            pacienteDto.setCelular(updatedModel.getCelular());
            pacienteDto.setData_de_nascimento(updatedModel.getData_de_nascimento());
            pacienteDto.setSexo(updatedModel.getSexo());
            pacienteDto.setTem_convenio(updatedModel.getTem_convenio());
            pacienteDto.setConvenio(updatedModel.getConvenio());
            pacienteDto.setSenha_acesso(updatedModel.getSenha_acesso());
            return pacienteDto;

        }else{
            return null;
        }

    }

}
