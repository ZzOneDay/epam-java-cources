package com.epam.university.java.core.task056;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Task056Impl implements Task056 {
    private List<Medicine> medicines;


    @Override
    public Collection<Integer> necessaryMedication(String prescriptionFile) {
        if (prescriptionFile == null) {
            throw new IllegalArgumentException();
        }

        medicines = getMedicinesFromFile(prescriptionFile);
        List<TreatmentPlan> treatmentPlans = new LinkedList<>();
        for (Medicine firstMedicine : medicines) {
            TreatmentPlan plan = new TreatmentPlan();
            treatmentPlans.add(plan);
            plan.addMedicine(firstMedicine);

            for (Medicine medicine : medicines) {
                int firstDayOfPlan = plan.getFirstDay();
                int lastDayOfPlan = plan.getLastDay();
                if (medicine.equals(firstMedicine)) {
                    continue;
                }

                int firstDay = medicine.getFirstDay();
                int lastDay = medicine.getLastDay();
                if (lastDay < firstDayOfPlan || firstDay > lastDayOfPlan) {
                    plan.addMedicine(medicine);
                }
            }
        }

        treatmentPlans.sort((o1, o2) -> o2.countTabletsOfPlan() - o1.countTabletsOfPlan());
        List<Integer> medicinesIndexes = new LinkedList<>();
        for (Medicine medicine : treatmentPlans.get(0).getPlan()) {
            medicinesIndexes.add(medicine.getIndex());
        }

        Collections.sort(medicinesIndexes);
        return medicinesIndexes;
    }

    @Override
    public Collection<String> intervalBetweenMedication(Collection<Integer> necessaryMedication) {
        if (necessaryMedication == null) {
            throw new IllegalArgumentException();
        }

        TreatmentPlan plan = new TreatmentPlan();
        for (Integer integer : necessaryMedication) {
            for (Medicine medicine : medicines) {
                if (integer == medicine.getIndex()) {
                    plan.addMedicine(medicine);
                }
            }
        }

        return plan.intervalBetweenMedication();
    }


    private List<Medicine> getMedicinesFromFile(String prescriptionFile) {
        List<Medicine> medicines = new LinkedList<>();
        try {
            File file = new File(prescriptionFile);
            Scanner scanner = new Scanner(file);
            int index = 0;
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] params = line.split(" ");
                String[] days = params[1].split("-");

                int countTables = Integer.parseInt(params[0]);
                int firstDay = Integer.parseInt(days[0]);
                int lastDay = Integer.parseInt(days[1]);

                Medicine medicine = new Medicine(index, firstDay, lastDay, countTables);
                medicines.add(medicine);
                index++;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Parsing error, creating medicines");
        }
        return medicines;
    }
}
