package br.uel.prova.control;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.uel.prova.model.Produto;
import br.uel.prova.model.ProdutoDAO;

@Named
@ViewScoped
public class ProdutoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Produto produto;
	private List<Produto> listaProdutos;

	// Gets e Sets

	public List<Produto> getListaProdutos() {
		return this.listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	// Inserir um novo Produto

	public void adicionar() {
		ProdutoDAO dao = new ProdutoDAO();

		dao.inserir(this.produto);

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto inserido com sucesso"));
	}

	// Buscar Produto

	public void buscarPorNome() {
		ProdutoDAO dao = new ProdutoDAO();
		this.listaProdutos = dao.buscarPorNome(this.produto.getNome());
	}

	// Remover Produto

	public void remover(Produto produto) {
		long idProduto = produto.getId();
		ProdutoDAO dao = new ProdutoDAO();
		dao.excluir(idProduto);
		listaProdutos.remove(produto);

	}

	// Alterar um Produto
	
	public void alterar(Produto produto) {
		ProdutoDAO dao = new ProdutoDAO();
		dao.alterar(produto);
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto alterado com sucesso"));		
	}

	@PostConstruct
	public void init() {
		produto = new Produto();
	}
}
