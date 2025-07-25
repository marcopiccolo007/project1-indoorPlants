import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws PlantException {

        //Testovací aplikace pro Plant a PlantException --> DONE

        //Testovací aplikace pro PlantsManager
        PlantsManager testPlantsList = new PlantsManager(new ArrayList<>());
        try {
            Plant plant1 = new Plant("Bazalka");
            plant1.addNote("druha poznamka k bazalce");
            Plant plant2 = new Plant("Petrzelka", List.of("zkusebni poznamka", "dalsi poznamka"),
                    LocalDate.of(2025, 6, 1),
                    LocalDate.of(2025, 6, 10), 5);
            Plant plant3 = new Plant("Rozmaryn", List.of("plantoidni nota", "chlorofyl ok"),
                    LocalDate.of(2025, 7, 1),
                    LocalDate.of(2025, 7, 10), 3);

            testPlantsList.addPlant(plant1);
            testPlantsList.addPlant(plant2);
            testPlantsList.addPlant(plant3);

            System.out.println(testPlantsList.getPlantListClone());
            System.out.println(testPlantsList.getPlantsToWatering());


        } catch (PlantException e) {
            System.err.println(e.getMessage());
        }

    }
}