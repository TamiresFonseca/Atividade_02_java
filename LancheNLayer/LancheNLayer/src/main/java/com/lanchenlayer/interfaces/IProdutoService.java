package com.lanchenlayer.interfaces;
import com.lanchenlayer.entities.Produto;

public interface IProdutoService {

        boolean salvarImagem(Produto produto);
        boolean removerImagem(Produto produto);

}
