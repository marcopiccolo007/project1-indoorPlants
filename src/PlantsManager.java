import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class PlantsManager {

    private List<Plant> plantList;

    public PlantsManager(List<Plant> plantList) {
        this.plantList = new ArrayList<>(plantList);
        Collections.sort(this.plantList);//třída Plant už má 'implements Comparable' a metodu compareTo
    }

    public void addPlant(Plant newPlant) {
        plantList.add(newPlant);//1. přidání nové květiny
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

    public List<Plant> getPlants() {
        return plantList;
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

    public List<Plant> getPlantListSortedAscByWatering() {
        List<Plant> sortedList = new ArrayList<>(plantList);
        sortedList.sort((p1, p2) -> p1.getWatering().compareTo(p2.getWatering()));
        return sortedList; //5. získání seznamu květin seřazeného podle data posledni zalivky vzestupně (ASC)
    }// bude fungovat i pro null hodnoty, pokud je v seznamu alespoň jedna květina s datem zalivky

    public List<Plant> getPlantListSortedDescByWatering() {
        List<Plant> sortedList = new ArrayList<>(plantList);
        sortedList.sort((p1, p2) -> p2.getWatering().compareTo(p1.getWatering()));
        //sortedList.sort(Comparator.comparing(Plant::getWatering).reversed());
        //funguje jen pokud getWatering() vždy vrací LocalDate (ne null hodnotu)
        return sortedList; //5. získání seznamu květin seřazeného podle data posledni zalivky sestupně (DESC)
    }

    public List<Plant> getPlantListSortedAscByName() {
        List<Plant> sortedList = new ArrayList<>(plantList);
        sortedList.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        return sortedList; //6. získání seznamu květin seřazeného podle jména vzestupně (ASC)
    }

    public List<Plant> getPlantListSortedDescByName() {
        List<Plant> sortedList = new ArrayList<>(plantList);
        sortedList.sort((p1, p2) -> p2.getName().compareTo(p1.getName()));
        //sortedList.sort(Comparator.comparing(Plant::getName).reversed());
        //funguje jen pokud getName() vždy vrací String (ne null hodnotu)
        return sortedList; //6. získání seznamu květin seřazeného podle jména sestupně (DESC)
    }

    //načtení květin ze souboru (loadFromFile)
    public void loadFromFile(String fileName, String delimiter) throws PlantException {
        int lineNumber = 0;
        String line = "";
        List<Plant> loadedPlants = new ArrayList<>();
        String[] parts;
        try (Scanner record = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (record.hasNextLine()) {
                lineNumber++;
                line = record.nextLine();
                if (line.contains(Settings.getFileHeader())) continue; //přeskočí hlavičku souboru
                if (line.trim().isEmpty()) continue;// načtení dalšího řádku ze souboru
                // parsing (parsování) řádku podle zadaného oddělovače
                parts = line.split(delimiter);
                if (parts.length != Settings.getParsingRowLength()) {
                    System.err.println("Chybný počet prvků na řádku: " + line);
                    continue; //pokračuje na další řádek, pokud není správný počet prvků
                }
                parsePlant(parts, loadedPlants, fileName, lineNumber, line);
            }
            this.plantList = loadedPlants; //nastavení načtených květin do plantList
            Collections.sort(this.plantList); //seřazení plantList podle jména květin
        } catch (IOException e) {
            System.out.println("Soubor nelze načíst: " + e.getMessage());
            this.plantList = new ArrayList<>(); // aplikace pokračuje, seznam prázdný
        }
    }

    private void parsePlant(String[] parts, List<Plant> plantList, String fileName,
                            int lineNumber, String line) throws PlantException {
        try {
            String name = parts[0].trim();
            List<String> notes = Arrays.asList(parts[1].trim().split(","));
            LocalDate planted = LocalDate.parse(parts[2].trim());
            LocalDate watering = LocalDate.parse(parts[3].trim());
            int frequencyOfWatering = Integer.parseInt(parts[4].trim());
            Plant plant = new Plant(name, notes, planted, watering, frequencyOfWatering);
            plantList.add(plant);
        } catch (DateTimeParseException e) {
            throw new PlantException("Chybný formát data a času na řádku číslo: " + lineNumber +
                    ".\nSoubor: " + fileName + "\nData na řádku: " + line +
                    "\n" + e.getMessage());
        } catch (NumberFormatException e) {
            throw new PlantException("Chybný formát čísla na řádku číslo: " + lineNumber +
                    ".\nSoubor: " + fileName + "\nData na řádku: " + line +
                    "\n" + e.getMessage());
        }
    }

    public void saveToFile(String fileName, String delimiter) throws PlantException {
        if (plantList == null || plantList.isEmpty()) {
            throw new PlantException("Seznam květin je prázdný, nejsou data k uložení do souboru.");
        }
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            writer.println("name\tnotes\tplanted\twatering\tfrequency");//pridani hlavicky souboru
            for (Plant plant : plantList) {
                String line = String.join(delimiter,
                        plant.getName(),
                        String.join(",", plant.getNotes()),
                        plant.getPlanted().toString(),
                        plant.getWatering().toString(),
                        String.valueOf(plant.getFrequencyOfWatering()));
                writer.println(line);
            }
        } catch (IOException e) {
            throw new PlantException("Chyba při zápisu do souboru: " + fileName + "\n" + e.getMessage());
        }
    }
}