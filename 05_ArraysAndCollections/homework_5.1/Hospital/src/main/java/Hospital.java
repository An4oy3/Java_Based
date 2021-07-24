public class Hospital {

    public static float[] generatePatientsTemperatures(int patientsCount) {
        float[] patientsList = new float[patientsCount];
        for (int i = 0; i < patientsList.length; i++) {
            float f = (float) (Math.random() * ((40-32))) + 32;
            patientsList[i] = (float) Math.round(f * 10f)/10f;
        }

        //TODO: напишите метод генерации массива температур пациентов

        return patientsList;
    }

    public static String getReport(float[] temperatureData) {
        /*
        TODO: Напишите код, который выводит среднюю температуру по больнице,количество здоровых пациентов,
            а также температуры всех пациентов.
        */

        float avValue = 0;
        int healthPersons = 0;
        String report =
                "Температуры пациентов: " ;
        for (int i = 0; i < temperatureData.length; i++) {
            temperatureData[i] = (float) Math.round(temperatureData[i] * 10f)/10f;
            if(temperatureData[i] >= 36.2f && temperatureData[i] <= 36.9f){
                healthPersons++;
            }
            avValue += temperatureData[i];
            if(i < temperatureData.length-1) {
                report += temperatureData[i] + " ";
            }else {
                report += temperatureData[i];
            }
        }

        avValue = (float) Math.round((avValue/temperatureData.length) * 100f)/100f;
                report +=
                        "\nСредняя температура: " + avValue +
                        "\nКоличество здоровых: " + healthPersons;

        return report;
    }
}
