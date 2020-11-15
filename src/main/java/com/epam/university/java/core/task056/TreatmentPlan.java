package com.epam.university.java.core.task056;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class TreatmentPlan {
    private LinkedList<Medicine> plan = new LinkedList<>();

    void addMedicine(Medicine medicine) {
        plan.add(medicine);
    }

    int getFirstDay() {
        int firstDay = plan.get(0).getFirstDay();

        for (Medicine medicine : plan) {
            int firstDayOfMedicine = medicine.getFirstDay();
            if (firstDay < firstDayOfMedicine) {
                firstDay = firstDayOfMedicine;
            }
        }
        return firstDay;
    }

    int getLastDay() {
        int lastDay = plan.get(0).getLastDay();

        for (Medicine medicine : plan) {
            int lastDayOfMedicine = medicine.getLastDay();
            if (lastDayOfMedicine > lastDay) {
                lastDay = lastDayOfMedicine;
            }
        }

        return lastDay;
    }

    /**
     * Tablets for the all course.
     * @return number of tablets.
     */
    public int countTabletsOfPlan() {
        int count = 0;

        for (Medicine medicine : plan) {
            count += medicine.getCountTablets();
        }

        return count;
    }

    public LinkedList<Medicine> getPlan() {
        return plan;
    }


    Collection<String> intervalBetweenMedication() {
        LinkedList<Medicine> medicines = new LinkedList<>(plan);
        medicines.sort((o1, o2) -> o1.getFirstDay() - o2.getFirstDay());

        List<String> string = new LinkedList<>();
        Medicine firstMedicine = medicines.get(0);
        for (Medicine medicine : medicines) {
            if (firstMedicine == medicine) {
                continue;
            }
            int firstFreeDay = firstMedicine.getLastDay() + 1;
            int lastFreeDay = medicine.getFirstDay() - 1;
            firstMedicine = medicine;

            if (lastFreeDay < firstFreeDay) {
                continue;
            }

            String between = firstFreeDay + "-" + lastFreeDay;
            string.add(between);
        }

        return string;
    }
}
