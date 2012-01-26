package nl.jpoint.top2k.scorer;

import nl.jpoint.top2k.domain.Track;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScorerTest {

    @Test
    public void sameScoreWithInitialValue() throws Exception {
        Track trackA = createTrack(1000);
        Track trackB = createTrack(1000);

        Scorer scorer = new Scorer(1.00d, trackA, trackB);
        scorer.voteTie();

        assertEquals(1000d, trackA.getScore(), 0.0d);
        assertEquals(1000d, trackB.getScore(), 0.0d);
    }

    @Test
    public void aWinsWithInitialValue() throws Exception {
        Track trackA = createTrack(1000);
        Track trackB = createTrack(1000);

        Scorer scorer = new Scorer(1.00d, trackA, trackB);
        scorer.aWins();

        assertEquals(1000.5, trackA.getScore(), 0.0d);
        assertEquals(999.5, trackB.getScore(), 0.0d);
    }

    @Test
    public void bWinsWithInitialValue() throws Exception {
        Track trackA = createTrack(1000);
        Track trackB = createTrack(1000);

        Scorer scorer = new Scorer(1.00d, trackA, trackB);
        scorer.bWins();

        assertEquals(999.5, trackA.getScore(), 0.0d);
        assertEquals(1000.5, trackB.getScore(), 0.0d);
    }

    @Test
    public void aWinsWith200Difference() throws Exception {
        Track trackA = createTrack(1100);
        Track trackB = createTrack(900);

        Scorer scorer = new Scorer(1.00d, trackA, trackB);
        scorer.aWins();

        assertEquals(1100 + (1 - 0.76), trackA.getScore(), 0.0d);
        assertEquals(900 + (0 - 0.24), trackB.getScore(), 0.0d);
    }

    @Test
    public void noOneWinsWith200Difference() throws Exception {
        Track trackA = createTrack(1100);
        Track trackB = createTrack(900);

        Scorer scorer = new Scorer(1.00d, trackA, trackB);
        scorer.voteTie();

        assertEquals(1100 + (0.5 - 0.76), trackA.getScore(), 0.0d);
        assertEquals(900 + (0.5 - 0.24), trackB.getScore(), 0.0d);
    }

    @Test
    public void tieWithNoDifferenceHalfKFactor() throws Exception {
        Track trackA = createTrack(1000);
        Track trackB = createTrack(1000);

        Scorer scorer = new Scorer(0.5d, trackA, trackB);
        scorer.voteTie();

        assertEquals(1000 + (0.5 * (0.5 - 0.5)), trackA.getScore(), 0.0d);
        assertEquals(1000 + (0.5 * (0.5 - 0.5)), trackB.getScore(), 0.0d);
    }
    @Test
    public void tieWith200DifferenceHalfKFactor() throws Exception {
        Track trackA = createTrack(900);
        Track trackB = createTrack(1100);

        Scorer scorer = new Scorer(0.5d, trackA, trackB);
        scorer.voteTie();

        assertEquals(900 + (0.5 * (0.5 - 0.76)), trackA.getScore(), 0.0d);
        assertEquals(1100 + (0.5 * (0.5 - 0.24)), trackB.getScore(), 0.0d);
    }

    private Track createTrack(int score) {
        Track track = new Track("track", null);
        track.setScore(score);
        return track;
    }
}