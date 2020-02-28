package hh.swd20.fin.Bookstore1.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long kategoriaid; 
	private String nimi; 
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kategoria")
	private List <Book> books; 
	
	public Category(String nimi) {
		super();
		this.nimi = nimi;
		
	}

	public Long getKategoriaid() {
		return kategoriaid;
	}
	public void setKategoriaid(Long kategoriaid) {
		this.kategoriaid = kategoriaid;
	}
	public String getNimi() {
		return nimi;
	}
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	
	@Override
	public String toString() {
		return "Kategoria [kategoriaid=" + kategoriaid + ", nimi=" + nimi + "]";
	}


	
}
