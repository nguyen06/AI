import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class CSPGraphZebra extends CSP{

	static Set<Object> varColors = new HashSet<Object>(
		Arrays.asList(new String[]{"Blue","Green","Ivory","Red","Yellow"}));
	static Set<Object> varDrinks = new HashSet<Object>(
		Arrays.asList(new String[]{"Coffee","Milk","Orange Juice","Tea","Water"}));

	static Set<Object> varNationalitys = new HashSet<Object>(
		Arrays.asList(new String[]{"Englishman","Japanese","Norwegian","Spaniard","Ukrainian"}));

	static Set<Object> varPets = new HashSet<Object>(
		Arrays.asList(new String[]{"Dog","Fox","Horse","Snail","Zebra"}));

	static Set<Object> varCigarettes = new HashSet<Object>(
		Arrays.asList(new String[]{"Chesterfield","Kools","Lucky-Strike","Old-Gold","Parliament"}));
	

	public boolean isGood(Object X, Object Y, Object x, Object y){
		
		//if X is not mentioned in by the constraints, just return true
		if(!C.containsKey(X))
			return true;

		//check to see if there is an arc between X and Y
        //if there isn't an arc, then no constraint, i.e. it is good
        if(!C.get(X).contains(Y)) 
			return true;

		//1. The Englishman live in the red house
		if(X.equals("Englishman") && Y.equals("Red") && !x.equals(y)) return false;

		//2.The Spaniard owns a dog
		if(X.equals("Spaniard") && Y.equals("Dog") && !x.equals(y)) return false;

		//3.Coffee is drunk in the green house
		if(X.equals("Coffee") && Y.equals("Green") && !x.equals(y)) return false;

		//4.The Ukrainian drinks tea
		if(X.equals("Ukrainian") && Y.equals("Tea") && !x.equals(y)) return false;

		//5.The Green house is directly to the right of the ivory house
		if(X.equals("Green") && Y.equals("Ivory") && (Math.abs((int)x-(int)y) != 1)) return false;

		//6.The Old-Gold smoker owns snail
		if(X.equals("Old-Gold") && Y.equals("Snail") && !x.equals(y)) return false;

		//7.Kools are being smoked in the yellow house
		if(X.equals("Kools") && Y.equals("Yellow") && !x.equals(y)) return false;

		//8. Milk is drunk in the middle house
		//9.The Snorwegian lives in the first house on the left
		//don't need 8, 9

		//10.The Chesterfield smoker lives next to the fox owner
		if(X.equals("Chesterfield") && Y.equals("Fox") && (Math.abs((int)x-(int)y) != 1)) return false;

		
		//11.Kools are smoked in the house next to the house where the horse is kept
		if(X.equals("Kools") && Y.equals("Horse") && (Math.abs((int)x-(int)y) != 1)) return false;


		//12.The Lucky-Strike smoker drinks orange juice
		if(X.equals("Lucky-Strike") && Y.equals("Orange Juice") && !x.equals(y)) return false;

		//13.The Japanese smokes Parliament
		if(X.equals("Japanese") && Y.equals("Parliament") && !x.equals(y)) return false;


		//14.The Norwegian lives next to the blue house
		if(X.equals("Norwegian") && Y.equals("Blue") && (Math.abs((int)x-(int)y) != 1)) return false;


		//Uniqueness constraints
		if(varColors.contains(X) && varColors.contains(Y) && !X.equals(Y) && x.equals(y)) return false;
		if(varDrinks.contains(X) && varDrinks.contains(Y) && !X.equals(Y) && x.equals(y)) return false;
		if(varNationalitys.contains(X) && varNationalitys.contains(Y) && !X.equals(Y) && x.equals(y)) return false;
		if(varPets.contains(X) && varPets.contains(Y) && !X.equals(Y) && x.equals(y)) return false;
		if(varCigarettes.contains(X) && varCigarettes.contains(Y) && !X.equals(Y) && x.equals(y)) return false;
		return true;
	}
	public static void main(String[] args) throws Exception{

		CSPGraphZebra csp = new CSPGraphZebra();

		Integer[] domain = {1,2,3,4,5};

		for(Object X: varColors){
			csp.addDomain(X,domain);
		}

		for(Object X: varDrinks)
			csp.addDomain(X,domain);

		for(Object X: varNationalitys)
			csp.addDomain(X,domain);

		for(Object X: varPets)
			csp.addDomain(X,domain);

		for(Object X: varCigarettes)
			csp.addDomain(X,domain);
		
		
		//unary constraints: just remove values from domains
		
		//Milk is drunk in the middle house.
		for(int i=1; i<=5; i++)
			if(i != 3)
				csp.D.get("Milk").remove(i);
				
		//The Norwegian lives in the first house on the left.
		for(int i=1; i<=5; i++)
			if(i != 1)
				csp.D.get("Norwegian").remove(i);

		
		//Uniqueness Contrains
		for(Object X: varColors)
			for(Object Y: varColors)
				csp.addBidirectionalArc(X,Y);

		for(Object X: varDrinks)
			for(Object Y: varDrinks)
				csp.addBidirectionalArc(X,Y);

		for(Object X: varNationalitys)
			for(Object Y: varNationalitys)
				csp.addBidirectionalArc(X,Y);

		for(Object X: varPets)
			for(Object Y: varPets)
				csp.addBidirectionalArc(X,Y);


		for(Object X: varCigarettes)
			for(Object Y: varCigarettes)
				csp.addBidirectionalArc(X,Y);

		//Binary constrains: add constrain arcs
		//1. The Englishman live in the red house
		csp.addBidirectionalArc("Englishman","Red");

		//2.The Spaniard owns a dog
		csp.addBidirectionalArc("Spaniard","Dog");

		//3.Coffee is drunk in the green house
		csp.addBidirectionalArc("Coffee","Green");

		//4.The Ukrainian drinks tea
		csp.addBidirectionalArc("Ukrainian","Tea");

		//5.The Green house is directly to the right of the ivory house
		//csp.addBidirectionalArc("Green","Ivory");

		//6.The Old-Gold smoker owns snail
		csp.addBidirectionalArc("Old-Gold","Snail");

		//7.Kools are being smoked in the yellow house
		csp.addBidirectionalArc("Kools","Yellow");

		/*
		//8. Milk is drunk in the middle house
		csp.addBidirectionalArc("Milk",new Integer[]{3});

		//9.The Snorwegian lives in the first house on the left
		csp.addBidirectionalArc("Snorwegian",new Integer[]{1});
		 */
		
		//10.The Chesterfield smoker lives next to the fox owner
		csp.addBidirectionalArc("Chesterfield","Fox");

		//11.Kools are smoked in the house next to the house where the horse is kept
		csp.addBidirectionalArc("Kools","Horse");

		//12.The Lucky-Strike smoker drinks orange juice
		csp.addBidirectionalArc("Lucky-Strike","Orange Juice");

		//13.The Japanese smokes Parliament
		csp.addBidirectionalArc("Japanese","Parliament");

		//14.The Norwegian lives next to the blue house
		csp.addBidirectionalArc("Norwegian","Blue");


        Search search = new Search(csp);
        System.out.println(search.BacktrackingSearch());
	}
}