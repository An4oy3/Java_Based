import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int boxes = Integer.parseInt(scanner.nextLine());

        int maxBoxesInCont = 27;
        int maxContInCar = 12;

        int conteiner;
        int carsCount;
        int boxesCount = 1;
        int conteinersCount = 1;

        if(boxes % maxBoxesInCont == 0)
            conteiner = boxes / maxBoxesInCont;
        else
            conteiner = (boxes / maxBoxesInCont) + 1;

        if(conteiner % maxContInCar == 0)
            carsCount = conteiner / maxContInCar;
        else
            carsCount = (conteiner / maxContInCar) + 1;

        for(int i = 1; i <= carsCount; i++){
            System.out.println("Грузовик: " + i);
                for(int j = 1; j <= maxContInCar; j++) {
                    if(boxesCount <= boxes) {
                        System.out.println("\tКонтейнер: " + conteinersCount);
                        for (int b = 1; b <= maxBoxesInCont; b++) {
                            if (boxesCount <= boxes) {
                                System.out.println("\t\tЯщик: " + boxesCount);
                                boxesCount++;
                            } else {
                                break;
                            }
                        }
                        conteinersCount++;
                    } else {
                        break;
                    }
                }
        }

        System.out.println("Необходимо:\n" +
                "грузовиков - " + carsCount + " шт.\n" +
                "контейнеров - " + conteiner + " шт.");















        // TODO: вывести в консоль коробки разложенные по грузовикам и контейнерам
        // пример вывода при вводе 2
        // для отступа используйте табуляцию - \t

        /*
        Грузовик: 1
            Контейнер: 1
                Ящик: 1
                Ящик: 2
        Необходимо:
        грузовиков - 1 шт.
        контейнеров - 1 шт.
        */
    }

}
