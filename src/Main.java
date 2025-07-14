import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws PlantException {

        try {
            Plant plant1 = new Plant("Bazalka");
            //plant1.setWatering(LocalDate.of(2025, 7, 5));
            //plant1.doWateringNow();
            //plant1.setFrequencyOfWatering(-3);
            //plant1.setWatering(LocalDate.of(2025,6,13));
            System.out.println(plant1.getWateringInfo());
        } catch (PlantException plantException) {
            System.err.println(plantException.getMessage());
            //plantException.printStackTrace();
        }

    }
}