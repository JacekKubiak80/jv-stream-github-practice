package practice;

import java.util.function.Predicate;
import model.Candidate;

public class CandidateValidator implements Predicate<Candidate> {

    @Override
    public boolean test(Candidate candidate) {
        if (candidate.getAge() <= 35) {
            return false;
        }
        if (!candidate.isAllowedToVote()) {
            return false;
        }
        if (!"Ukrainian".equals(candidate.getNationality())) {
            return false;
        }

        String period = candidate.getPeriodsInUkr();
        if (!livedAtLeastYears(period, 10)) {
            return false;
        }
        return true;
    }

    private boolean livedAtLeastYears(String period, int minYears) {
        if (period == null || !period.contains("-")) {
            return false;
        }

        try {
            String[] years = period.split("-");
            int start = Integer.parseInt(years[0].trim());
            int end = Integer.parseInt(years[1].trim());
            return (end - start + 1) >= minYears;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
