package no.hvl.dat100.prosjekt.modell;
import java.util.ArrayList;
import java.util.Random;

import no.hvl.dat100.prosjekt.TODO;
public class KortUtils {

	/**
	 * Sorterer en samling. Rekkefølgen er bestemt av compareTo() i Kort-klassen.
	 * 
	 * @see Kort
	 * 
	 * @param samling
	 * 			samling av kort som skal sorteres. 
	 */
	
	public static void sorter(KortSamling samling) {
        Kort[] Collection = samling.getAllekort();
        Kort[] nyeTall = new Kort[Collection.length];
        for(int tallNr=0; tallNr<Collection.length; tallNr++){
            int minPos = 0;
            for(int posNr=1;posNr<Collection.length; posNr++){
                if((Collection[posNr] != null && Collection[minPos] != null) && 
                        (Collection[posNr].getVerdi() < Collection[minPos].getVerdi()) 
                        || Collection[posNr].getVerdi() == Collection[posNr].getVerdi() && 
                        Collection[posNr].getFarge().ordinal() < Collection[posNr].getFarge().ordinal())

                    minPos = posNr;
        }
        nyeTall[tallNr] = Collection[minPos];
        Collection[minPos] = new Kort(Kortfarge.Hjerter, 13);

        }
        if(nyeTall.length > 0) {
            samling.fjernAlle();
            for(int i = 0; i < nyeTall.length; i++) {
                samling.leggTil(nyeTall[i]);
            }
            
        }
	}  
	public static void stokk(KortSamling samling) {
        Kort[] Collection = samling.getAllekort();
        ArrayList<Kort> list = new ArrayList<Kort>();
        Random rnd = new Random();
        int randLoc = 0;
        for(int i = 0; i < Collection.length; i++) {
            list.add(Collection[randLoc]);
            Collection[randLoc] = Collection[i];
            Collection[i] = null;
        }
        for(int i = 0; i < Collection.length; i++) {
            if(Collection[i] == null)
                Collection[i] = list.get(rnd.nextInt(list.size()));
        } 
	}
}

