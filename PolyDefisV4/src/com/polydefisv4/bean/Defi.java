package com.polydefisv4.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.polydefisv4.bean.defis.Geolocalisation;
import com.polydefisv4.bean.defis.Photo;
import com.polydefisv4.bean.defis.QrCode;
import com.polydefisv4.bean.defis.Quizz;


public abstract class Defi implements Serializable {

	public static final int ETAT_EN_COURS_ACCEPTATION = 0;
	public static final int ETAT_ACCEPTE = 1;
	public static final int ETAT_TERMINE = 2;
	
	public static final int TYPE_PHOTO = 1;
	public static final int TYPE_GEOLOCALIATION = 2;
	public static final int TYPE_QRCODE = 3;
	public static final int TYPE_QUIZZ = 4;
	
	public static final String PORTEE_ALL = "Tous";
	public static final String PORTEE_PROMO = "Promo";
	public static final String PORTEE_FILLEUL = "Filleul";
	
	private int id;
	private String idEtudiant;
	private String intitule;
	private String description;
	private Date dateFin;
	private int etatAcceptation;
	private int nombrePoint;
	private String portee;
	
	public Defi (String idEtudiant, String intitule, String description, int etatAcceptation, String portee) {
		this.idEtudiant = idEtudiant;
		this.intitule = intitule;
		this.description = description;
		this.portee = portee;
		this.etatAcceptation = etatAcceptation;
	}

	public Defi(int id, String idEtudiant, String intitule, String description, Date dateFin, int etatAcceptation, int nombrePoint, String portee) {
		this.id = id;
		this.idEtudiant = idEtudiant;
		this.intitule = intitule;
		this.description = description;
		this.dateFin = dateFin;
		this.etatAcceptation = etatAcceptation;
		this.nombrePoint = nombrePoint;
		this.portee = portee;
	}
	
	public static ArrayList<Defi> getAllDefis() {
		ArrayList<Defi> listeDefis = new ArrayList<Defi>();
		
		listeDefis.add(new Geolocalisation(1, "E116143S", "Boire", "Description", new Date(2000, 10, 12), 1, 20, PORTEE_PROMO, 47.282475, -1.5164999999999509));
		listeDefis.add(new Photo(2, "E116143S", "Boire", "Description", new Date(2000, 10, 12), 1, 25, PORTEE_ALL, ""));
		listeDefis.add(Quizz.getQuizz());
		listeDefis.add(new QrCode(3, "E116143S", "Boire", "Description", new Date(2000, 10, 12), 1, 25, PORTEE_ALL, ""));

		listeDefis.add(new Geolocalisation(1, "E116143S", "Boire", "Description", new Date(2000, 10, 12), 1, 24, PORTEE_PROMO, 10, 20));
		listeDefis.add(new Photo(2, "E116143S", "Boire", "Description", new Date(2000, 10, 12), 1, 21, PORTEE_ALL, ""));
		listeDefis.add(Quizz.getQuizz());
		listeDefis.add(new QrCode(2, "E116143S", "Boire", "Description", new Date(2000, 10, 12), 1, 30, PORTEE_ALL, ""));

		listeDefis.add(new Geolocalisation(1, "E116143S", "Boire", "Description", new Date(2000, 10, 12), 1, 12, PORTEE_PROMO, 10, 20));
		listeDefis.add(new Photo(2, "E116143S", "Boire", "Description", new Date(2000, 10, 12), 1, 14, PORTEE_ALL, ""));
		listeDefis.add(Quizz.getQuizz());
		listeDefis.add(new QrCode(2, "E116143S", "Boire", "Description", new Date(2000, 10, 12), 1, 11, PORTEE_ALL, ""));

		listeDefis.add(new Geolocalisation(1, "E116143S", "Boire", "Description", new Date(2000, 10, 12), 1, 30, PORTEE_PROMO, 10, 20));
		listeDefis.add(new Photo(2, "E116143S", "Boire", "Description", new Date(2000, 10, 12), 1, 2, PORTEE_ALL, ""));
		listeDefis.add(Quizz.getQuizz());
		listeDefis.add(new QrCode(2, "E116143S", "Boire", "Description", new Date(2000, 10, 12), 1, 17, PORTEE_ALL, ""));
		
		return listeDefis;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getIdEtudiant() {
		return idEtudiant;
	}

	public void setIdEtudiant(String idEtudiant) {
		this.idEtudiant = idEtudiant;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public int getEtatAcceptation() {
		return etatAcceptation;
	}

	public void setEtatAcceptation(int etatAcceptation) {
		this.etatAcceptation = etatAcceptation;
	}

	public int getNombrePoint() {
		return nombrePoint;
	}

	public void setNombrePoint(int nombrePoint) {
		this.nombrePoint = nombrePoint;
	}

	public String getPortee() {
		return portee;
	}

	public void setPortee(String portee) {
		this.portee = portee;
	}
	
}
