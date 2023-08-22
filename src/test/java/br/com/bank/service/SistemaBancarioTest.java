package br.com.bank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;
import br.com.exception.BancoNaoCadastradoException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SistemaBancarioTest {

   @InjectMocks
    private SistemaBancario sistemaBancario;

    @Mock
    private Bacen bacen;

//    @BeforeEach
//    public void setUp() {
//         sistemaBancario = new SistemaBancario(new BacenFake());
//    }

    @Test
    public void deve_registrar_banco_se_ele_possuir_autorizacao_do_bacen() {
        Banco bb = new Banco("bb");

        Mockito.doReturn(1l)
            .when(bacen)
            .cadastrarBanco(bb);

        long numeroDoRegistro = sistemaBancario.registrarBanco(bb);
        assertEquals(1, numeroDoRegistro);
        
    }

    @Test
    public void nao_deve_registrar_banco_se_houver_problema_de_comunicao_com_o_bacen() {
        Banco bb = new Banco("BB");
        
        Mockito.doThrow(BancoNaoCadastradoException.class)
            .when(bacen)
            .cadastrarBanco(bb);

        assertThrows(BancoNaoCadastradoException.class,
             () -> sistemaBancario.registrarBanco(bb));
        
    }

}