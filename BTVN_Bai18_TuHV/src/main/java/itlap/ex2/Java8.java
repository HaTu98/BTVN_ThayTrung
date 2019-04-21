package itlap.ex2;

import java.util.*;
import java.util.stream.Collectors;

public class Java8 {
    public static void main(String[] args) {
        List<Students> students = Arrays.asList(
                new Students("TuHV", 21, 4000000),
                new Students("TuNT", 22, 4000000),
        new Students("LongNT", 23, 4000000),
        new Students("DuyKK", 22, 4000000),
        new Students("HoangNH", 23, 4000000),
        new Students("KhanhNT", 23, 4000000),
        new Students("AnhQHT", 23, 4000000),
        new Students("NamDQ", 23, 4000000),
        new Students("HoangNV", 22, 4000000),
        new Students("ToanNT", 22, 4000000)
        );

        long sumMoney = students.stream()
                .mapToLong(Students::getMoney)
                .sum();
        System.out.println("Total " + sumMoney);
        Map student = students.stream().collect(Collectors.toMap(Students::getName, Students::getAge));
        System.out.println( "List student : " + student);

        int count = (int) students.stream()
                .count();
        System.out.println("Count Student : " + count);

        Comparator<Students> comparator = Comparator.comparing(Students::getAge);

        Students min = students.stream().min(comparator).get();
        System.out.println(min.getName() + " : " + min.getAge());

        Students max = students.stream().max(comparator).get();
        System.out.println(max.getName() + " : " + max.getAge());



        Map<Integer, Set<String>> result =
                students.stream().collect(
                        Collectors.groupingBy(Students::getAge,
                                Collectors.mapping(Students::getName, Collectors.toSet())
                        )
                );

        System.out.println(result);

    }
}
