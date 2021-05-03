package br.uel.prova.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.uel.prova.model.Produto;

@Named
@SessionScoped
public class CarrinhoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<Produto> listaCarrinho;

	// Gets e Sets

	public List<Produto> getListaCarrinho() {
		return listaCarrinho;
	}

	public void setListaCarrinho(List<Produto> listaCarrinho) {
		this.listaCarrinho = listaCarrinho;
	}

	// Funçoes

	public void adicionarCarrinho(Produto produto) {

		Produto produtocarrinho = new Produto();

		produtocarrinho.setId(produto.getId());
		produtocarrinho.setNome(produto.getNome());
		produtocarrinho.setPreco(produto.getPreco());

		if (!listaCarrinho.contains(produto)) {
			produtocarrinho.setQuantidade(1);
			listaCarrinho.add(produtocarrinho);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto adicionado ao carrinho"));
		} else {
			for (int i = 0; i < listaCarrinho.size(); i++) {
				if (listaCarrinho.get(i).getId().equals(produto.getId())) {
					if ((listaCarrinho.get(i).getQuantidade() + 1) <= produto.getQuantidade()) {
						listaCarrinho.get(i).setQuantidade(listaCarrinho.get(i).getQuantidade() + 1);
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto alterado"));
					} else {
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage("Produto sem mais estoque"));
					}
				}
			}

		}
	}

	public void remover(Produto produto) {
		listaCarrinho.remove(produto);
	}

	@PostConstruct
	public void init() {
		listaCarrinho = new ArrayList<Produto>();
	}

}
