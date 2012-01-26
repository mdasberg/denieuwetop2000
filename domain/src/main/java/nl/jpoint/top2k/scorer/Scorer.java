package nl.jpoint.top2k.scorer;

import nl.jpoint.top2k.domain.Track;

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

        winningScore += 1 * (1 - 0.5);
        losingScore += 1 * (0 - 0.5);

        winningTrack.setScore(winningScore);
        losingTrack.setScore(losingScore);
    }


    public void noVote() {


    }
}
