import java.util.List;
import java.util.Set;
import java.util.Optional;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Main {
    public static void main(String[] args) {
        //initialize winners list
        List<Winner> winners = Winner.tdfWinners;

        //Queries calls
        Set<String> winnerNameUnder3450Km = winnerNameUnder3450Km(winners);
        //winnerNameUnder3450Km.forEach(System.out::println);

        List<String> winnerOfToursLessThan3500Km = winnersOfToursLessThan3500Km(winners);
        //winnerOfToursLessThan3500Km.forEach(System.out::println);

        List<String> winnerOfToursGreaterThan3500Km = winnersOfToursGreaterThan3500Km(winners);
        //winnerOfToursGreaterThan3500Km.forEach(System.out::println);

        List<Winner> winnerObjectsOfToursLessThan3500kmLimit2 = winnerObjectsOfToursLessThan3500kmLimit2(winners);
        //winnerObjectsOfToursLessThan3500kmLimit2.forEach(System.out::println);

        List<String> distinctTDFWinners = distinctTDFWinners(winners);
        //distinctTDFWinners.forEach(System.out::println);

        long numberOfDistinctWinners = numberOfDistinctWinners(winners);
        //System.out.println(numberOfDistinctWinners);

        List<String> mapWinnerYearNamesToList = mapWinnerYearNamesToList(winners);
        //mapWinnerYearNamesToList.forEach(System.out::println);

        List<Integer> mapWinnerNameLengthToList = mapWinnerNameLengthToList(winners);
        //mapWinnerNameLengthToList.forEach(System.out::println);

        Optional<Winner> winner2012 = winner2012(winners);
        //System.out.println(winner2012.get());

        int totalDistance = totalDistance(winners);
        //System.out.println(totalDistance);

        Optional<Integer> shortestYear = shortestYear(winners);
        //System.out.println(shortestYear.get());

        Optional<Integer> longestYear = longestYear(winners);
        //System.out.println(longestYear.get());

        Optional<Winner> fastestWinner = fastestWinner(winners);
        //System.out.println(fastestWinner.get());

        Optional<Double> fastestTDF = fastestTDF(winners);
        //System.out.println(fastestTDF.get());

        Map<String, List<Winner>> namesVsWinner = namesVsWinner(winners);
        //namesVsWinner.entrySet().forEach(System.out::println);

        String allTDFWinnersTeamsCSV = allTDFWinnersTeamsCSV(winners);
        //System.out.println(allTDFWinnersTeamsCSV);

        Map<String, Long> winsByNationalityCounting = winsByNationalityCounting(winners);
        //winsByNationalityCounting.entrySet().forEach(System.out::println);

    }

    //Queries definition
    static Set<String> winnerNameUnder3450Km(List<Winner> winners) {
        return winners.stream()
                .filter(winner -> winner.getLengthKm() < 3450)
                .map(Winner::getName)
                .collect(Collectors.toSet());
    }

    static List<String> winnersOfToursLessThan3500Km(List<Winner> winners) {
        return winners.stream()
                .filter(winner -> winner.getLengthKm() < 3500)
                .map(Winner::toString)
                .collect(Collectors.toList());
    }

    static List<String> winnersOfToursGreaterThan3500Km(List<Winner> winners) {
        return winners.stream()
                .filter(winner -> winner.getLengthKm() > 3500)
                .map(Winner::toString)
                .collect(Collectors.toList());
    }

    static List<Winner> winnersDetailOfToursGreaterThan3500km(List<Winner> winners) {
        return winners.stream()
                .filter(winner -> winner.getLengthKm() > 3500)
                .collect(Collectors.toList());
    }

    static List<Winner> winnerObjectsOfToursLessThan3500kmLimit2(List<Winner> winners) {
        return winners.stream()
                .filter(winner -> winner.getLengthKm() < 3500)
                .limit(2)
                .collect(Collectors.toList());
    }

    static List<String> distinctTDFWinners(List<Winner> winners) {
        return winners.stream()
                .map(Winner::getName)
                .distinct()
                .collect(Collectors.toList());
    }

    static long numberOfDistinctWinnersWithDuplicate(List<Winner> winners) {
        return winners.stream()
                .count();
    }

    static long numberOfDistinctWinners(List<Winner> winners) {
        return winners.stream()
                .map(Winner::getName)
                .distinct()
                .count();
    }

    static List<String> mapWinnerYearNamesToList(List<Winner> winners) {
        return winners.stream()
                .collect(Collectors.toMap(Winner::getYear, Winner::getName))
                .entrySet()
                .stream()
                .map(Map.Entry::toString)
                .collect(Collectors.toList());
    }

    static List<Integer> mapWinnerNameLengthToList(List<Winner> winners) {
        return winners.stream()
                .map(Winner::getName)
                .map(String::length)
                .collect(Collectors.toList());
    }

    static Optional<Winner> winner2012(List<Winner> winners) {
        return winners.stream()
                .filter(winner -> winner.getYear() == 2012)
                .findFirst();
    }

    static Optional<Integer> winnerYear2014(List<Winner> winners) {
        return winners.stream()
                .map(Winner::getYear)
                .filter(year -> year == 2014)
                .findFirst();
    }

    static int totalDistance(List<Winner> winners) {
        return winners.stream()
                .map(Winner::getLengthKm)
                .reduce(0, Integer::sum);
    }

    static Optional<Integer> shortestYear(List<Winner> winners) {
        return winners.stream()
                .reduce((w1,w2) -> w1.getLengthKm() < w2.getLengthKm() ? w1 : w2)
                .map(Winner::getYear);
    }

    static Optional<Integer> longestYear(List<Winner> winners) {
        return winners.stream()
                .reduce((w1,w2) -> w1.getLengthKm() > w2.getLengthKm() ? w1 : w2)
                .map(Winner::getYear);
    }

    static Optional<Winner> fastestWinner(List<Winner> winners) {
        return winners.stream()
                .reduce((w1,w2) -> w1.getWinningTime().compareTo(w2.getWinningTime()) < 0 ? w1 : w2);
    }

    static Optional<Double> fastestTDF(List<Winner> winners) {
        return winners.stream()
                .map(Winner::getWinningTime)
                .reduce((t1,t2) -> t1.compareTo(t2) < 0 ? t1 : t2)
                .map(duration -> (double) duration.getSeconds());

    }

    static Map<String, List<Winner>> namesVsWinner(List<Winner> winners) {
        return winners.stream()
                .collect(groupingBy(Winner::getName));
    }

    static String allTDFWinnersTeamsCSV(List<Winner> winners) {
        return winners.stream()
                .map(Winner::getTeam)
                .distinct()
                .reduce("", (t1,t2) -> t1 + "," + t2);
    }


    static Map<String, Long> winsByNationalityCounting(List<Winner> winners) {
        return winners.stream()
                .collect(groupingBy(Winner::getNationality))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (long) e.getValue().size()));
    }

}