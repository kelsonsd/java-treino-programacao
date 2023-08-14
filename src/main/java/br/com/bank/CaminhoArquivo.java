package br.com.bank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CaminhoArquivo {

    private Path diretorio;

    private Path arquivo;

    private CaminhoArquivo(Path diretorio, Path arquivo) {
        super();
        this.diretorio = diretorio;
        this.arquivo = arquivo;
    }

    public Path getDiretorio() {
        return diretorio;
    }

    public Path getArquivo() {
        return arquivo;
    }

    public static CaminhoArquivo getInstance(Integer id) {
        String b = "/tmp/";
        String d, a = null;

        if(id == null) {
            throw new IllegalArgumentException();
        }

        if (id <= 1000) {
            d = b + "1";
        } else {
            int i = (id - 1) / 1000 + 1;
            d = b + i;
        }
        a = d + "/" + id;

        return new CaminhoArquivo(Paths.get(d), Paths.get(a));
    }

}
