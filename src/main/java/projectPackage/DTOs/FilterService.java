package projectPackage.DTOs;
//
//public class FilterService {
//    private List<Person> people;
//
//    public EntityService(List<Person> people) {
//        this.people = people;
//    }
//
//    public List<Person> getPeopleByFilter(FilterDTO filterDTO) {
//        List<Person> filteredPeople = new ArrayList<>();
//
//        for (Person person : people) {
//            if (matchesFilter(person, filterDTO)) {
//                filteredPeople.add(person);
//            }
//        }
//        return filteredPeople;
//    }
//
//    private boolean matchesFilter(Person person, FilterDTO filterDTO) {
//        // Check if the person matches the filter criteria
//        boolean nameMatches = filterDTO.getName() == null || person.getName().equals(filterDTO.getName());
//        boolean ageInRange = (filterDTO.getMinAge() == null || person.getAge() >= filterDTO.getMinAge()) &&
//                (filterDTO.getMaxAge() == null || person.getAge() <= filterDTO.getMaxAge());
//
//        return nameMatches && ageInRange;
//    }
//}
//}
