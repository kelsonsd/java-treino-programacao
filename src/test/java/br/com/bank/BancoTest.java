package br.com.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.bank.model.Banco;
import br.com.bank.model.Conta;

public class BancoTest {

    protected Banco banco;

    public void alimentar_dados_do_banco_para_teste() {
        this.banco = new Banco("banco-teste");
        
        Conta conta = new Conta("123.456.789-00");
        conta.setSaldo(10000);

        this.banco.adicionarConta(conta);

        conta = new Conta("111.222.333-44");
        conta.setSaldo(10001);

        this.banco.adicionarConta(conta);

        conta = new Conta("555.666.777-88");
        conta.setSaldo(9999);

        this.banco.adicionarConta(conta);

    }

    @Test
    public void deve_adicionar_conta() {
        this.banco = new Banco("banco-teste-adicionar-conta");

        Conta conta = new Conta("123.456.789-00");
        this.banco.adicionarConta(conta);

        assertEquals(1, banco.qtdContasCadastradas());

    }
        
    @Test
    public void deve_pesquisar_conta_do_cliente() {
        alimentar_dados_do_banco_para_teste();

        assertEquals("123.456.789-00", banco.pesquisarContaDoCliente("123.456.789-00").getCpf());
        assertEquals("111.222.333-44", banco.pesquisarContaDoCliente("111.222.333-44").getCpf());
        assertEquals("555.666.777-88", banco.pesquisarContaDoCliente("555.666.777-88").getCpf());

    }

    @Test
    public void deve_listar_contas_alta_renda() {
        alimentar_dados_do_banco_para_teste();

        assertEquals(2, this.banco.listarContasAltaRenda().size());

    }
    
}
