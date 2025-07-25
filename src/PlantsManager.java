import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlantsManager {

    private List<Plant> plantList;

    public PlantsManager(List<Plant> plantList) {
        this.plantList = new ArrayList<>(plantList);
    }

    public void addPlant(Plant plant) {
        plantList.add(plant);//1. přidání nové květiny
    }

    public Plant getPlant(int index) throws PlantException {
        if (index < 0 || index > (plantList.size() - 1)) {
            throw new PlantException("Zadaný index rostliny je mimo platný rozsah (MIN==0, MAX=="
                    + (plantList.size() - 1) + "). Zadaná hodnota indexu: " + index);
        }
        return plantList.get(index);//2. získání květiny na zadaném indexu
    }

    public void removePlant(Plant plant) {
        plantList.remove(plant); //3. odebrání květiny ze seznamu
    }

    public List<Plant> getPlantListClone() {
        return new ArrayList<>(plantList);//4. získání kopie seznamu květin
    }

    //Metoda, která vrátí seznam rostlin, které je třeba zalít.
    public List<Plant> getPlantsToWatering() throws PlantException {
        List<Plant> plantsToWatering = new ArrayList<>();
        for (Plant plant : plantList) {
            if (plant.getNextWateringDate().isBefore(LocalDate.now())) {
                plantsToWatering.add(plant);
            }
        }
        return plantsToWatering;
    }
}