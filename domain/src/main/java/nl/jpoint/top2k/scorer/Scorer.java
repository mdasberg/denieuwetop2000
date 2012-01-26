package nl.jpoint.top2k.scorer;

import com.google.common.collect.Maps;
import nl.jpoint.top2k.domain.Track;

import java.util.Map;
import java.util.Set;

/**
 * User: ron
 * Date: 26-01-12
 * Time: 15:50
 */
public class Scorer {

    private Track trackA;
    private Track trackB;

    public Scorer(double v, Track trackA, Track trackB) {
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

        EloRating rating = EloRating.getRating(winningScore - losingScore);

        winningScore += 1 * (1 - rating.getLeftScore());
        losingScore += 1 * (0 - rating.getRightScore());

        winningTrack.setScore(winningScore);
        losingTrack.setScore(losingScore);
    }


    public void noVote() {
        double winningScore = trackA.getScore();
        double losingScore = trackB.getScore();

        EloRating rating = EloRating.getRating(winningScore - losingScore);

        winningScore += 1 * (0.5d - rating.getLeftScore());
        losingScore += 1 * (0.5d - rating.getRightScore());

        trackA.setScore(winningScore);
        trackB.setScore(losingScore);
    }

    private int getELO() {


        return 0;

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
            ratingMap.put(25, new EloRating(0.5));
            ratingMap.put(50, new EloRating(0.53));
            ratingMap.put(100, new EloRating(0.64));
            ratingMap.put(150, new EloRating(0.70));
            ratingMap.put(200, new EloRating(0.76));
            ratingMap.put(250, new EloRating(0.81));
            ratingMap.put(350, new EloRating(0.89));
            ratingMap.put(400, new EloRating(0.92));
            ratingMap.put(450, new EloRating(0.94));
            ratingMap.put(500, new EloRating(0.96));
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
}