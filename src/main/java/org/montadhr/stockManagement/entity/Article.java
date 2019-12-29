package org.montadhr.stockManagement.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ARTICLE")
public class Article implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ARTICLEID")
	private Long articleId;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="STOCK",columnDefinition="int default 0")
	private int stock;
	
	@Column(name="STOCK_MIN",columnDefinition="int default 10")
	private int stockMin;
	
	@Column(name="ARTICLE_UNIT_PRICE")
	private Double unitPrice;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="article")
	private List<Piece> pieces;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
	private List<Operation> operations;

	
	
	
	
	public int getStockMin() {
		return stockMin;
	}

	public void setStockMin(int stockMin) {
		this.stockMin = stockMin;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public List<Operation> getCommandes() {
		if(operations == null){
			operations = new ArrayList<>();
		}
		return operations;
	}

	public void setCommandes(List<Operation> commandes) {
		this.operations = commandes;
	}

	public List<Piece> getPieces() {
		if(pieces == null){
			pieces = new ArrayList<>();
		}
		return pieces;
	}

	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name ;
	}
	
	
	

}
