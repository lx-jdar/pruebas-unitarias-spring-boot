package com.api.rest.repository;

import com.api.rest.model.Empleado;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmpleadoRepositoryTests {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Test
    void testGuardarEmpleado() {
        // given - dado o condición previa o configuración
        Empleado empleado1 = Empleado.builder()
                .nombre("Pepe")
                .apellido("Lopez")
                .email("p12@gmail.com")
                .build();
        // when - acción o el comportamiento que vamos a probar
        Empleado empleadoGuardado = empleadoRepository.save(empleado1);

        // then - verificar la salida
        assertThat(empleadoGuardado).isNotNull();
        assertThat(empleadoGuardado.getId()).isGreaterThan(0);
    }
}
