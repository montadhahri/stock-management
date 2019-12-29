package org.montadhr.stockManagement.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "PIECE")
public class Piece implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NUMPIECE")
	private int numPiece;

	@Column(name = "DESCRIPTION")
	private String description;

	@Transient
	private String partie;

	@ManyToOne(fetch = FetchType.EAGER)
	private Article article;

	@Column(name = "NBREPIECE")
	private int nmbrePiece;

	@ManyToOne(fetch = FetchType.LAZY)
	private SousEquipemet sousEquipement;

	public String getPartie() {
		partie = article.getName();
		return partie;
	}

	public void setPartie(String partie) {
		this.partie = partie;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumPiece() {
		return numPiece;
	}

	public void setNumPiece(int numPiece) {
		this.numPiece = numPiece;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNmbrePiece() {
		return nmbrePiece;
	}

	public void setNmbrePiece(int nmbrePiece) {
		this.nmbrePiece = nmbrePiece;
	}

	public SousEquipemet getSousEquipement() {
		return sousEquipement;
	}

	public void setSousEquipement(SousEquipemet sousEquipement) {
		this.sousEquipement = sousEquipement;
	}
}
