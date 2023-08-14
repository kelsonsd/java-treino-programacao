package br.com.bank.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Banco {

    private String nome;

    public Banco(String nome) {
        this.nome = nome;
    }

    private List<Conta> contas = new ArrayList<>();

    public void adicionarConta(Conta conta) {
        if(conta != null) {
            contas.add(conta);
        }
    }

    public Conta pesquisarContaDoCliente(String cpf) {
        Conta c = null;
        
        if(cpf != null) {
            c = listarContasPorCpf(cpf);
        }
        return c;
    }

    public List<Conta> listarContasAltaRenda() {
        return filtrarContas(c -> c.getSaldo() >= 10000);
    }

    public Conta listarContasPorCpf(String cpf) {
        return filtrarContas(c -> c.getCpf().equals(cpf)).get(0);
    }

    private List<Conta> filtrarContas(Predicate<Conta> filtro) {
        return contas.stream().filter(filtro).collect(Collectors.toList());
    }

    public int qtdContasCadastradas() {
        return contas.size();
    }
}
