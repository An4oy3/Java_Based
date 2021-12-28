
public class Cat
{
    public static final int EYES_COUNT = 2; //Модуль №3. Задание №4. Пункт №1 Создайте в классе Cat константы: «количество глаз», «минимальный вес», «максимальный вес».
    public static final int MIN_WEIGHT = 2000; //Модуль №3. Задание №4. Пункт №1 Создайте в классе Cat константы: «количество глаз», «минимальный вес», «максимальный вес».
    public static final int MAX_WEIGHT = 15000; //Модуль №3. Задание №4. Пункт №1 Создайте в классе Cat константы: «количество глаз», «минимальный вес», «максимальный вес».

    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;

    private double feedAmount = 0; //Модуль №3. Задание №2. Пункт №1 Переменная для подсчета обьема еды, которую сьела this.кот

    private static int count = 0; //Модуль №3. Задание №3. Пункт №1 Создайте у класса Cat статическую переменную count
    private Color catColor; //Модуль №3. Задание №6. Пункт №2 Создайте в классе Cat переменную, в которой хранится окрас

    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        count++; //Модуль №3. Задание №3. Пункт №1
    }

    //Модуль №3. Задание №7. Пункт №1 Сделайте метод создания «глубокой» копии кошки.
    public static Cat catCopy(Cat cat){
        Cat catCopy = new Cat(cat.getWeight());
        catCopy.setCatColor(cat.getCatColor());
        return catCopy;
    }

    public Cat(double weight){
        this.weight = weight;
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        count++;
    }
    //Модуль №3. Задание №6. Пункт №2. Getter для переменной catColor
    public Color getCatColor() {
        return catColor;
    }

    //Модуль №3. Задание №6. Пункт №2. Setter для переменной catColor
    public void setCatColor(Color catColor) {
        this.catColor = catColor;
    }

    public void meow()
    {
        weight = weight - 1;
        System.out.println("Meow");
        if(weight<minWeight){
            System.out.println("Dead");
            count--;
        }
    }

    public void feed(Double amount)
    {
        weight = weight + amount;
        feedAmount += amount; //Модуль №3. Задание №2. Пункт №1 Подсчет кол-ва сьеденной кошкой еды
        if(weight>maxWeight){
            System.out.println("Exploded");
            count--;
        }
    }

    public void drink(Double amount)
    {
        weight = weight + amount;
        if(weight>maxWeight){
            System.out.println("Exploded");
            count--;
        }
    }

    public Double getWeight()
    {
        return weight;
    }

    public String getStatus()
    {
        if(weight < minWeight) {
            count--; //Модуль №3. Задание №3. Пункт №1
            return "Dead";
        }
        else if(weight > maxWeight) {
            count--; //Модуль №3. Задание №3. Пункт №1
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }

    //Модуль №3. Задание №2. Пункт №1 Создайте в классе Cat метод, который возвращает сумму съеденной еды текущей кошки.
    public double getFeedAmount(){
        return this.feedAmount;
    }

    //Модуль №3. Задание №2. Пункт №2 Создайте в классе Cat метод «Сходить в туалет» pee(), который уменьшает вес кошки и что-нибудь печатает.
    public void pee(){
        System.out.println("Идем в туалет");
        weight = weight - 50.0;
            if(weight<minWeight){
                System.out.println("Dead");
                count--;
            }
    }

    //Модуль №3. Задание №3. Пункт №2 Создайте статический метод getCount(), который возвращает количество кошек.
    public static int getCount() {
        return count;
    }




}