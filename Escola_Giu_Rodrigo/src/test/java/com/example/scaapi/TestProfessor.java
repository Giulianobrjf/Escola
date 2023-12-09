package com.example.scaapi;

import com.example.scaapi.model.entity.Curso;
import com.example.scaapi.model.entity.interf.IProfessor;
import com.example.scaapi.service.ProfessorService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class TestProfessor {
    @Test
    public void matriculaNulo (){
        try {
            ProfessorService DS = new ProfessorService();
            IProfessor Professormock =  createMock(IProfessor.class);
            expect(Professormock.getMatricula()).andReturn(null);
            replay(Professormock);
            DS.validar(Professormock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Matrícula inválida",e.getMessage());
        }
    }
    @Test
    public void matricula0 (){
        try {
            ProfessorService DS = new ProfessorService();
            IProfessor Professormock =  createMock(IProfessor.class);
            expect(Professormock.getMatricula()).andReturn(0);
            expect(Professormock.getMatricula()).andReturn(0);
            replay(Professormock);
            DS.validar(Professormock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Matrícula inválida",e.getMessage());
        }
    }
    @Test
    public void nomeNulo (){
        try {
            ProfessorService DS = new ProfessorService();
            IProfessor Professormock =  createMock(IProfessor.class);
            expect(Professormock.getMatricula()).andReturn(1);
            expect(Professormock.getMatricula()).andReturn(1);
            expect(Professormock.getNome()).andReturn(null);
            replay(Professormock);
            DS.validar(Professormock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Nome inválido",e.getMessage());
        }
    }
    @Test
    public void nomeVazio (){
        try {
            ProfessorService DS = new ProfessorService();
            IProfessor Professormock =  createMock(IProfessor.class);
            expect(Professormock.getMatricula()).andReturn(1);
            expect(Professormock.getMatricula()).andReturn(1);
            expect(Professormock.getNome()).andReturn("nome");
            expect(Professormock.getNome()).andReturn("");
            replay(Professormock);
            DS.validar(Professormock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Nome inválido",e.getMessage());
        }
    }
    @Test
    public void cursoNulo (){
        try {
            ProfessorService DS = new ProfessorService();
            IProfessor Professormock =  createMock(IProfessor.class);
            expect(Professormock.getMatricula()).andReturn(1);
            expect(Professormock.getMatricula()).andReturn(1);
            expect(Professormock.getNome()).andReturn("nome");
            expect(Professormock.getNome()).andReturn("nome");
            expect(Professormock.getCurso()).andReturn(null);
            replay(Professormock);
            DS.validar(Professormock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Curso inválido",e.getMessage());
        }
    }
    @Test
    public void CursoIDNulo (){
        try {
            ProfessorService DS = new ProfessorService();
            Curso C = new Curso();
            IProfessor Professormock = createMock(IProfessor.class);
            expect(Professormock.getMatricula()).andReturn(1);
            expect(Professormock.getMatricula()).andReturn(1);
            expect(Professormock.getNome()).andReturn("nome");
            expect(Professormock.getNome()).andReturn("nome");
            expect(Professormock.getCurso()).andReturn(C);
            expect(Professormock.getCurso()).andReturn(C);
            replay(Professormock);
            DS.validar(Professormock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Curso inválido",e.getMessage());
        }
    }
    @Test
    public void CursoID0 (){
        try {
            ProfessorService DS = new ProfessorService();
            Curso C = new Curso();
            C.setId(0L);
            IProfessor Professormock = createMock(IProfessor.class);
            expect(Professormock.getMatricula()).andReturn(1);
            expect(Professormock.getMatricula()).andReturn(1);
            expect(Professormock.getNome()).andReturn("nome");
            expect(Professormock.getNome()).andReturn("nome");
            expect(Professormock.getCurso()).andReturn(C);
            expect(Professormock.getCurso()).andReturn(C);
            expect(Professormock.getCurso()).andReturn(C);
            replay(Professormock);
            DS.validar(Professormock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Curso inválido",e.getMessage());
        }
    }
    @Test
    public void  ProfessorValido (){
        ProfessorService PS = new ProfessorService();
        Curso C = new Curso();
        C.setId(1L);
        IProfessor Professormock = createMock(IProfessor.class);
        expect(Professormock.getMatricula()).andReturn(1);
        expect(Professormock.getMatricula()).andReturn(1);
        expect(Professormock.getNome()).andReturn("nome");
        expect(Professormock.getNome()).andReturn("nome");
        expect(Professormock.getCurso()).andReturn(C);
        expect(Professormock.getCurso()).andReturn(C);
        expect(Professormock.getCurso()).andReturn(C);
        replay(Professormock);
        assertEquals("Professor valido", PS.validar(Professormock));
    }
}
