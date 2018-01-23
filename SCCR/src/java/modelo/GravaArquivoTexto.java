package modelo;

import java.io.FileNotFoundException;
import java.util.Formatter;

public class GravaArquivoTexto {

    private Formatter saida;

    public void abreArquivo(String arquivo) throws FileNotFoundException {
        saida = new Formatter(arquivo);
    }

    public void gravaDados(String texto) {
        saida.format("%s", texto);
    }

    public void fechaArquivo() {
        saida.close();
    }
}
