package nl.jpoint.top2k.scorer;

import com.google.common.collect.Maps;
import nl.jpoint.top2k.domain.Track;

import java.util.Map;
import java.util.Set;

public class Scorer {

    private Track trackA;
    private Track trackB;
    private double kFactor;

    public Scorer(double kFactor, Track trackA, Track trackB) {
        this.kFactor = kFactor;
        this.trackA = trackA;
        this.trackB = trackB;
    }

    public void aWins() {
        win(trackA, trackB);
    }

    public void bWins() {
        win(trackB, trackA);
    }

    private void win(Track winningTrack, Track losingTrack) {
        double winningScore = winningTrack.getScore();
        double losingScore = losingTrack.getScore();

        EloRating rating = getEloRating(winningScore, losingScore);

        winningScore += kFactor * (1 - rating.getLeftScore());
        losingScore += kFactor * (0 - rating.getRightScore());

        winningTrack.setScore(winningScore);
        losingTrack.setScore(losingScore);
    }


    public void voteTie() {
        double winningScore = trackA.getScore();
        double losingScore = trackB.getScore();

        EloRating rating = getEloRating(winningScore, losingScore);

        winningScore += kFactor * (0.5d - rating.getLeftScore());
        losingScore += kFactor * (0.5d - rating.getRightScore());

        trackA.setScore(winningScore);
        trackB.setScore(losingScore);
    }

    private static class EloRating {

        private double leftScore;
        private double rightScore;

        private EloRating(double leftScore) {
            this.leftScore = leftScore;
            this.rightScore = 1d - leftScore;
        }

        public double getLeftScore() {
            return leftScore;
        }

        public double getRightScore() {
            return rightScore;
        }

        private static Map<Integer, EloRating> elos = init();

        private static Map<Integer, EloRating> init() {
            Map<Integer, EloRating> ratingMap = Maps.newLinkedHashMap();
            ratingMap.put(3, new EloRating(0.5));
            ratingMap.put(10, new EloRating(0.51));
            ratingMap.put(17, new EloRating(0.52));
            ratingMap.put(25, new EloRating(0.53));
            ratingMap.put(32, new EloRating(0.54));
            ratingMap.put(39, new EloRating(0.55));
            ratingMap.put(46, new EloRating(0.56));
            ratingMap.put(53, new EloRating(0.57));
            ratingMap.put(61, new EloRating(0.58));
            ratingMap.put(68, new EloRating(0.59));
            ratingMap.put(76, new EloRating(0.60));
            ratingMap.put(83, new EloRating(0.61));
            ratingMap.put(91, new EloRating(0.62));
            ratingMap.put(98, new EloRating(0.63));
            ratingMap.put(106, new EloRating(0.64));
            ratingMap.put(113, new EloRating(0.65));
            ratingMap.put(121, new EloRating(0.66));
            ratingMap.put(129, new EloRating(0.67));
            ratingMap.put(137, new EloRating(0.68));
            ratingMap.put(145, new EloRating(0.69));
            ratingMap.put(153, new EloRating(0.70));
            ratingMap.put(162, new EloRating(0.71));
            ratingMap.put(170, new EloRating(0.72));
            ratingMap.put(179, new EloRating(0.73));
            ratingMap.put(188, new EloRating(0.74));
            ratingMap.put(197, new EloRating(0.75));
            ratingMap.put(206, new EloRating(0.76));
            ratingMap.put(215, new EloRating(0.77));
            ratingMap.put(225, new EloRating(0.78));
            ratingMap.put(235, new EloRating(0.79));
            ratingMap.put(245, new EloRating(0.80));
            ratingMap.put(256, new EloRating(0.81));
            ratingMap.put(267, new EloRating(0.82));
            ratingMap.put(278, new EloRating(0.83));
            ratingMap.put(290, new EloRating(0.84));
            ratingMap.put(302, new EloRating(0.85));
            ratingMap.put(315, new EloRating(0.86));
            ratingMap.put(328, new EloRating(0.87));
            ratingMap.put(344, new EloRating(0.88));
            ratingMap.put(357, new EloRating(0.89));
            ratingMap.put(374, new EloRating(0.90));
            ratingMap.put(391, new EloRating(0.91));
            ratingMap.put(411, new EloRating(0.92));
            ratingMap.put(432, new EloRating(0.93));
            ratingMap.put(456, new EloRating(0.94));
            ratingMap.put(484, new EloRating(0.95));
            ratingMap.put(517, new EloRating(0.96));
            ratingMap.put(559, new EloRating(0.97));
            ratingMap.put(619, new EloRating(0.98));
            ratingMap.put(735, new EloRating(0.99));

            return ratingMap;
        }

        public static EloRating getRating(double number) {
            Set<Map.Entry<Integer, EloRating>> entries = elos.entrySet();

            for (Map.Entry<Integer, EloRating> it : entries) {
                if (number <= it.getKey()) {
                    return it.getValue();
                }
            }

            return new EloRating(1.0d);
        }
    }

    private EloRating getEloRating(double winningScore, double losingScore) {
        if (winningScore > losingScore) {
            return EloRating.getRating(winningScore - losingScore);
        } else {
            return EloRating.getRating(losingScore - winningScore);
        }
    }
}