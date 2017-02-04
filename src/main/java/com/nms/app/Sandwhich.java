package com.nms.app;

public class Sandwhich {
	private String name; //Name Should contain students name + Special Instructions
	private String classGroup;
	
	
	//Sandwhich Ingredients
	private String breadType;
	private boolean margerine;
	private int vegimite;
	private int hommus;
	private int chicken;
	private int ham;
	private int tomato;
	private int lettuce;
	private int done;		//Blank Column for attendants to fill as sandwiches are made
	
	public Sandwhich(){
		
	}
	/**
	 * 
	 * @param sandwhichIngredients We will get a string from an excel order file such as ("margerine, white bread, chicken" etc)
	 * Splitting this string will give us an array of ingredients, which we can then use to create a sandwhich description
	 */
	//Skoolbag is currently doen so I cant get the exact syntax of the ingredients of an example order form
	public Sandwhich(String sandwhichIngredients, String name, String specialInstructions, String classGroup){
		this.name =  name +" "+ specialInstructions;
		this.classGroup = classGroup;

		String [] ingredients = sandwhichIngredients.split(",");
		
		//TODO
		//Place a bunch of if statement here, or a switch statement.
		//Each case will match to something like "white bread", if we have a match, add that ingredient to the data members
		
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassGroup() {
		return classGroup;
	}
	public void setClassGroup(String classGroup) {
		this.classGroup = classGroup;
	}
	public String getBreadType() {
		return breadType;
	}
	public void setBreadType(String breadType) {
		this.breadType = breadType;
	}
	public boolean isMargerine() {
		return margerine;
	}
	public void setMargerine(boolean margerine) {
		this.margerine = margerine;
	}
	public int getVegimite() {
		return vegimite;
	}
	public void setVegimite(int vegimite) {
		this.vegimite = vegimite;
	}
	public int getHommus() {
		return hommus;
	}
	public void setHommus(int hommus) {
		this.hommus = hommus;
	}
	public int getChicken() {
		return chicken;
	}
	public void setChicken(int chicken) {
		this.chicken = chicken;
	}
	public int getHam() {
		return ham;
	}
	public void setHam(int ham) {
		this.ham = ham;
	}
	public int getTomato() {
		return tomato;
	}
	public void setTomato(int tomato) {
		this.tomato = tomato;
	}
	public int getLettuce() {
		return lettuce;
	}
	public void setLettuce(int lettuce) {
		this.lettuce = lettuce;
	}
	public int getDone() {
		return done;
	}
	public void setDone(int done) {
		this.done = done;
	}

	
	

}
