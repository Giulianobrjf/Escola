package com.example.scaapi;

import com.example.scaapi.model.entity.Aluno;
import com.example.scaapi.model.entity.Disciplina;
import com.example.scaapi.model.entity.Professor;
import com.example.scaapi.service.TurmaService;
import com.example.scaapi.model.entity.interf.ITurma;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
@SpringBootTest
public class TestTurma {

    @Test
    public void anoNulo (){
        try {
            TurmaService TS = new TurmaService();
            ITurma turmamock =createMock(ITurma.class);
            expect(turmamock.getAno()).andReturn(null);
            replay(turmamock);
            TS.validar(turmamock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Ano inválido",e.getMessage());
        }
    }
    @Test
    public void anoZero (){
        TurmaService TS = new TurmaService();
        ITurma turmamock =createMock(ITurma.class);
        EasyMock.expect(turmamock.getAno()).andReturn(0);
        EasyMock.expect(turmamock.getAno()).andReturn(0);
        replay(turmamock);
        try {
            TS.validar(turmamock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Ano inválido",e.getMessage());
        }
    }
    @Test
    public void semestreNulo (){
        try {
            TurmaService TS = new TurmaService();
            ITurma turmamock =createMock(ITurma.class);
            expect(turmamock.getAno()).andReturn(1);
            expect(turmamock.getAno()).andReturn(1);
            expect(turmamock.getSemestre()).andReturn(null);
            replay(turmamock);
            TS.validar(turmamock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Semestre inválido",e.getMessage());
        }
    }
    @Test
    public void semestreZero (){
        try {
            TurmaService TS = new TurmaService();
            ITurma turmamock =createMock(ITurma.class);
            expect(turmamock.getAno()).andReturn(1);
            expect(turmamock.getAno()).andReturn(1);
            expect(turmamock.getSemestre()).andReturn(0);
            expect(turmamock.getSemestre()).andReturn(0);
            replay(turmamock);
            TS.validar(turmamock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Semestre inválido",e.getMessage());
        }
    }
    @Test
    public void nomeNulo (){
        try {
            TurmaService TS = new TurmaService();
            ITurma turmamock =  createMock(ITurma.class);
            expect(turmamock.getAno()).andReturn(1);
            expect(turmamock.getAno()).andReturn(1);
            expect(turmamock.getSemestre()).andReturn(1);
            expect(turmamock.getSemestre()).andReturn(1);
            expect(turmamock.getNome()).andReturn(null);
            replay(turmamock);
            TS.validar(turmamock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Nome inválido",e.getMessage());
        }
    }
    @Test
    public void nomeVazio (){
        try {
            TurmaService TS = new TurmaService();
            ITurma turmamock =  createMock(ITurma.class);
            expect(turmamock.getAno()).andReturn(1);
            expect(turmamock.getAno()).andReturn(1);
            expect(turmamock.getSemestre()).andReturn(1);
            expect(turmamock.getSemestre()).andReturn(1);
            expect(turmamock.getNome()).andReturn("nome");
            expect(turmamock.getNome()).andReturn("");
            replay(turmamock);
            TS.validar(turmamock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Nome inválido",e.getMessage());
        }
    }
    @Test
    public void DisciplinaNulo (){
        try {
            TurmaService TS = new TurmaService();
            ITurma turmamock =createMock(ITurma.class);
            expect(turmamock.getAno()).andReturn(1);
            expect(turmamock.getAno()).andReturn(1);
            expect(turmamock.getSemestre()).andReturn(1);
            expect(turmamock.getSemestre()).andReturn(1);
            expect(turmamock.getNome()).andReturn("nome");
            expect(turmamock.getNome()).andReturn("nome");
            expect(turmamock.getDisciplina()).andReturn(null);
            replay(turmamock);
            TS.validar(turmamock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Disciplina inválida",e.getMessage());
        }
    }
    @Test
    public void DisciplinaIDNulo (){
        try {
            TurmaService TS = new TurmaService();
            Disciplina D = new Disciplina();
            ITurma turmamock =createMock(ITurma.class);
            expect(turmamock.getAno()).andReturn(1);
            expect(turmamock.getAno()).andReturn(1);
            expect(turmamock.getSemestre()).andReturn(1);
            expect(turmamock.getSemestre()).andReturn(1);
            expect(turmamock.getNome()).andReturn("nome");
            expect(turmamock.getNome()).andReturn("nome");
            expect(turmamock.getDisciplina()).andReturn(D);
            expect(turmamock.getDisciplina()).andReturn(D);
            replay(turmamock);
            TS.validar(turmamock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Disciplina inválida",e.getMessage());
        }
    }
    @Test
    public void DisciplinaID0 (){
        try {
            TurmaService TS = new TurmaService();
            Disciplina D = new Disciplina();
            D.setId(0L);
            ITurma turmamock =createMock(ITurma.class);
            expect(turmamock.getAno()).andReturn(1);
            expect(turmamock.getAno()).andReturn(1);
            expect(turmamock.getSemestre()).andReturn(1);
            expect(turmamock.getSemestre()).andReturn(1);
            expect(turmamock.getNome()).andReturn("nome");
            expect(turmamock.getNome()).andReturn("nome");
            expect(turmamock.getDisciplina()).andReturn(D);
            expect(turmamock.getDisciplina()).andReturn(D);
            expect(turmamock.getDisciplina()).andReturn(D);
            replay(turmamock);
            TS.validar(turmamock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Disciplina inválida",e.getMessage());
        }
    }
    @Test
    public void turmaCheia (){
        try {
            TurmaService TS = new TurmaService();
            Disciplina D = new Disciplina();
            D.setId(1L);
            Aluno a = new Aluno();
            List<Aluno> lista = new ArrayList<>();
            for(int i=0; i<16; i++)
                lista.add(a);
            ITurma turmamock =createMock(ITurma.class);
            expect(turmamock.getAno()).andReturn(1);
            expect(turmamock.getAno()).andReturn(1);
            expect(turmamock.getSemestre()).andReturn(1);
            expect(turmamock.getSemestre()).andReturn(1);
            expect(turmamock.getNome()).andReturn("nome");
            expect(turmamock.getNome()).andReturn("nome");
            expect(turmamock.getDisciplina()).andReturn(D);
            expect(turmamock.getDisciplina()).andReturn(D);
            expect(turmamock.getDisciplina()).andReturn(D);
            expect(turmamock.getAlunos()).andReturn(lista);
            replay(turmamock);
            TS.validar(turmamock);
            fail();
        }catch(RuntimeException e){
            assertEquals("maximo de alunos na turma atingido",e.getMessage());
        }
    }
    @Test
    public void semProfessor (){
        try {
            TurmaService TS = new TurmaService();
            Disciplina D = new Disciplina();
            D.setId(1L);
            Aluno a = new Aluno();
            List<Aluno> lista = new ArrayList<>();
            for(int i=0; i<15; i++)
                lista.add(a);
            ITurma turmamock =createMock(ITurma.class);
            expect(turmamock.getAno()).andReturn(1);
            expect(turmamock.getAno()).andReturn(1);
            expect(turmamock.getSemestre()).andReturn(1);
            expect(turmamock.getSemestre()).andReturn(1);
            expect(turmamock.getNome()).andReturn("nome");
            expect(turmamock.getNome()).andReturn("nome");
            expect(turmamock.getDisciplina()).andReturn(D);
            expect(turmamock.getDisciplina()).andReturn(D);
            expect(turmamock.getDisciplina()).andReturn(D);
            expect(turmamock.getAlunos()).andReturn(lista);
            expect(turmamock.getProfessor()).andReturn(null);
            replay(turmamock);
            TS.validar(turmamock);
            fail();
        }catch(RuntimeException e){
            assertEquals("turma deve haver um professor",e.getMessage());
        }
    }
    @Test
    public void ProfessorSemID (){
        try {
            TurmaService TS = new TurmaService();
            Professor P = new Professor();
            Disciplina D = new Disciplina();
            D.setId(1L);
            Aluno a = new Aluno();
            List<Aluno> lista = new ArrayList<>();
            for(int i=0; i<15; i++)
                lista.add(a);
            ITurma turmamock =createMock(ITurma.class);
            expect(turmamock.getAno()).andReturn(1);
            expect(turmamock.getAno()).andReturn(1);
            expect(turmamock.getSemestre()).andReturn(1);
            expect(turmamock.getSemestre()).andReturn(1);
            expect(turmamock.getNome()).andReturn("nome");
            expect(turmamock.getNome()).andReturn("nome");
            expect(turmamock.getDisciplina()).andReturn(D);
            expect(turmamock.getDisciplina()).andReturn(D);
            expect(turmamock.getDisciplina()).andReturn(D);
            expect(turmamock.getAlunos()).andReturn(lista);
            expect(turmamock.getProfessor()).andReturn(P);
            expect(turmamock.getProfessor()).andReturn(P);
            replay(turmamock);
            TS.validar(turmamock);
            fail();
        }catch(RuntimeException e){
            assertEquals("turma deve haver um professor",e.getMessage());
        }
    }
    @Test
    public void ProfessorID0 (){
        try {
            TurmaService TS = new TurmaService();
            Disciplina D = new Disciplina();
            D.setId(1L);
            Professor P = new Professor();
            P.setId(0L);
            Aluno a = new Aluno();
            List<Aluno> lista = new ArrayList<>();
            for(int i=0; i<15; i++)
                lista.add(a);
            ITurma turmamock =createMock(ITurma.class);
            expect(turmamock.getAno()).andReturn(1);
            expect(turmamock.getAno()).andReturn(1);
            expect(turmamock.getSemestre()).andReturn(1);
            expect(turmamock.getSemestre()).andReturn(1);
            expect(turmamock.getNome()).andReturn("nome");
            expect(turmamock.getNome()).andReturn("nome");
            expect(turmamock.getDisciplina()).andReturn(D);
            expect(turmamock.getDisciplina()).andReturn(D);
            expect(turmamock.getDisciplina()).andReturn(D);
            expect(turmamock.getAlunos()).andReturn(lista);
            expect(turmamock.getProfessor()).andReturn(P);
            expect(turmamock.getProfessor()).andReturn(P);
            expect(turmamock.getProfessor()).andReturn(P);
            replay(turmamock);
            TS.validar(turmamock);
            fail();
        }catch(RuntimeException e){
            assertEquals("turma deve haver um professor",e.getMessage());
        }
    }
    @Test
    public void turmavalida (){
            TurmaService TS = new TurmaService();
            Disciplina D = new Disciplina();
            D.setId(1L);
            Professor P = new Professor();
            P.setId(1L);
            Aluno a = new Aluno();
            List<Aluno> lista = new ArrayList<>();
            for(int i=0; i<15; i++)
                lista.add(a);
            ITurma turmamock =createMock(ITurma.class);
            expect(turmamock.getAno()).andReturn(1);
            expect(turmamock.getAno()).andReturn(1);
            expect(turmamock.getSemestre()).andReturn(1);
            expect(turmamock.getSemestre()).andReturn(1);
            expect(turmamock.getNome()).andReturn("nome");
            expect(turmamock.getNome()).andReturn("nome");
            expect(turmamock.getDisciplina()).andReturn(D);
            expect(turmamock.getDisciplina()).andReturn(D);
            expect(turmamock.getDisciplina()).andReturn(D);
            expect(turmamock.getAlunos()).andReturn(lista);
            expect(turmamock.getProfessor()).andReturn(P);
            expect(turmamock.getProfessor()).andReturn(P);
            expect(turmamock.getProfessor()).andReturn(P);
            replay(turmamock);
            assertEquals("turma valida", TS.validar(turmamock));
    }

}
