package com.example.scaapi;

import com.example.scaapi.model.entity.Curso;
import com.example.scaapi.model.entity.interf.IAluno;
import com.example.scaapi.service.AlunoService;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.replay;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestAluno {

    @Test
    public void matriculaNulo (){
        try {
            AlunoService DS = new AlunoService();
            IAluno Alunomock =  createMock(IAluno.class);
            expect(Alunomock.getMatricula()).andReturn(null);
            replay(Alunomock);
            DS.validar(Alunomock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Matrícula inválida",e.getMessage());
        }
    }
    @Test
    public void matricula0 (){
        try {
            AlunoService DS = new AlunoService();
            IAluno Alunomock =  createMock(IAluno.class);
            expect(Alunomock.getMatricula()).andReturn(0);
            expect(Alunomock.getMatricula()).andReturn(0);
            replay(Alunomock);
            DS.validar(Alunomock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Matrícula inválida",e.getMessage());
        }
    }
    @Test
    public void nomeNulo (){
        try {
            AlunoService DS = new AlunoService();
            IAluno Alunomock =  createMock(IAluno.class);
            expect(Alunomock.getMatricula()).andReturn(1);
            expect(Alunomock.getMatricula()).andReturn(1);
            expect(Alunomock.getNome()).andReturn(null);
            replay(Alunomock);
            DS.validar(Alunomock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Nome inválido",e.getMessage());
        }
    }
    @Test
    public void nomeVazio (){
        try {
            AlunoService DS = new AlunoService();
            IAluno Alunomock =  createMock(IAluno.class);
            expect(Alunomock.getMatricula()).andReturn(1);
            expect(Alunomock.getMatricula()).andReturn(1);
            expect(Alunomock.getNome()).andReturn("nome");
            expect(Alunomock.getNome()).andReturn("");
            replay(Alunomock);
            DS.validar(Alunomock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Nome inválido",e.getMessage());
        }
    }
    @Test
    public void cursoNulo (){
        try {
            AlunoService DS = new AlunoService();
            IAluno Alunomock =  createMock(IAluno.class);
            expect(Alunomock.getMatricula()).andReturn(1);
            expect(Alunomock.getMatricula()).andReturn(1);
            expect(Alunomock.getNome()).andReturn("nome");
            expect(Alunomock.getNome()).andReturn("nome");
            expect(Alunomock.getCurso()).andReturn(null);
            replay(Alunomock);
            DS.validar(Alunomock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Curso inválido",e.getMessage());
        }
    }
    @Test
    public void CursoIDNulo (){
        try {
            AlunoService DS = new AlunoService();
            Curso C = new Curso();
            IAluno Alunomock = createMock(IAluno.class);
            expect(Alunomock.getMatricula()).andReturn(1);
            expect(Alunomock.getMatricula()).andReturn(1);
            expect(Alunomock.getNome()).andReturn("nome");
            expect(Alunomock.getNome()).andReturn("nome");
            expect(Alunomock.getCurso()).andReturn(C);
            expect(Alunomock.getCurso()).andReturn(C);
            replay(Alunomock);
            DS.validar(Alunomock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Curso inválido",e.getMessage());
        }
    }
    @Test
    public void CursoID0 (){
        try {
            AlunoService DS = new AlunoService();
            Curso C = new Curso();
            C.setId(0L);
            IAluno Alunomock = createMock(IAluno.class);
            expect(Alunomock.getMatricula()).andReturn(1);
            expect(Alunomock.getMatricula()).andReturn(1);
            expect(Alunomock.getNome()).andReturn("nome");
            expect(Alunomock.getNome()).andReturn("nome");
            expect(Alunomock.getCurso()).andReturn(C);
            expect(Alunomock.getCurso()).andReturn(C);
            expect(Alunomock.getCurso()).andReturn(C);
            replay(Alunomock);
            DS.validar(Alunomock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Curso inválido",e.getMessage());
        }
    }
    @Test
    public void  AlunoValido (){
        AlunoService PS = new AlunoService();
        Curso C = new Curso();
        C.setId(1L);
        IAluno Alunomock = createMock(IAluno.class);
        expect(Alunomock.getMatricula()).andReturn(1);
        expect(Alunomock.getMatricula()).andReturn(1);
        expect(Alunomock.getNome()).andReturn("nome");
        expect(Alunomock.getNome()).andReturn("nome");
        expect(Alunomock.getCurso()).andReturn(C);
        expect(Alunomock.getCurso()).andReturn(C);
        expect(Alunomock.getCurso()).andReturn(C);
        replay(Alunomock);
        assertEquals("Aluno valido", PS.validar(Alunomock));
    }
}
