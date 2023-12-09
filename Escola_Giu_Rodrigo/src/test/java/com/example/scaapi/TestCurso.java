package com.example.scaapi;

import com.example.scaapi.model.entity.Curso;
import com.example.scaapi.model.entity.interf.ICurso;
import com.example.scaapi.model.entity.interf.IDisciplina;
import com.example.scaapi.service.CursoService;
import com.example.scaapi.service.DisciplinaService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
@SpringBootTest
public class TestCurso {

    @Test
    public void nomeNulo (){
        try {
            CursoService CS = new CursoService();
            ICurso Cursomock =  createMock(ICurso.class);
            expect(Cursomock.getNome()).andReturn(null);
            replay(Cursomock);
            CS.validar(Cursomock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Nome inválido",e.getMessage());
        }
    }
    @Test
    public void nomeVazio (){
        try {
            CursoService CS = new CursoService();
            ICurso Cursomock =  createMock(ICurso.class);
            expect(Cursomock.getNome()).andReturn("nome");
            expect(Cursomock.getNome()).andReturn("");
            replay(Cursomock);
            CS.validar(Cursomock);
            fail();
        }catch(RuntimeException e){
            assertEquals("Nome inválido",e.getMessage());
        }
    }
    @Test
    public void  CursoValida (){
        CursoService CS = new CursoService();
        ICurso Cursomock =  createMock(ICurso.class);
        expect(Cursomock.getNome()).andReturn("nome");
        expect(Cursomock.getNome()).andReturn("nome");
        replay(Cursomock);

        assertEquals("Curso valida", CS.validar(Cursomock));
    }

}
