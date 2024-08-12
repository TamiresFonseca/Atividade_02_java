package com.lanchenlayer.repositories;

import com.lanchenlayer.entities.Produto;

import java.util.ArrayList;

public class ProdutoRepository {
    private ArrayList<Produto> produtos = new ArrayList<Produto>();

    public void adicionar(Produto produto) {
        produtos.add(produto);
    }

    public void remover(int id) {
        produtos.removeIf(produto -> produto.getId() == id);
    }

    public Produto buscarPorId(int id) {
        return produtos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
    public ArrayList<Produto> buscarTodos() {
        return produtos;
    }

    /*- Implementar o método atualizar() em ProdutoRepository para atualizar
as informações do produto como descrição, preco e imagem*/

    public boolean atualizar(int id, String descricao, float preco, String imagem) {
        Produto produto = buscarPorId(id);
        if (produto != null) {
            produto.setDescricao(descricao);
            produto.setValor(preco);
            produto.setImagem(imagem);
            return true;
        } else {
            System.out.println("Produto não encontrado, atualização falhou.");
            return false;
        }
    }
}
