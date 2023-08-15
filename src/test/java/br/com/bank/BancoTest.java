package br.com.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import br.com.bank.model.Banco;
import br.com.bank.model.Conta;

public class BancoTest {

    Banco banco;

    public void alimentar_dados_do_banco_para_teste() {
        banco = new Banco("banco-de-teste");

        banco.adicionarConta(new Conta("123.456.789-00", 10000));
        banco.adicionarConta(new Conta("111.222.333-44", 10001));
        banco.adicionarConta(new Conta("555.666.777-88", 9999));

    }

    @Test
    public void deve_adicionar_conta() {
        banco = new Banco("banco-de-teste");

        banco.adicionarConta(new Conta("123.456.789-00"));

        assertEquals(1, banco.qtdContasCadastradas());

    }

    @Test
    public void deve_pesquisar_conta_do_cliente() {
        alimentar_dados_do_banco_para_teste();

        Optional<Conta> conta = banco.pesquisarContaDoCliente("123.456.789-00");

        assertTrue(conta.isPresent());
        assertEquals("123.456.789-00", conta.get().getCpf());

    }

    @Test
    public void nao_deve_recuperar_conta_se_cliente_nao_existir() {
        alimentar_dados_do_banco_para_teste();

        Optional<Conta> conta = banco.pesquisarContaDoCliente("000.000.000-00");

        assertFalse(conta.isPresent());
    }

    @Test
    public void deve_listar_contas_alta_renda() {
        alimentar_dados_do_banco_para_teste();

        List<Conta> contasAltaRenda = banco.listarContasAltaRenda();

        assertEquals(2, contasAltaRenda.size());

    }

}
