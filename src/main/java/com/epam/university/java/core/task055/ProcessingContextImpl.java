package com.epam.university.java.core.task055;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ProcessingContextImpl implements ProcessingContext {
    private List<HouseDefinition> houseDefinitionList;

    private ProcessingContextImpl(List<HouseDefinition> houseDefinitionList) {
        this.houseDefinitionList = houseDefinitionList;


    }

    static ProcessingContextImpl getInstance(List<HouseDefinition> houseDefinitionList) {
        for (HouseDefinition house : houseDefinitionList) {
            HouseDefinitionImpl houseDefinition = (HouseDefinitionImpl) house;
            String yearString = houseDefinition.getYearString();
            yearString = yearString.replaceAll("[^0-9]", "");
            if (yearString.length() == 0) {
                house.setYear(-1);
                continue;
            }

            if (yearString.length() == 4) {
                house.setYear(Integer.parseInt(yearString));
            } else {
                //incorrect data
                house.setYear(-1);
            }
        }

        return new ProcessingContextImpl(houseDefinitionList);
    }


    @Override
    public Collection<HouseDefinition> oldestHouses() {
        int oldestYear = 2020;
        for (HouseDefinition house : houseDefinitionList) {
            if (house.getYear() == -1) {
                continue;
            }

            if (house.getYear() < oldestYear) {
                oldestYear = house.getYear();
            }
        }
        List<HouseDefinition> oldestHouse = new LinkedList<>();

        for (HouseDefinition house : houseDefinitionList) {
            if (house.getYear() == oldestYear) {
                oldestHouse.add(house);
            }
        }

        return oldestHouse;
    }

    @Override
    public int averageAge(String district) {
        int count = 0;
        int sum = 0;
        for (HouseDefinition house : houseDefinitionList) {
            if (house.getYear() == -1) {
                continue;
            }

            if (house.getAddress().toLowerCase().contains(district.toLowerCase())) {
                count++;
                sum += (2020 - house.getYear());
            }
        }

        if (count == 0) {
            //City is not district.
            for (HouseDefinition house : houseDefinitionList) {
                HouseDefinitionImpl houseDefinition = (HouseDefinitionImpl) house;
                String address = houseDefinition.getFullAddress();
                if (address.contains("Санкт-Петербург")
                        && houseDefinition.getYear() != -1) {
                    count++;
                    sum += (2020 - house.getYear());
                }
            }
        }

        return sum / count;
    }

    @Override
    public HouseDefinition biggestTotalFloorSpace() {
        double area = 0;
        for (HouseDefinition house : houseDefinitionList) {
            if (house.getArea() > area) {
                area = house.getArea();
            }
        }
        List<HouseDefinition> oldestHouse = new LinkedList<>();

        for (HouseDefinition house : houseDefinitionList) {
            if (house.getArea() == area) {
                oldestHouse.add(house);
            }
        }

        return oldestHouse.get(0);
    }

    @Override
    public HouseDefinition smallestTotalFloorSpace() {
        double area = 10000;
        for (HouseDefinition house : houseDefinitionList) {
            if (house.getArea() < area && house.getArea() != 0) {
                area = house.getArea();
            }
        }
        List<HouseDefinition> oldestHouse = new LinkedList<>();

        for (HouseDefinition house : houseDefinitionList) {
            if (house.getArea() == area) {
                oldestHouse.add(house);
            }
        }

        return oldestHouse.get(0);
    }

    @Override
    public int totalCountHouses() {
        return houseDefinitionList.size();
    }

    @Override
    public int totalCountHousesWithCommunalFlats() {
        int count = 0;
        for (HouseDefinition house : houseDefinitionList) {
            HouseDefinitionImpl houseDefinition = (HouseDefinitionImpl) house;
            if (houseDefinition.getCommType().length() != 0) {
                count++;
            }
        }
        return count;
    }
}
