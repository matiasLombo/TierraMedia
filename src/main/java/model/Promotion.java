package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import persistence.AttractionDAO;
import persistence.commons.DAOFactory;

public abstract class Promotion implements Comparable<Promotion> {
	protected Integer id, discount;
	protected String name, typeProm;
	protected String type;
	protected double totalDuration, montoPromo;
	protected String attractionsId;
	protected ArrayList<Attraction> attractions;
	
	private Map<String, String> errors;

	public Promotion(Integer id, String name, String type, String typeProm, String attractionsId, double montoPromo) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.typeProm = typeProm;
		this.attractionsId = attractionsId;
		this.montoPromo = montoPromo;
		if(attractionsId != null) {
			this.attractions = getAttraction();
			this.totalDuration = getTotDuration();
		}
	}
	
	
	
	public Promotion(Integer id, String name, String type, String typeProm, Integer discount) {
		this.id = id;
		this.discount = discount;
		this.name = name;
		this.typeProm = typeProm;
		this.type = type;
	}



	public boolean isPORCENTUAL() {
		return this.typeProm.equals("PROMOCIONPORCENTUAL");
	}
	
	public boolean isABSOLUTA() {
		return this.typeProm.equals("PROMOCIONABSOLUTA");
	}
	
	public boolean isAxB() {
		return this.typeProm.equals("PROMOCIONAXB");
	}
	
	public Promotion(Integer id, String name, String type, String typeProm) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.typeProm = typeProm;
	}
	

	public ArrayList<Attraction> getAttraction(){
		AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
		ArrayList<Integer> ints = new ArrayList<>();
		ArrayList<Attraction> attraction = new ArrayList<>();
		if(this.attractionsId != ""){
			for (String s : this.attractionsId.split("\\|")) {
				ints.add(Integer.parseInt(s));
			}
			for (int i = 0; i < ints.size(); i++) {
				attraction.add(attractionDAO.find(ints.get(i)));
			}
		}
		return attraction;
	}


	public double getCost() {
		double costo = 0;
		for (int i = 0; i < attractions.size(); i++) {
			costo += attractions.get(i).getCost();
		}
		return costo;
	}


	public double getTotDuration() {
		double tiempo = 0;
		for (int i = 0; i < attractions.size(); i++) {
			tiempo += attractions.get(i).getDuration();
		}
		return tiempo;
	}


	public double getMontoPromo() {
		return montoPromo;
	}

	public void setMontoPromo(double montoPromo) {
		this.montoPromo = montoPromo;
	}

	public String getAttractions() {
		String aux = "";
		for (int i = 0; i < attractions.size(); i++) {
			aux += attractions.get(i).toString() + "\n";
		}
		return aux;
	}

	public String getAtraccionesItinenario() {
		String aux = "";
		for (int i = 0; i < attractions.size(); i++) {
			aux += attractions.get(i).getName() + "|";
		}
		return aux;
	}

	public boolean hasCapacity() {
		boolean aux = true;
		for (int i = 0; i < attractions.size(); i++) {
			if (attractions.get(i).getCapacity() <= 0) {
				aux = false;
				break;
			}
		}
		return aux;
	}
	
	public void host(int i) {
		for (Attraction attraction : attractions) {
			attraction.host(i);
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();
		if(typeProm.equals("PROMOCIONPORCENTUAL")) {
			if (attractionsId != null) {
				errors.put("attractionsId", "No se seleccionaron atracciones");
			}
		}
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

	public String getTypeProm() {
		return typeProm;
	}

	public void setTypeProm(String typeProm) {
		this.typeProm = typeProm;
	}

	public String getAttractionsId() {
		return attractionsId;
	}

	public void setAttractionsId(String attractionsId) {
		this.attractionsId = attractionsId;
		if(attractionsId != null) {
			this.attractions = getAttraction();
			this.totalDuration = getTotDuration();
		}
		
	}

	public void setTotalDuration(double totalDuration) {
		this.totalDuration = totalDuration;
	}

	public double getTotalDuration() {
		return totalDuration;
	}
	
	public boolean canHost(int i) {
		boolean aux = false;
		for (Attraction attraction : attractions) {
			if(!attraction.canHost(i)) {
				aux= false;
				break;
			}
			aux = attraction.canHost(i);
		}
		return aux;
	}


	public int compareTo(Promotion o) {
		int res = 0;
		if (this.getMontoPromo() < o.getMontoPromo()) {
			res = 1;
		} else {
			if (this.getMontoPromo() > o.getMontoPromo()) {
				res = -1;
			} else {
				if (this.getMontoPromo() == o.getMontoPromo()) {
					res = 0;
				} else {
					if (this.getTotalDuration() < o.getTotalDuration()) {
						res = 1;
					} else {
						if (this.getTotalDuration() > o.getTotalDuration()) {
							res = -1;
						} else {
							if (this.getTotalDuration() == o.getTotalDuration()) {
								res = 0;
							}
						}
					}
				}
			}
		}
		return res;
	}

	
	
	@Override
	public int hashCode() {
		return Objects.hash(name, type, typeProm);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promotion other = (Promotion) obj;
		return Objects.equals(name, other.name) && Objects.equals(type, other.type)
				&& Objects.equals(typeProm, other.typeProm);
	}

	@Override
	public String toString() {
		String toString = "Promocion que incluye las atracciones ";
		for (int i = 0; i < this.attractions.size() - 1; i++) {
			toString += this.attractions.get(i).getName() + ", ";
		}
		toString += this.attractions.get(this.attractions.size() - 1).getName() + ".";
		return toString;
	}
	
	
	

}