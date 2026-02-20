package com.api.rest.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.api.rest.exception.ResourceNotFoundException;
import com.api.rest.model.Empleado;
import com.api.rest.repository.EmpleadoRepository;
import com.api.rest.service.impl.EmpleadoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmpleadoServiceTests {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoServiceImpl empleadoService;

    private Empleado empleado;

    @BeforeEach
    void setup() {
        System.out.println("===== Hola Mundo!!! =====");
        empleado = Empleado.builder()
                .nombre("Christian")
                .apellido("Ramirez")
                .email("c1@gmail.com")
                .build();
    }

    @DisplayName("Test para Guardar un empleado")
    @Test
    void testGuardarEmpleado() {
        //given
        given(empleadoRepository.findByEmail(empleado.getEmail()))
                .willReturn(Optional.empty());
        given(empleadoRepository.save(empleado)).willReturn(empleado);

        //when
        Empleado empleadoGuardado = empleadoService.saveEmpleado(empleado);

        //then
        assertThat(empleadoGuardado).isNotNull();
    }

    @DisplayName("Test para Guardar un empleado con Throw Exception")
    @Test
    void testGuardarEmpleadoConThrowException() {
        //given
        given(empleadoRepository.findByEmail(empleado.getEmail()))
                .willReturn(Optional.of(empleado));

        //when
        assertThrows(ResourceNotFoundException.class, () -> {
            empleadoService.saveEmpleado(empleado);
        });

        //then
        verify(empleadoRepository, never()).save(any(Empleado.class));
    }

    @DisplayName("Test para listar a los empleados")
    @Test
    void testListarEmpleados() {
        //given
        Empleado empleado1 = Empleado.builder()
                .id(1L)
                .nombre("Julen")
                .apellido("j2@gmail.com")
                .build();

        //when
        given(empleadoRepository.findAll()).willReturn(List.of(empleado, empleado1));

        //then
	
    }
}
