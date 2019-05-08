package model;

public class Weather {

    public Place place;
    public String iconData;
    public CurrentCondition currentCondition = new CurrentCondition();
    public Tempreture tempreture = new Tempreture();
    public Wind wind = new Wind();
    public Snow snow = new Snow();
    public Clouds clouds = new Clouds();

}
