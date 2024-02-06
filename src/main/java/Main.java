import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    List<Person> people = getPeople();
    System.out.println("\n*** PEOPLE ***");
    people.forEach(System.out::println);

    // Imperative approach ❌

    /*

    List<Person> females = new ArrayList<>();

    for (Person person : people) {

      if (person.getGender().equals(Gender.FEMALE)) {
        females.add(person);
      }
    }

    females.forEach(System.out::println);

    */

    // Declarative approach ✅

    // Filter
    List<Person> females = people.stream()
        .filter(person -> person.getGender().equals(Gender.FEMALE))
        .collect(Collectors.toList());
    System.out.println("\n*** FILTER ONLY FEMALE PEOPLE ***");
    females.forEach(System.out::println);

    // Sort & reverse
    List<Person> sorted = people.stream()
        .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender).reversed())
        .collect(Collectors.toList());
    System.out.println("\n*** SORTED PEOPLE BY AGE, THEN GENDER AND REVERSE IT***");
    sorted.forEach(System.out::println);

    // All match
    boolean allMatch = people.stream()
        .allMatch(person -> person.getAge() > 8); // ONLY 1 PERSON DON'T MATCH
    System.out.println("\n*** CHECK IF ALL MATCH ***");
    System.out.println(allMatch);

    // Any match
    boolean anyMatch = people.stream()
        .anyMatch(person -> person.getAge() < 8);  // ONLY 1 PERSON MATCH
    System.out.println("\n*** ANY MATCH ***");
    System.out.println(anyMatch);

    // None match
    boolean noneMatch = people.stream()
        .noneMatch(person -> person.getName().equals("Antonio")); // ANTONIO APPEARS AT LEAST 1 TIME
    System.out.println("\n*** NONE MATCH ***");
    System.out.println(noneMatch);

    // Max
    System.out.println("\n*** GET PERSON WITH MAX AGE ***");
    people.stream()
        .max(Comparator.comparing(Person::getAge))
        .ifPresent(System.out::println);

    // Min
    System.out.println("\n*** GET PERSON WITH MIN AGE ***");
    people.stream()
        .min(Comparator.comparing(Person::getAge))
        .ifPresent(System.out::println);

    // Group
    Map<Gender, List<Person>> groupByGender = people.stream()
        .collect(Collectors.groupingBy(Person::getGender));

    System.out.println("\n*** GROUP BY GENDER ***");
    groupByGender.forEach((gender, people1) -> {
      System.out.println(gender);
      people1.forEach(System.out::println);
      System.out.println();
    });

    System.out.println("\n*** OLDEST FEMALE ***");
    Optional<String> oldestFemaleAge = people.stream()
        .filter(person -> person.getGender().equals(Gender.FEMALE))
        .max(Comparator.comparing(Person::getAge))
        .map(Person::getName); // RETURN THE NAME OF THE FILTERED PERSON

    oldestFemaleAge.ifPresent(System.out::println);
  }

  private static List<Person> getPeople() {
    return List.of(
        new Person("Antonio", 20, Gender.MALE),
        new Person("Alina Smith", 33, Gender.FEMALE),
        new Person("Helen White", 57, Gender.FEMALE),
        new Person("Alex Boz", 14, Gender.MALE),
        new Person("Jamie Goa", 99, Gender.MALE),
        new Person("Anna Cook", 7, Gender.FEMALE),
        new Person("Zelda Brown", 120, Gender.FEMALE)
    );
  }

}
