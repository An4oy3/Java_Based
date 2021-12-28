package core;

public class Car {
    public String number;//Переменная типа String
    public int height;//Переменная типа int
    public double weight;//Переменная типа double
    public boolean hasVehicle;//Переменная типа boolean
    public boolean isSpecial;//Переменная типа boolean

    /*Модуль №3. Задание №6. Пункт №1 В классе Car проекта RoadController создайте геттеры и сеттеры для всех переменных класса.
    ================================================================================================================*/

    public String getNumber() {
        return number;
    } //Модуль №3. Задание №6. Пункт №1. Getter для переменной number

    public int getHeight() {
        return height;
    } //Модуль №3. Задание №6. Пункт №1. Getter для переменной height

    public double getWeight() {
        return weight;
    } //Модуль №3. Задание №6. Пункт №1. Getter для переменной weight

    public boolean isHasVehicle() {
        return hasVehicle;
    }//Модуль №3. Задание №6. Пункт №1. Getter для переменной hasVehicle

    public boolean isSpecial() {
        return isSpecial;
    } //Модуль №3. Задание №6. Пункт №1. Getter для переменной isSpecial

    public void setNumber(String number) {
        this.number = number;
    } //Модуль №3. Задание №6. Пункт №1. Setter для переменной number

    public void setHeight(int height) {
        this.height = height;
    } //Модуль №3. Задание №6. Пункт №1. Setter для переменной height

    public void setWeight(double weight) {
        this.weight = weight;
    } //Модуль №3. Задание №6. Пункт №1. Setter для переменной weight

    public void setHasVehicle(boolean hasVehicle) {
        this.hasVehicle = hasVehicle;
    } //Модуль №3. Задание №6. Пункт №1. Setter для переменной hasVehicle

    public void setSpecial(boolean special) {
        isSpecial = special;
    } //Модуль №3. Задание №6. Пункт №1. Setter для переменной special

     //================================================================================================================

    public String toString() {
        String special = isSpecial ? "СПЕЦТРАНСПОРТ " : "";
        return "\n=========================================\n" +
            special + "Автомобиль с номером " + number +
            ":\n\tВысота: " + height + " мм\n\tМасса: " + weight + " кг";
    }
}