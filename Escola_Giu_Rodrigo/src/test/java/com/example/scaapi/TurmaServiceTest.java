package com.example.scaapi;

import com.example.scaapi.exception.RegraNegocioException;
import com.example.scaapi.model.entity.Aluno;
import com.example.scaapi.model.entity.Disciplina;
import com.example.scaapi.model.entity.Professor;
import com.example.scaapi.model.entity.Turma;
import com.example.scaapi.model.repository.TurmaRepository;
import com.example.scaapi.service.TurmaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TurmaServiceTest {

    @InjectMocks
    private TurmaService turmaService;

    @Mock
    private TurmaRepository turmaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private void assertExceptionThrowsAndMessage(Turma turma, String message) {
        RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> {
            turmaService.validar(turma);
        });

        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testGetTurmas() {
        // Create some sample data
        Turma turma1 = new Turma(/* Set necessary properties */);
        Turma turma2 = new Turma(/* Set necessary properties */);
        List<Turma> mockTurmas = Arrays.asList(turma1, turma2);

        // Mock the repository behavior
        when(turmaRepository.findAll()).thenReturn(mockTurmas);

        // Call the service method
        List<Turma> result = turmaService.getTurmas();

        // Assertions
        assertEquals(2, result.size());
        assertEquals(turma1, result.get(0));
        assertEquals(turma2, result.get(1));
        // Add more assertions based on your specific requirements
    }

    @Test
    public void testGetTurmaById() {
        Long turmaId = 1L;
        Turma mockTurma = new Turma(/* Set necessary properties */);

        when(turmaRepository.findById(turmaId)).thenReturn(Optional.of(mockTurma));

        assertTrue(turmaService.getTurmaById(turmaId).isPresent());
    }

    @Test
    public void testSalvar() {
        Turma turma = this.createValidTurma();

        when(turmaRepository.save(turma)).thenReturn(turma);

        Turma savedTurma = turmaService.salvar(turma);

        // Add assertions based on your requirements
    }

    @Test
    public void testSalvarInvalidTurma() {
        Turma turma = new Turma();

        when(turmaRepository.save(turma)).thenReturn(turma);

        this.assertExceptionThrowsAndMessage(turma, "Ano inválido");
    }

    @Test
    public void testExcluir() {
        Turma turma = new Turma();

        turma.setId(1L);
        assertDoesNotThrow(() -> turmaService.excluir(turma));
    }

    @Test
    public void testExcluirWithNullTurma() {
        Turma turma = null;

        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            turmaService.excluir(turma);
        });

        assertNotNull(exception.getMessage());
    }

    @Test
    public void testValidar() {
        Turma turma = createValidTurma(); // Create a valid ITurma object for testing

        String result = turmaService.validar(turma);
        assertEquals("turma valida", result);

        turma.setAno(null);
        assertThrows(RegraNegocioException.class, () -> turmaService.validar(turma));
    }

    @Test
    public void testValidarWithNullAno() {
        Turma turma = createValidTurma();
        turma.setAno(null);

        this.assertExceptionThrowsAndMessage(turma, "Ano inválido");
    }

    @Test
    public void testValidarWith0Ano() {
        Turma turma = createValidTurma();
        turma.setAno(0);

        this.assertExceptionThrowsAndMessage(turma, "Ano inválido");
    }

    @Test
    public void testValidarWithNullSemestre() {
        Turma turma = createValidTurma();
        turma.setSemestre(null);

        this.assertExceptionThrowsAndMessage(turma, "Semestre inválido");
    }

    @Test
    public void testValidarWith0Semestre() {
        Turma turma = createValidTurma();
        turma.setSemestre(0);

        this.assertExceptionThrowsAndMessage(turma, "Semestre inválido");
    }

    @Test
    public void testValidarWithNullNome() {
        Turma turma = this.createValidTurma();

        turma.setNome(null);
        this.assertExceptionThrowsAndMessage(turma, "Nome inválido");
    }

    @Test
    public void testValidarWithEmptyNome() {
        Turma turma = this.createValidTurma();

        turma.setNome("");
        this.assertExceptionThrowsAndMessage(turma, "Nome inválido");
    }

    @Test
    public void testValidarTurmaWithNullDisciplina() {
        Turma turma = this.createValidTurma();

        turma.setDisciplina(null);
        this.assertExceptionThrowsAndMessage(turma, "Disciplina inválida");
    }

    @Test
    public void testValidarTurmaWithNullDisciplinaId() {
        Turma turma = this.createValidTurma();

        turma.getDisciplina().setId(null);
        this.assertExceptionThrowsAndMessage(turma, "Disciplina inválida");
    }

    @Test
    public void testValidarTurmaWith0DisciplinaId() {
        Turma turma = this.createValidTurma();

        turma.getDisciplina().setId(0L);
        this.assertExceptionThrowsAndMessage(turma, "Disciplina inválida");
    }

    @Test
    public void testValidarTurmaWithNullProfessor() {
        Turma turma = this.createValidTurma();

        turma.setProfessor(null);
        this.assertExceptionThrowsAndMessage(turma, "turma deve haver um professor");
    }

    @Test
    public void testValidarTurmaWithNullProfessorId() {
        Turma turma = this.createValidTurma();

        turma.getProfessor().setId(null);
        this.assertExceptionThrowsAndMessage(turma, "turma deve haver um professor");
    }

    @Test
    public void testValidarTurmaWith0ProfessorId() {
        Turma turma = this.createValidTurma();

        turma.getProfessor().setId(0L);
        this.assertExceptionThrowsAndMessage(turma, "turma deve haver um professor");
    }

    @Test
    public void testValidarTurmaWith15Alunos() {
        Turma turma = this.createValidTurma();

        for (int i = 0; i <= 15; i++) {
            Aluno aluno = new Aluno();
            aluno.setId((long) i);
            turma.getAlunos().add(aluno);
        }

        this.assertExceptionThrowsAndMessage(turma, "maximo de alunos na turma atingido");
    }

    private Turma createValidTurma() {
        Turma turma = new Turma();

        turma.setAno(2021);
        turma.setSemestre(1);
        turma.setNome("Turma 1");

        Disciplina disciplina = new Disciplina();
        disciplina.setId(1L);

        turma.setDisciplina(disciplina);

        Professor professor = new Professor();
        professor.setId(1L);

        turma.setProfessor(professor);

        return turma;
    }
}
