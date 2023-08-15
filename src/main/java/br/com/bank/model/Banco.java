package br.com.bank.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Banco {

    private String nome;
    private static final int SALDO_CONTA_ALTA_RENDA = 10000;

    public Banco(String nome) {
        this.nome = nome;
    }

    //private List<Conta> contas = new ArrayList<>();
    private Map<String, Conta> contas = new HashMap<>();

    public void adicionarConta(Conta conta) {
        if(conta != null) {
            contas.put(conta.getCpf(), conta);
        }
    }

    // public Optional<Conta> pesquisarContaDoCliente(String cpf) {
    //     Conta c = null;
    //     for (int i = 0; i < contas.size(); i++) {
    //         if(contas.get(i).getCpf().equals(cpf)) {
    //             c = contas.get(i);
    //         }
    //     }
    //     return Optional.ofNullable(c);
    // }

    public Optional<Conta> pesquisarContaDoCliente(String cpf) {
        return Optional.ofNullable(contas.get(cpf));
    }

    public List<Conta> listarContasAltaRenda() {
        return filtrarContas(c -> c.getSaldo() >= SALDO_CONTA_ALTA_RENDA);
    }

    private List<Conta> filtrarContas(Predicate<Conta> filtro) {
        return contas.values().stream().filter(filtro).collect(Collectors.toList());
    }

    public int qtdContasCadastradas() {
        return contas.size();
    }
}
