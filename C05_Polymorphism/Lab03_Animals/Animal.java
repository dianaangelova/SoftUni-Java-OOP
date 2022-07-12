package C05_Polymorphism.Lab03_Animals;

public abstract class Animal {
    private String name;
    private String favoriteFood;

    protected Animal (String name, String favoriteFood) {
        this.name = name;
        this.favoriteFood = favoriteFood;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(java.lang.String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }


    public String explainSelf() {
        return "I am " + name + " and my favourite food is " + favoriteFood;
    }
}
