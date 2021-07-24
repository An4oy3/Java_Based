
public class Loader
{
    //Модуль №3. Задача №5
    private static Cat getKitten(){
        return new Cat(1100.00);
    }


    public static void main(String[] args)
    {
        Cat cat = new Cat();

        System.out.println(cat.getStatus());


        //Модуль №3. Задача №1. Пункт 1. Выводим вес кошексв
        Cat vasya = new Cat();
        Cat zara = new Cat();
        Cat jora = new Cat();
        Cat oscar = new Cat();
        System.out.println("Вес кота cat: " + cat.getWeight());
        System.out.println("Вес кота vasya: " + vasya.getWeight());
        System.out.println("Вес кота zara: " + zara.getWeight());
        System.out.println("Вес кота jora: " + jora.getWeight());
        System.out.println("Вес кота oscar: " + oscar.getWeight());


        //Модуль №3. Задача №1. Пункт 2. Кормим кошек "vasya" & "zara" и выводим их вес
        System.out.println("Кормим котов vasya и zara");
        vasya.feed(3.1);
        zara.feed(2.8);
        System.out.println("Вес кота vasya: " + vasya.getWeight());
        System.out.println("Вес кота zara: " + zara.getWeight());


        //Модуль №3. Задача №1. пункт 3. Перекормите кошку, чтобы ее статус стал Exploded.
        System.out.println("Взрываем кота \"cat\" едой :)" );
        System.out.println("Статус кота \"cat\": " + cat.getStatus());
        while (cat.getStatus()!="Exploded"){
            cat.feed(0.5);
        }
        System.out.println("Статус кота \"cat\": " + cat.getStatus());


        //Модуль №3. Задача №1. Пункт 4. «Замяукайте» кошку до статуса Dead.
        System.out.println("Убиваем кота \"vasya\" лаской :)");
        System.out.println("Статус кота \"vasya\": " + vasya.getStatus());
        while (vasya.getStatus()!="Dead"){
            vasya.meow();
        }
        System.out.println("Статус кота \"vasya\": " + vasya.getStatus());


        //Модуль №3. Задача №2. Вывод результата
        System.out.println(vasya.getWeight());
        vasya.feed(150.00);
        System.out.println(vasya.getWeight());
        vasya.feed(100.00);
        System.out.println(vasya.getWeight());
        System.out.println(vasya.getFeedAmount());
        vasya.pee();
        vasya.pee();
        System.out.println(vasya.getWeight());

        //Модуль №3. Задача №3. Вывод результата
        System.out.println(Cat.getCount());


        //Модуль №3. Задача №5. Пункт №3 Создайте три объекта класса Cat в методе main(), используя метод getKitten().

        Cat mick = getKitten();
        Cat nick = getKitten();
        Cat volt = getKitten();


        //Модуль №3. Задание №7. Пункт №1 Сделайте метод создания «глубокой» копии кошки.
        vasya.setCatColor(Color.GINGER);
        Cat catCopy = Cat.catCopy(vasya);

        System.out.println(vasya.getWeight() + "  "+ vasya.getCatColor());
        System.out.println(catCopy.getWeight() + "  "+ catCopy.getCatColor());



    }
}