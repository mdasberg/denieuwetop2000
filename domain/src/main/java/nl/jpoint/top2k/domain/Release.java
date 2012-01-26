package nl.jpoint.top2k.domain;

/**
 * A MusicBrainz release represents the unique release (i.e. issuing) of a product on a specific date with specific
 * release information such as the country, label, barcode, packaging, etc. If you walk into a store and purchase an
 * album or single, they're each represented in MusicBrainz as one release.
 *
 * Each release belongs to a release group and contains at least one medium (commonly referred to as a disc when talking
 * about a CD release). Each medium has a tracklist.
 *
 * A medium is the actual physical medium the audio content is stored upon. This means that each CD in a multi-disc
 * release will be entered as separate mediums within the release, and that both sides of a vinyl record or cassette
 * will exist on one medium. Mediums have a format (e.g. CD, DVD, vinyl, cassette) and can optionally also have a title.
 *
 * Tracklists represent the set and ordering of tracks as listed on a liner, and the same tracklist can appear on more
 * than one release. For example, a boxset compilation that contains previously released CDs would share the same
 * tracklists as the separate releases.
 */
public class Release {
}
