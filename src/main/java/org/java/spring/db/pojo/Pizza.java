package org.java.spring.db.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pizza")
public class Pizza {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 60, unique = true, nullable = false)
	private String name;

	@Column(columnDefinition = "TEXT")
	private String descrizione;

	@Column()
	private String foto;

	@Column(nullable = false)
	private double prezzo;

	public Pizza() {
	}

	public Pizza(String name, String descrizione, String foto, double prezzo) {
	
		setNome(name);
		setDescrizione(descrizione);
		setFoto(foto);
		setPrezzo(prezzo);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setNome(String nome) {
		this.name = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Pizza: " + this.name + "\n" + this.descrizione + "\n" + this.prezzo;
	}

}
