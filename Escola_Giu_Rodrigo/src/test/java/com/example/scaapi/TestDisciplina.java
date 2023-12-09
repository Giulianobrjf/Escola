package com.example.scaapi;

import com.example.scaapi.model.entity.Curso;
import com.example.scaapi.service.DisciplinaService;
import com.example.scaapi.model.entity.interf.IDisciplina;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class TestDisciplina {
    @Test
    public void nomeNulo (){
        try {
            DisciplinaService DS = new DisciplinaService();
            IDisciplina disciplinamock =  createMock(IDisciplina.class);
            expect(disciplinamock.getNome()).andReturn(null);
            replay(disciplinamock);
            DS.validar(disciplinamock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Nome inválido",e.getMessage());
        }
    }
    @Test
    public void nomeVazio (){
        try {
            DisciplinaService DS = new DisciplinaService();
            IDisciplina disciplinamock =  createMock(IDisciplina.class);
            expect(disciplinamock.getNome()).andReturn("nome");
            expect(disciplinamock.getNome()).andReturn("");
            replay(disciplinamock);
            DS.validar(disciplinamock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Nome inválido",e.getMessage());
        }
    }
    @Test
    public void cursoNulo (){
        try {
            DisciplinaService DS = new DisciplinaService();
            IDisciplina disciplinamock =  createMock(IDisciplina.class);
            expect(disciplinamock.getNome()).andReturn("nome");
            expect(disciplinamock.getNome()).andReturn("nome");
            expect(disciplinamock.getCurso()).andReturn(null);
            replay(disciplinamock);
            DS.validar(disciplinamock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Curso inválido",e.getMessage());
        }
    }
    @Test
    public void CursoIDNulo (){
        try {
            DisciplinaService DS = new DisciplinaService();
            Curso C = new Curso();
            IDisciplina disciplinamock = createMock(IDisciplina.class);
            expect(disciplinamock.getNome()).andReturn("nome");
            expect(disciplinamock.getNome()).andReturn("nome");
            expect(disciplinamock.getCurso()).andReturn(C);
            expect(disciplinamock.getCurso()).andReturn(C);
            replay(disciplinamock);
            DS.validar(disciplinamock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Curso inválido",e.getMessage());
        }
    }
    @Test
    public void CursoID0 (){
        try {
            DisciplinaService DS = new DisciplinaService();
            Curso C = new Curso();
            C.setId(0L);
            IDisciplina disciplinamock = createMock(IDisciplina.class);
            expect(disciplinamock.getNome()).andReturn("nome");
            expect(disciplinamock.getNome()).andReturn("nome");
            expect(disciplinamock.getCurso()).andReturn(C);
            expect(disciplinamock.getCurso()).andReturn(C);
            expect(disciplinamock.getCurso()).andReturn(C);
            replay(disciplinamock);
            DS.validar(disciplinamock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Curso inválido",e.getMessage());
        }
    }
    @Test
    public void CargaHorariaAcima (){
        try {
            DisciplinaService DS = new DisciplinaService();
            Curso C = new Curso();
            C.setId(1L);
            IDisciplina disciplinamock = createMock(IDisciplina.class);
            expect(disciplinamock.getNome()).andReturn("nome");
            expect(disciplinamock.getNome()).andReturn("nome");
            expect(disciplinamock.getCurso()).andReturn(C);
            expect(disciplinamock.getCurso()).andReturn(C);
            expect(disciplinamock.getCurso()).andReturn(C);
            expect(disciplinamock.getCargaHoraria()).andReturn(301);
            replay(disciplinamock);
            DS.validar(disciplinamock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Carga horaria acima do permitido",e.getMessage());
        }
    }
    @Test
    public void CargaHorariaAbaixo (){
        try {
            DisciplinaService DS = new DisciplinaService();
            Curso C = new Curso();
            C.setId(1L);
            IDisciplina disciplinamock = createMock(IDisciplina.class);
            expect(disciplinamock.getNome()).andReturn("nome");
            expect(disciplinamock.getNome()).andReturn("nome");
            expect(disciplinamock.getCurso()).andReturn(C);
            expect(disciplinamock.getCurso()).andReturn(C);
            expect(disciplinamock.getCurso()).andReturn(C);
            expect(disciplinamock.getCargaHoraria()).andReturn(14);
            expect(disciplinamock.getCargaHoraria()).andReturn(14);
            replay(disciplinamock);
            DS.validar(disciplinamock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Carga horaria abaixo do minimo",e.getMessage());
        }
    }
    @Test
    public void DisciplinaValida (){
        DisciplinaService DS = new DisciplinaService();
        Curso C = new Curso();
        C.setId(1L);
        IDisciplina disciplinamock = createMock(IDisciplina.class);
        expect(disciplinamock.getNome()).andReturn("nome");
        expect(disciplinamock.getNome()).andReturn("nome");
        expect(disciplinamock.getCurso()).andReturn(C);
        expect(disciplinamock.getCurso()).andReturn(C);
        expect(disciplinamock.getCurso()).andReturn(C);
        expect(disciplinamock.getCargaHoraria()).andReturn(15);
        expect(disciplinamock.getCargaHoraria()).andReturn(15);
        replay(disciplinamock);
        assertEquals("disciplina valida", DS.validar(disciplinamock));
    }

}
