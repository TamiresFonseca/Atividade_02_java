package com.lanchenlayer.services;

import com.lanchenlayer.entities.Produto;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/*- Implementar na classe ProdutoService os métodos RemoverImagem(), atualizar()
 */
public class ProdutoService {
    private String caminhoDestino = "C:\\Users\\aluno\\LancheNLayer\\src\\main\\resources\\images\\";

    public static String getFileExtension(String filePath) {
        String fileName = new File(filePath).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "No extension" : fileName.substring(dotIndex + 1);
    }

    public boolean salvarImagem(Produto produto) {
        Path path = Paths.get(produto.getImagem());

        Path pastaDestino = Paths.get(String.format("%s%d.%s", caminhoDestino, produto.getId(), getFileExtension(produto.getImagem())));

        if (Files.exists(path)) {
            try {
                Files.copy(path, pastaDestino, StandardCopyOption.REPLACE_EXISTING);
                produto.setImagem(pastaDestino.getFileName().toString());
                return true;
            } catch (Exception ex)
            {
                return false;
            }
        }

        return false;
    }


    public boolean removerImagem(Produto produto) {
        Path path = Paths.get(caminhoDestino + produto.getImagem());
        try {
            if (Files.exists(path)) {
                Files.delete(path);
                return true;
            } else {
                System.out.println("Imagem não encontrada.");
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Erro ao tentar remover a imagem: " + ex.getMessage());
            return false;
        }
    }
}
