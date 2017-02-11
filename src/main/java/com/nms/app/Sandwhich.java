package com.nms.app;

public class Sandwhich {
	private String name; // Name Should contain students name + Special
							// Instructions
	private String classGroup;

	// Sandwhich Ingredients - could be all booleans instead
	private String breadType;
	private boolean margerine = false;
	private int vegimite = 0;
	private int hommus = 0;
	private int chicken = 0;
	private int ham = 0;
	private int tomato = 0;
	private int lettuce = 0;
	private int done; // Blank Column for attendants to fill as sandwiches are
						// made

	public Sandwhich() {

	}

	/**
	 * 
	 * @param sandwhichIngredients
	 *            We will get a string from an excel order file such as (
	 *            "margerine, white bread, chicken" etc) Splitting this string
	 *            will give us an array of ingredients, which we can then use to
	 *            create a sandwhich description
	 */
	// Skoolbag is currently doen so I cant get the exact syntax of the
	// ingredients of an example order form
	public Sandwhich(String sandwhichIngredients, String name, String specialInstructions, String classGroup) {
		this.name = name + " " + specialInstructions;
		this.classGroup = classGroup;

		String[] ingredients = sandwhichIngredients.split(", ");
		for (int i = 0; i < ingredients.length; i++) {
			switch (ingredients[i]) {
			case "White High Fibre Bread":
				breadType = ingredients[i];
				break;
			case "Hommus":
				hommus = 1;
				break;
			case "Vegimite":
				vegimite = 1;
				break;
			case "Chicken":
				chicken = 1;
				break;
			case "Tomato":
				tomato = 1;
				break;
			case "Ham":
				ham = 1;
			case "Lettuce":
				lettuce = 1;
				break;
			case "Margerine":
				margerine = true;
				break;
			default:
				break;
			}
		}

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
