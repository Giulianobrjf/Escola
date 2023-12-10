package com.example.scaapi;

import com.example.scaapi.exception.RegraNegocioException;
import com.example.scaapi.model.entity.Aluno;
import com.example.scaapi.model.entity.Curso;
import com.example.scaapi.model.repository.AlunoRepository;
import com.example.scaapi.service.AlunoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AlunoServiceTest {

    @InjectMocks
    private AlunoService alunoService;

    @Mock
    private AlunoRepository alunoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private void assertExceptionThrowsAndMessage(Aluno aluno, String message) {
        RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> {
            alunoService.validar(aluno);
        });

        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testGetAlunos() {
        Aluno aluno1 = new Aluno();
        Aluno aluno2 = new Aluno();
        List<Aluno> mockAlunos = Arrays.asList(aluno1, aluno2);

        when(alunoRepository.findAll()).thenReturn(mockAlunos);

        List<Aluno> result = alunoService.getAlunos();

        assertEquals(2, result.size());
        assertEquals(aluno1, result.get(0));
        assertEquals(aluno2, result.get(1));
    }

    @Test
    public void testGetAlunoById() {
        Long alunoId = 1L;
        Aluno mockAluno = new Aluno();
        mockAluno.setId(alunoId);

        Mockito.when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(mockAluno));

        assertTrue(alunoService.getAlunoById(alunoId).isPresent());
    }

    @Test
    public void testSalvarValidAluno() {
        Aluno aluno = this.createValidAluno();

        Mockito.when(alunoRepository.save(aluno)).thenReturn(aluno);

        Aluno savedAluno = alunoService.salvar(aluno);
    }

    @Test
    public void testSalvarInvalidAluno() {
        Aluno aluno = new Aluno();

        Mockito.when(alunoRepository.save(aluno)).thenReturn(aluno);

        this.assertExceptionThrowsAndMessage(aluno, "Matrícula inválida");
    }

    @Test
    public void testExcluir() {
        Aluno aluno = new Aluno();

        aluno.setId(1L);
        assertDoesNotThrow(() -> alunoService.excluir(aluno));
    }

    @Test
    public void testExcluirWithNullAluno() {
        // Test with a null Aluno
        Aluno aluno = null;

        // Use assertThrows to verify that NullPointerException is thrown
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            alunoService.excluir(aluno);
        });

        // Optionally, you can add more assertions based on your specific requirements
        assertNotNull(exception.getMessage()); // Check if the exception message is not null
    }

    @Test
    public void testValidar() {
        Aluno aluno = this.createValidAluno();

        // Test with a valid student
        String result = alunoService.validar(aluno);
        assertTrue(result.equals("Aluno valido"));
    }

    @Test
    public void testValidarAlunoWithNullMatricula() {
        Aluno aluno = this.createValidAluno();

        aluno.setMatricula(null);
        this.assertExceptionThrowsAndMessage(aluno, "Matrícula inválida");
    }

    @Test
    public void testValidarAlunoWith0Matricula() {
        Aluno aluno = this.createValidAluno();

        aluno.setMatricula(0);
        this.assertExceptionThrowsAndMessage(aluno, "Matrícula inválida");
    }

    @Test
    public void testValidarAlunoWithNullNome() {
        Aluno aluno = this.createValidAluno();

        aluno.setNome(null);
        this.assertExceptionThrowsAndMessage(aluno, "Nome inválido");
    }

    @Test
    public void testValidarAlunoWithEmptyNome() {
        Aluno aluno = this.createValidAluno();

        aluno.setNome("");
        this.assertExceptionThrowsAndMessage(aluno, "Nome inválido");
    }

    @Test
    public void testValidarAlunoWithNullCurso() {
        Aluno aluno = this.createValidAluno();

        aluno.setCurso(null);
        this.assertExceptionThrowsAndMessage(aluno, "Curso inválido");
    }

    @Test
    public void testValidarAlunoWithNullCursoId() {
        Aluno aluno = this.createValidAluno();

        aluno.getCurso().setId(null);
        this.assertExceptionThrowsAndMessage(aluno, "Curso inválido");
    }

    @Test
    public void testValidarAlunoWith0CursoId() {
        Aluno aluno = this.createValidAluno();

        aluno.getCurso().setId(0L);
        this.assertExceptionThrowsAndMessage(aluno, "Curso inválido");
    }

    private Aluno createValidAluno() {
        // Implement this method to create a valid Aluno object for testing
        Aluno aluno = new Aluno();

        aluno.setMatricula(1);
        aluno.setNome("Aluno 1");

        Curso curso = new Curso();
        curso.setId(1L);
        aluno.setCurso(curso);

        return aluno;
    }

}
