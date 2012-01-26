package nl.jpoint.top2k.domain;

import java.util.UUID;

/**
 * A Release Group groups several different releases into a single logical entity. Every release belongs to one, and
 * only one release group, which has a type (single, album, compilation, etc.)
 *
 * Both release groups and releases are "albums" in a general sense, but with a slight difference: a release is
 * something you can buy as media, e.g. a CD, a vinyl record etc. on its own, while a release group embraces the concept
 * of an album -- it doesn't matter how many CDs or editions/versions it had. When an artist tells you "We've released
 * our new album", they're talking about a release group. When his publisher says "This new album gets released next
 * week in Japan and next month in Europe", they're talking about the different releases that belong in the release
 * group that the artist told you about.
 *
 * MusicBrainz automatically considers every release in the database to be part of a release group, even if this group
 * only contains the one release. As an editor you don't have to worry about creating release groups, you will only need
 * to merge existing ones.
 */
public class ReleaseGroup {
    
    private UUID mbId;
    private String title;
    private Artist artist;
    private ReleaseType type;

}
