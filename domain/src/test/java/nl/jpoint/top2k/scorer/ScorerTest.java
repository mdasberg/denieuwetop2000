package nl.jpoint.top2k.scorer;

import nl.jpoint.top2k.domain.Track;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: ron
 * Date: 26-01-12
 * Time: 15:46
 */
public class ScorerTest {

    @Test
    public void sameScoreWithInitialValue() throws Exception {
        Track trackA = new Track();
        trackA.setScore(1000);

        Track trackB = new Track();
        trackB.setScore(1000);

        Scorer scorer = new Scorer(1.00d, trackA, trackB);
        scorer.noVote();

        assertEquals(1000d, trackA.getScore(), 0.0d);
        assertEquals(1000d, trackB.getScore(), 0.0d);
    }

    @Test
    public void aWinsWithInitialValue() throws Exception {
        Track trackA = new Track();
        trackA.setScore(1000);

        Track trackB = new Track();
        trackB.setScore(1000);

        Scorer scorer = new Scorer(1.00d, trackA, trackB);
        scorer.aWins();

        assertEquals(1000 + (1 * 0.5), trackA.getScore(), 0.0d);
        assertEquals(1000 - (1 * 0.5), trackB.getScore(), 0.0d);
    }

    @Test
    public void bWinsWithInitialValue() throws Exception {
        Track trackA = new Track();
        trackA.setScore(1000);

        Track trackB = new Track();
        trackB.setScore(1000);

        Scorer scorer = new Scorer(1.00d, trackA, trackB);
        scorer.bWins();

        assertEquals(999.5, trackA.getScore(), 0.0d);
        assertEquals(1000.5, trackB.getScore(), 0.0d);
    }
}
