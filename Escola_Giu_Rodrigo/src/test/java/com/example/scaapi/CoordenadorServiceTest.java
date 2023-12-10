package com.example.scaapi;

import com.example.scaapi.exception.RegraNegocioException;
import com.example.scaapi.model.entity.Coordenador;
import com.example.scaapi.model.repository.CoordenadorRepository;
import com.example.scaapi.service.CoordenadorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CoordenadorServiceTest {

    @InjectMocks
    private CoordenadorService coordenadorService;

    @Mock
    private CoordenadorRepository coordenadorRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCoordenadores() {
        // Create some sample data
        Coordenador coordenador1 = new Coordenador(/* Set necessary properties */);
        Coordenador coordenador2 = new Coordenador(/* Set necessary properties */);
        List<Coordenador> mockCoordenadores = Arrays.asList(coordenador1, coordenador2);

        // Mock the repository behavior
        when(coordenadorRepository.findAll()).thenReturn(mockCoordenadores);

        // Call the service method
        List<Coordenador> result = coordenadorService.getCoordenadores();

        // Assertions
        assertEquals(2, result.size());
        assertEquals(coordenador1, result.get(0));
        assertEquals(coordenador2, result.get(1));
        // Add more assertions based on your specific requirements
    }

    @Test
    public void testGetCoordenadorById() {
        Long coordenadorId = 1L;
        Coordenador mockCoordenador = new Coordenador(/* Set necessary properties */);

        when(coordenadorRepository.findById(coordenadorId)).thenReturn(Optional.of(mockCoordenador));

        assertTrue(coordenadorService.getCoordenadorById(coordenadorId).isPresent());
    }

    @Test
    public void testSalvarValidCoordenador() {
        Coordenador coordenador = new Coordenador(LocalDateTime.now(), LocalDateTime.now());

        when(coordenadorRepository.save(coordenador)).thenReturn(coordenador);

        Coordenador savedCoordenador = coordenadorService.salvar(coordenador);
    }

    @Test
    public void testSalvarInvalidCoordenador() {
        Coordenador coordenador = new Coordenador();


        when(coordenadorRepository.save(coordenador)).thenReturn(coordenador);

        RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> {
            coordenadorService.salvar(coordenador);
        });

        assertEquals("Data de início inválida", exception.getMessage());
    }

    @Test
    public void testExcluir() {
        Coordenador coordenador = new Coordenador(/* Set necessary properties */);

        coordenador.setId(1L);
        assertDoesNotThrow(() -> coordenadorService.excluir(coordenador));
    }

    @Test
    public void testExcluirWithNullCoordenador() {
        // Test with a null Coordenador
        Coordenador coordenador = null;

        // Use assertThrows to verify that NullPointerException is thrown
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            coordenadorService.excluir(coordenador);
        });

        assertNotNull(exception.getMessage()); // Check if the exception message is not null
    }

    @Test
    public void testValidar() {
        Coordenador coordenador = new Coordenador(LocalDateTime.now(), LocalDateTime.now());

        // Test with valid coordinator
        String result = coordenadorService.validar(coordenador);
        assertEquals("Coordenador valido", result);

        // Test with invalid coordinator (null dataInicio)
        coordenador.setDataInicio(null);
        assertThrows(RegraNegocioException.class, () -> coordenadorService.validar(coordenador));
    }

    @Test
    public void testValidarCoordenadorWithNullDataInicio() {
        Coordenador coordenador = new Coordenador(LocalDateTime.now(), LocalDateTime.now());

        coordenador.setDataInicio(null);

        RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> {
            coordenadorService.validar(coordenador);
        });

        assertEquals("Data de início inválida", exception.getMessage());
    }

    @Test
    public void testValidarCoordenadorWithNullDataFim() {
        Coordenador coordenador = new Coordenador(LocalDateTime.now(), LocalDateTime.now());

        coordenador.setDataFim(null);

        RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> {
            coordenadorService.validar(coordenador);
        });

        assertEquals("Data de fim inválida", exception.getMessage());
    }
}
