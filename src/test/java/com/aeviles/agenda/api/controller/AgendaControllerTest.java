package com.aeviles.agenda.api.controller;

import com.aeviles.agenda.api.response.AgendaResponse;
import com.aeviles.agenda.domain.entity.Agenda;
import com.aeviles.agenda.service.AgendaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;




import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = {AgendaController.class})
@AutoConfigureMockMvc
class AgendaControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private AgendaService agendaService;






}