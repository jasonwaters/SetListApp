// Generated by Enunciate
package fm.setlist.api.model;

/**
 *  This class represents an artist. An artist is a musician or a group of
 *  musicians. Each artist has a definite <a
 *  href="http://wiki.musicbrainz.org/MBID">Musicbrainz Identifier</a> (MBID)
 *  with which the artist can be uniquely identified.
 */
@javax.xml.bind.annotation.XmlType (
  name = "artist",
  namespace = "",
  propOrder = {"url"}
)
@javax.xml.bind.annotation.XmlRootElement (
  name = "artist",
  namespace = ""
)
public class Artist implements java.io.Serializable {

  private java.lang.String _disambiguation;
  private java.lang.String _mbid;
  private java.lang.String _name;
  private java.lang.String _sortName;
  private java.lang.String _url;

  /**
   * disambiguation to distinguish between artists with same names
   */
  @javax.xml.bind.annotation.XmlAttribute (
    name = "disambiguation",
    namespace = "",
    required = false
  )
  public java.lang.String getDisambiguation() {
    return this._disambiguation;
  }

  /**
   * disambiguation to distinguish between artists with same names
   */
  public void setDisambiguation(java.lang.String _disambiguation) {
    this._disambiguation = _disambiguation;
  }

  /**
   * unique Musicbrainz Identifier (MBID), e.g.
   * <em>&quot;b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d&quot;</em>
   */
  @javax.xml.bind.annotation.XmlAttribute (
    name = "mbid",
    namespace = "",
    required = false
  )
  public java.lang.String getMbid() {
    return this._mbid;
  }

  /**
   * unique Musicbrainz Identifier (MBID), e.g.
   * <em>&quot;b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d&quot;</em>
   */
  public void setMbid(java.lang.String _mbid) {
    this._mbid = _mbid;
  }

  /**
   * the artist's name, e.g. <em>&quot;The Beatles&quot;</em>
   */
  @javax.xml.bind.annotation.XmlAttribute (
    name = "name",
    namespace = "",
    required = false
  )
  public java.lang.String getName() {
    return this._name;
  }

  /**
   * the artist's name, e.g. <em>&quot;The Beatles&quot;</em>
   */
  public void setName(java.lang.String _name) {
    this._name = _name;
  }

  /**
   * the artist's sort name, e.g. <em>&quot;Beatles, The&quot;</em> or
   * <em>&quot;Springsteen, Bruce&quot;</em>
   */
  @javax.xml.bind.annotation.XmlAttribute (
    name = "sortName",
    namespace = "",
    required = false
  )
  public java.lang.String getSortName() {
    return this._sortName;
  }

  /**
   * the artist's sort name, e.g. <em>&quot;Beatles, The&quot;</em> or
   * <em>&quot;Springsteen, Bruce&quot;</em>
   */
  public void setSortName(java.lang.String _sortName) {
    this._sortName = _sortName;
  }

  /**
   * the attribution url
   */
  @javax.xml.bind.annotation.XmlElement (
    name = "url",
    namespace = ""
  )
  public java.lang.String getUrl() {
    return this._url;
  }

  /**
   * the attribution url
   */
  public void setUrl(java.lang.String _url) {
    this._url = _url;
  }

}
