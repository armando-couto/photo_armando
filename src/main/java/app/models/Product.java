package app.models;

import javax.persistence.Lob;

import org.postgresql.util.Base64;

@javax.persistence.Entity
public class Product extends Entity {

	@Lob
	private byte[] image;
	
	private String nome;
	
	private String contentType;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public String getImageByteArray() {
		return Base64.encodeBytes(this.image);
	}
	
}
