package org.montadhr.stockManagement.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "OPERATION")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_OPR", discriminatorType = DiscriminatorType.STRING, length = 2)
public class Operation implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="OPERATION_ID")
	private Long operationId;
	
	@Column(name="DATE_OPERATION")
	private Date date;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Article article;
	
	@Column(name="OPERATION_QUANTITE")
	private int quantite;
	
	@Transient
	private String articleName;
	
	@Transient
	private String qteEntre;
	
	@Transient
	private String qteSortie;
	
	
	
	public String getQteEntre() {
		if(this instanceof Commande){
			qteEntre = String.valueOf(quantite);
		}else{
			qteEntre = "--";
		}
		return qteEntre;
	}

	public String getQteSortie() {
		if(this instanceof Consommation){
			qteSortie = String.valueOf(quantite);
		}else{
			qteSortie = "--";
		}
		return qteSortie;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getArticleName() {
		articleName = article.getName();
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Long getOperationId() {
		return operationId;
	}

	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}
	
}
