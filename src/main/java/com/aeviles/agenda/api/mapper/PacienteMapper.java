package com.aeviles.agenda.api.mapper;

import com.aeviles.agenda.api.request.PacienteRequest;
import com.aeviles.agenda.api.response.PacienteResponse;
import com.aeviles.agenda.domain.entity.Paciente;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component // anotado como componente
@RequiredArgsConstructor  //senão houvesse essa anotação teríamos que gerar o construtor de todos os metodos
public class PacienteMapper {

    private final ModelMapper mapper;

    public Paciente toPaciente(PacienteRequest request) {
        return mapper.map(request, Paciente.class);//eu quero transforma essa request em Paciente.class
    }

    public PacienteResponse toPacienteResponse(Paciente paciente) {
        return mapper.map(paciente, PacienteResponse.class);
    }

    public List<PacienteResponse> toPacienteResponseList(List<Paciente> pacientes) {
        return pacientes.stream()//percorrer todos os paciente
                .map(this::toPacienteResponse)//this a propria classe paciente mapper,
                .collect(Collectors.toList());//depois que ele fizer acima ele coleta tudo para uma lista
                //o final é uma lista de paciented
    }
}
