package br.edu.imepac;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//TODO: Fazer o programa rodar

@SpringBootApplication
public class ClinicaAgendamento {
    public static void main(String[] args) {
        SpringApplication.run(ClinicaAgendamento.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
