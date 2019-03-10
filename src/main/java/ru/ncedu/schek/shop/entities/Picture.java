package ru.ncedu.schek.shop.entities;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PICTURES")
public class Picture {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PICTURE_ID", nullable = false)
    private Long id;
	private String color = "black";
	@ManyToOne
    @JoinColumn(name = "MODEL_ID", nullable = false)
    private Model model;
	@Lob
	@Column(name = "PICTURE", nullable = true)
    private byte[] bytes;

	public Picture() {}
	
	public Picture(Model model, String color) {
		this.color = color;
		this.model = model;
	}
	
    public String encodeImage() throws IOException, URISyntaxException {
        String file = Base64.getEncoder().encodeToString(bytes);
        return file;
    }

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) throws InterruptedException {
		TimeUnit.SECONDS.sleep(1);
		System.out.println(bytes);
		this.bytes = bytes;
	}

}
