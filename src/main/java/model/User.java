package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	@Embedded
	private Location location;
	private String email;
	@Column(unique = true)
	private String identificador; // Es unico y es el nombre de usuario
	private String tipo;
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Constructor
	 */
	User() {
	}

	/**
	 * Constructor
	 * 
	 * @param nombre
	 * @param location
	 * @param email
	 * @param identificador
	 * @param tipo
	 */
	public User(String nombre, String location, String email, String identificador, String tipo) {
		setNombre(nombre);
		setLocation(obtenerLocalizacion(location));
		setEmail(email);
		setIdentificador(identificador);
		setTipo(tipo);
		generarPassword();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identificador == null) ? 0 : identificador.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (identificador == null) {
			if (other.identificador != null)
				return false;
		} else if (!identificador.equals(other.identificador))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nombre=" + nombre + ", localizacion=" + location.toString() + ", email=" + email + ", identificador=" + identificador
				+ ", tipo=" + tipo + "]";
	}

	/**
	 * Método para generar una contraseña aleatoria a cada usuario
	 */
	private void generarPassword() {
		StringBuffer pass = new StringBuffer();
		int low = 65;
		int top = 90;
		for (int i = 0; i < 9; i++) {
			int numAleatorio = (int) Math.floor(Math.random() * (top - low) + low);
			pass.append((char) numAleatorio);
		}
		for (int i = 0; i < 3; i++) {
			int numAleatorio = (int) Math.floor(Math.random() * (9 - 0) + 0);
			pass.append(numAleatorio);
		}
		setPassword(pass.toString());
	}

	/**
	 * Método para obtener la localizacion del usuario a partir del string que se
	 * pasa por parámetro
	 * 
	 * @param localizacion
	 * @return localizacion del usuario
	 */
	private Location obtenerLocalizacion(String localizacion) {
		if (localizacion.equals("")) {
			Location location = new Location(0, 0);
			location.setExist(false);
			return location;
		}
		String[] trozos = localizacion.split(";");
		double latitud = Double.parseDouble(trozos[0]);
		double longitud = Double.parseDouble(trozos[1]);
		return new Location(latitud, longitud);
	}

}
