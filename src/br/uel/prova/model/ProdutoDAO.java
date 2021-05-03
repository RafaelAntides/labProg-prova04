package br.uel.prova.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

	// Conexão

	private Connection connection;

	public ProdutoDAO() {

		this.connection = new ConnectionFactory().getConnection();

	}

	// Adicionar um Produto

	public void inserir(Produto produto) {

		String sql = "INSERT INTO produto (NOME, PRECO, QUANTIDADE) VALUES (?,?,?)";

		try {
			PreparedStatement prstate = connection.prepareStatement(sql);

			prstate.setString(1, produto.getNome());
			prstate.setDouble(2, produto.getPreco());
			prstate.setInt(3, produto.getQuantidade());

			prstate.execute();
			prstate.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}

	// Buscar um Produto

	public List<Produto> buscarPorNome(String nome) {
		List<Produto> produtos = new ArrayList<Produto>();

		String sql = "SELECT * FROM produto WHERE NOME LIKE UPPER(?)";

		try {

			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setString(1, "%" + nome + "%");
			ResultSet resultado = prstate.executeQuery();
			while (resultado.next()) {
				Produto produto = new Produto();

				produto.setId(resultado.getLong("ID"));
				produto.setNome(resultado.getString("NOME"));
				produto.setPreco(resultado.getDouble("PRECO"));
				produto.setQuantidade(resultado.getInt("QUANTIDADE"));

				produtos.add(produto);
			}

			resultado.close();
			prstate.close();

		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println(e);
				throw new RuntimeException(e);
			}
		}

		return produtos;
	}

	// Remover um Produto

	public void excluir(long id) {
		String sql = "DELETE FROM produto WHERE ID=?";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setLong(1, id);
			prstate.execute();
			prstate.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	// Alterar um Contato

	public void alterar(Produto produto) {
		String sql = "UPDATE produto SET NOME=?, PRECO=?, QUANTIDADE=? WHERE ID=?";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setString(1, produto.getNome());
			prstate.setDouble(2, produto.getPreco());
			prstate.setInt(3, produto.getQuantidade());
			prstate.setLong(4, produto.getId());
			
			prstate.execute();
			prstate.close();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
