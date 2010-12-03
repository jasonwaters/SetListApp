// Generated by Enunciate
package fm.setlist.api.model;

/**
 *  This class represents a song that is part of a {@link Set}.
 *  
 */
@javax.xml.bind.annotation.XmlType (
  name = "song",
  namespace = ""
)
@javax.xml.bind.annotation.XmlRootElement (
  name = "song",
  namespace = ""
)
public class Song implements java.io.Serializable {

  private java.lang.String _name;
  private fm.setlist.api.model.Artist _with;
  private fm.setlist.api.model.Artist _cover;
  private java.lang.String _info;

  /**
   * The name of the song. E.g. <em>Yesterday</em> or
   * <em>&quot;Wish You Were Here&quot;</em>
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
   * The name of the song. E.g. <em>Yesterday</em> or
   * <em>&quot;Wish You Were Here&quot;</em>
   */
  public void setName(java.lang.String _name) {
    this._name = _name;
  }

  /**
   * A different Artist than the performing one that joined the stage
   * for this song.
   */
  @javax.xml.bind.annotation.XmlElement (
    name = "with",
    namespace = ""
  )
  public fm.setlist.api.model.Artist getWith() {
    return this._with;
  }

  /**
   * A different Artist than the performing one that joined the stage
   * for this song.
   */
  public void setWith(fm.setlist.api.model.Artist _with) {
    this._with = _with;
  }

  /**
   * The original Artist of this song, if different to the performing
   * artist.
   */
  @javax.xml.bind.annotation.XmlElement (
    name = "cover",
    namespace = ""
  )
  public fm.setlist.api.model.Artist getCover() {
    return this._cover;
  }

  /**
   * The original Artist of this song, if different to the performing
   * artist.
   */
  public void setCover(fm.setlist.api.model.Artist _cover) {
    this._cover = _cover;
  }

  /**
   * Special incidents or additional information about the way the song was
   * performed at this specific concert. See the <a
   * href="http://www.setlist.fm/guidelines">setlist.fm guidelines</a> for a
   * complete list of allowed content.
   */
  @javax.xml.bind.annotation.XmlElement (
    name = "info",
    namespace = ""
  )
  public java.lang.String getInfo() {
    return this._info;
  }

  /**
   * Special incidents or additional information about the way the song was
   * performed at this specific concert. See the <a
   * href="http://www.setlist.fm/guidelines">setlist.fm guidelines</a> for a
   * complete list of allowed content.
   */
  public void setInfo(java.lang.String _info) {
    this._info = _info;
  }

}