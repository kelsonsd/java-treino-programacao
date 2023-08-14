package br.com.bank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CaminhoArquivo {

    private Path diretorio;
    private Path arquivo;

    private static final String DIRETORIO_BASE = "/tmp/";
    private static final int QTD_MAXIMA_ARQUIVOS_NO_DIRETORIO = 1000;

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
        String diretorio, arquivo = null;

        if(id == null) {
            throw new IllegalArgumentException();
        }

        if (id <= QTD_MAXIMA_ARQUIVOS_NO_DIRETORIO) {
            diretorio = DIRETORIO_BASE + "1";
        } else {
            int i = (id - 1) / QTD_MAXIMA_ARQUIVOS_NO_DIRETORIO + 1;
            diretorio = DIRETORIO_BASE + i;
        }
        arquivo = diretorio + "/" + id;

        return new CaminhoArquivo(Paths.get(diretorio), Paths.get(arquivo));
    }

}
