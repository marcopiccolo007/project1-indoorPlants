import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws PlantException {

        //1. Načtení seznamu květin ze souboru kvetiny_IN.txt (loadFromFile)
        PlantsManager managerFromFile = new PlantsManager(new ArrayList<>());
        try {
            managerFromFile.loadFromFile(Settings.getFileNameIn(), Settings.getDelimiter());
        } catch (PlantException e) {
            System.err.println("Chyba při načítání rostlin ze souboru. " + e.getMessage());
        }

        //2. Vypsání na obrazovku informace o zálivce pro všechny květiny ze seznamu (getWateringInfo)
        System.out.println("\nVstupní seznam květin s informací o zálivce jednotlivých rostlin:");
        for (Plant plant : managerFromFile.getPlants()) {
            System.out.println(plant.getWateringInfo());
        }

        //3. Přidání nové květiny do seznamu (libovolné údaje)
        Plant newPlant = new Plant("Royal Rose", new ArrayList<>(List.of("kralovska ruze", "citliva na chlad")),
                LocalDate.of(2025, 7, 7), LocalDate.of(2025, 7, 14),
                7);
        managerFromFile.addPlant(newPlant);

        //4. Přidání 10 rostlin s popisem „Tulipán na prodej 1“ až „Tulipán na prodej 10“
        // Zasazeny byly dnes, zality také, frekvence zálivky je 14 dnů
        for (int i = 1; i <= 10; i++) {
            Plant tulip = new Plant("Tulipán na prodej " + i, new ArrayList<>(List.of("prodej tulipánu " + i)),
                    LocalDate.now(), LocalDate.now(), 14);
            managerFromFile.addPlant(tulip);
        }

        //5. Odebrání květiny na třetí pozici ze seznamu
        managerFromFile.removePlant(managerFromFile.getPlant(2)); // index 2 odpovídá třetí pozici ze seznamu (indexování začíná od 0)

        //6. Uložení seznamu květin do nového souboru kvetiny_OUT.txt (saveToFile)
        PlantsManager managerToFile = new PlantsManager(managerFromFile.getPlantListClone());
        try {
            managerToFile.saveToFile(Settings.getFileNameOut(), Settings.getDelimiter());
        } catch (PlantException e) {
            System.err.println("Chyba při ukládání rostlin do souboru. " + e.getMessage());
        }

        //7. Načtení seznamu květin z nového souboru kvetiny_OUT.txt (loadFromFile)
        PlantsManager manager = new PlantsManager(new ArrayList<>());
        try {
            manager.loadFromFile(Settings.getFileNameOut(), Settings.getDelimiter());
        } catch (PlantException e) {
            System.err.println("Chyba při načítání rostlin ze souboru. " + e.getMessage());
        }

        //8. Test seřazení rostlin ve správci seznamu podle různých kritérií
        // + výpis seřazeného seznamu

        System.out.println("\nNový seznam květin - výchozí řazení seznamu rostlin (dle názvu vzestupně):");
        for (Plant plant : manager.getPlants()) {
            //System.out.println(plant.toString());
            System.out.println(plant.getWateringInfo());
        }

        System.out.println("\nNový seznam květin seřazený podle názvu rostliny sestupně (DESC):");
        manager.getPlantListSortedDescByName().forEach(plant -> {
            System.out.println("Rostlina: " + plant.getName() + ", poslední zálivka: " + plant.getWatering());
        });

        System.out.println("\nNový seznam květin seřazený podle data poslední zálivky vzestupně (ASC):");
        manager.getPlantListSortedAscByWatering().forEach(plant -> {
            System.out.println("Rostlina: " + plant.getName() + ", poslední zálivka: " + plant.getWatering());
        });
    }
}//konec třídy Main