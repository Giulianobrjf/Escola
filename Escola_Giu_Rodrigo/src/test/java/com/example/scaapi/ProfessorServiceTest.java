package com.example.scaapi;

import com.example.scaapi.exception.RegraNegocioException;
import com.example.scaapi.model.entity.Curso;
import com.example.scaapi.model.entity.Professor;
import com.example.scaapi.model.entity.Turma;
import com.example.scaapi.model.repository.ProfessorRepository;
import com.example.scaapi.service.ProfessorService;
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
import static org.mockito.Mockito.when;

public class ProfessorServiceTest {

    @InjectMocks
    private ProfessorService professorService;

    @Mock
    private ProfessorRepository professorRepository;

    @Mock
    private TurmaService turmaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private void assertExceptionThrowsAndMessage(Professor professor, String message) {
        RegraNegocioException exception = assertThrows(RegraNegocioException.class, () -> {
            professorService.validar(professor);
        });

        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testGetProfessores() {
        // Create some sample data
        Professor professor1 = new Professor(/* Set necessary properties */);
        Professor professor2 = new Professor(/* Set necessary properties */);
        List<Professor> mockProfessores = Arrays.asList(professor1, professor2);

        // Mock the repository behavior
        when(professorRepository.findAll()).thenReturn(mockProfessores);

        // Call the service method
        List<Professor> result = professorService.getProfessores();

        // Assertions
        assertEquals(2, result.size());
        assertEquals(professor1, result.get(0));
        assertEquals(professor2, result.get(1));
        // Add more assertions based on your specific requirements
    }

    @Test
    public void testGetProfessorById() {
        Long professorId = 1L;
        Professor mockProfessor = new Professor(/* Set necessary properties */);

        when(professorRepository.findById(professorId)).thenReturn(Optional.of(mockProfessor));

        assertTrue(professorService.getProfessorById(professorId).isPresent());
    }

    @Test
    public void testSalvarValidProfessor() {
        Professor professor = this.createValidProfessor();

        when(professorRepository.save(professor)).thenReturn(professor);

        Professor savedProfessor = professorService.salvar(professor);
    }

    @Test
    public void testSalvarInvalidProfessor() {
        Professor professor = new Professor();

        when(professorRepository.save(professor)).thenReturn(professor);

        this.assertExceptionThrowsAndMessage(professor, "Matrícula inválida");
    }

    @Test
    public void testExcluir() {
        Professor professor = new Professor();
        Turma turma1 = new Turma();
        Turma turma2 = new Turma();
        professor.setTurmas(Arrays.asList(turma1, turma2));

        when(turmaService.salvar(turma1)).thenReturn(turma1);
        when(turmaService.salvar(turma2)).thenReturn(turma2);

        professor.setId(1L);
        assertDoesNotThrow(() -> professorService.excluir(professor));
    }

    @Test
    public void testExcluirWithNullProfessor() {
        Professor professor = null;

        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            professorService.excluir(professor);
        });

        assertNotNull(exception.getMessage());
    }

    @Test
    public void testValidar() {
        Professor professor = createValidProfessor(); // Create a valid IProfessor object for testing

        // Test with valid professor
        String result = professorService.validar(professor);
        assertEquals("Professor valido", result);

        // Test with invalid professor (null matricula)
        professor.setMatricula(null);
        assertThrows(RegraNegocioException.class, () -> professorService.validar(professor));
    }

    @Test
    public void testValidarProfessorWithNullMatricula() {
        Professor professor = this.createValidProfessor();

        professor.setMatricula(null);
        this.assertExceptionThrowsAndMessage(professor, "Matrícula inválida");
    }

    @Test
    public void testValidarProfessorWith0Matricula() {
        Professor professor = this.createValidProfessor();

        professor.setMatricula(0);
        this.assertExceptionThrowsAndMessage(professor, "Matrícula inválida");
    }

    @Test
    public void testValidarProfessorWithNullNome() {
        Professor professor = this.createValidProfessor();

        professor.setNome(null);
        this.assertExceptionThrowsAndMessage(professor, "Nome inválido");
    }

    @Test
    public void testValidarProfessorWithEmptyNome() {
        Professor professor = this.createValidProfessor();

        professor.setNome("");
        this.assertExceptionThrowsAndMessage(professor, "Nome inválido");
    }

    @Test
    public void testValidarProfessorWithNullCurso() {
        Professor professor = this.createValidProfessor();

        professor.setCurso(null);
        this.assertExceptionThrowsAndMessage(professor, "Curso inválido");
    }

    @Test
    public void testValidarProfessorWithNullCursoId() {
        Professor professor = this.createValidProfessor();

        professor.getCurso().setId(null);
        this.assertExceptionThrowsAndMessage(professor, "Curso inválido");
    }

    @Test
    public void testValidarProfessorWith0CursoId() {
        Professor professor = this.createValidProfessor();

        professor.getCurso().setId(0L);
        this.assertExceptionThrowsAndMessage(professor, "Curso inválido");
    }

    private Professor createValidProfessor() {
        Professor professor = new Professor();

        professor.setMatricula(1);
        professor.setNome("Aluno 1");

        Curso curso = new Curso();
        curso.setId(1L);
        professor.setCurso(curso);

        return professor;
    }
}
