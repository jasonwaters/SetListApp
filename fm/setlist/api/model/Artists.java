// Generated by Enunciate
package fm.setlist.api.model;

/**
 *  A {@link Result} consisting of a list of artists.
 */
@javax.xml.bind.annotation.XmlType (
  name = "artists",
  namespace = ""
)
@javax.xml.bind.annotation.XmlRootElement (
  name = "artists",
  namespace = ""
)
public class Artists extends fm.setlist.api.model.Result<fm.setlist.api.model.Artist> {

  private java.util.ArrayList<fm.setlist.api.model.Artist> _list;

  /**
   * result list of artists
   */
  @javax.xml.bind.annotation.XmlElement (
    name = "artist",
    namespace = ""
  )
  public java.util.ArrayList<fm.setlist.api.model.Artist> getList() {
    return this._list;
  }

  /**
   * result list of artists
   */
  public void setList(java.util.ArrayList<fm.setlist.api.model.Artist> _list) {
    this._list = _list;
  }

}
