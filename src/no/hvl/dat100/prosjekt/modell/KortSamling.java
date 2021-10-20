package no.hvl.dat100.prosjekt.modell;

import no.hvl.dat100.prosjekt.kontroll.dommer.Regler;

/**
 * Struktur for Ã¥ lagre ei samling kort. Kan lagre hele kortstokken. Det finnes
 * en konstant i klassen Regler som angir antall kort i hver av de 4 fargene. NÃ¥r
 * programmet er ferdig settes denne til 13, men under utvikling / testing kan
 * det vÃ¦re praktisk Ã¥ ha denne mindre.
 * 
 */
public class KortSamling {


	private final int MAKS_KORT = 4 * Regler.MAKS_KORT_FARGE;

	private Kort[] samling;
	private int antall;

	/**
	 * Oppretter en tom Kortsamling med plass til MAKS_KORT (hele kortstokken).
	 */
	public KortSamling() {
		samling = new Kort[MAKS_KORT];
		
	}

	/**
	 * Returnerer en tabell med kortene i samlinga. Tabellen trenger ikke v�re
	 * full. Kortene ligger sammenhengende fra starten av tabellen. Kan f�
	 * tilgang til antallet ved � bruke metoden getAntallKort(). Metoden er
	 * f�rst og fremst ment � brukes i testklasser. Om man trenger
	 * kortene utenfor, anbefales metoden getAlleKort().
	 * 
	 * @return tabell av kort.
	 */
	public Kort[] getSamling() {
		return samling;
	}
	
	/**
	 * Antall kort i samlingen.
	 * 
	 * @return antall kort i samlinga.
	 */
	public int getAntalKort() {
		int nullkort = 0;
		for(int i = 0; i < samling.length; i++){
			if(samling[i] == null)
				nullkort++;
		}
		return samling.length - nullkort;
	}
	
	/**
	 * Sjekker om samlinga er tom.
	 * 
	 * @return true om samlinga er tom, false ellers.
	 */
	public boolean erTom() {
		for(int i = 0; i < samling.length; i++) {
			if(samling[i] != null)
				return false;
		}
		return true;
	}

	/**
	 * Legg et kort til samlinga.
	 * 
	 * @param kort
	 *            er kortet som skal leggast til.
	 */
	public void leggTil(Kort kort){
		Kort[] temparr = new Kort[samling.length + 1];
		for(int i = 0; i < samling.length; i++){
			temparr[i] = samling[i];
		}
		temparr[temparr.length - 1] = kort;
		samling = temparr;
	}
	
	/**
	 * Legger alle korta (hele kortstokken) til samlinga. Korta vil v�re sortert
	 * slik at de normalt m� stokkes f�r bruk.
	 */
	public void leggTilAlle(){
		samling = new Kort[MAKS_KORT];
		int rotation = 0;
		for(int i = 0; i < Regler.MAKS_KORT_FARGE; i++){
			for(Kortfarge k : Kortfarge.values()){
				samling[rotation] = new Kort(k, i + 1);
				rotation++;
				}
			}
		}
	

	/**
	 * Fjerner alle korta fra samlinga slik at den blir tom.
	 */
	public void fjernAlle() {
		samling = new Kort[0];
	}
	
	/**
	 * Ser p� siste kortet i samlinga.
	 * 
	 * @return siste kortet i samlinga, men det blir ikke fjernet. Dersom samalinga er tom, returneres
	 *         null.
	 */
	public Kort seSiste() {
		if(samling == null || samling.length == 0)
			return null;
		int index = -1;
		for(int i = samling.length - 1; i > -1; i--) {
			if(samling[i] != null) {
				index = i;
				break;
			}	
		}
		if(index == -1)
			return null;
		Kort retme = samling[index];
		return retme;
	}

	/**
	 * Tek ut siste kort fra samlinga.
	 * 
	 * @return siste kortet i samlinga. Dersom samalinga er tom, returneres
	 *         null.
	 */
	public Kort taSiste() {
		if(samling == null || samling.length == 0)
			return null;
		int index = -1;
		for(int i = samling.length - 1; i > -1; i--) {
			if(samling[i] != null) {
				index = i;
				break;
			}
				
		}
		if(index == -1)
			return null;
		Kort retme = samling[index];
		samling[index] = null;
		return retme;
	}
	
	/**
	 * Unders�ker om et kort finst i samlinga.
	 * 
	 * @param kort.
	 * 
	 * @return true om kortet finst i samlinga, false ellers.
	 */
	public boolean har(Kort kort) {
		for(int i = 0; i < samling.length; i++){
			if(samling[i] != null)
				if(samling[i].equals(kort))
					return true;
		}
		return false; 
	}

	/**
	 * Fjernar et kort fr� samlinga. Dersom kortet ikke finnest i samlinga,
	 * skjer ingenting med samilingen
	 * 
	 * @param kort
	 *            kortet som skal fjernast. Dersom kortet ikke finnes, skjer
	 *            ingenting.
	 * @return true om kortet blev fjernet fra samlinga, false ellers.
	 */
	public boolean fjern(Kort kort) {
		if(samling.length == 0)
			return false;
		Kort[] tempList = new Kort[samling.length - 1];
		int MinusAmount = 0;
		boolean returnme = false;
		
		for(int i = 0; i < samling.length; i++){
			if(samling[i] != null && samling[i].equals(kort))
			{
				MinusAmount++;
				returnme = true;
			}
			else {
				if(MinusAmount != 0 || tempList.length > i)
					tempList[i - MinusAmount] = samling[i];
			}
				
		}
		if(returnme)
			samling = tempList;
		return returnme;
	}

	/**
	 * Gir kortene som en tabell av samme lengde som antall kort i samlingen
	 * 
	 * @return tabell av kort som er i samlingen, der kort skal ha samme rekkef�lge
	 *         som i kortsamlinga.
	 */
	public Kort[] getAllekort() {
		int amount = 0;
		for(int i = 0; i < samling.length; i++){
			if(samling[i] == null)
				amount++;
		}
		int retmePos = 0;
		Kort[] retme = new Kort[samling.length - amount];
		for(int i = 0; i < samling.length; i++){
			if(samling[i] != null){
				retme[retmePos] = samling[i];
				retmePos++;
			}
		}
		return retme;
	}
	
}