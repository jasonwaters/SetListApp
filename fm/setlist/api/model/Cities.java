// Generated by Enunciate
package fm.setlist.api.model;

/**
 *  A {@link Result} consisting of a list of cities.
 */
@javax.xml.bind.annotation.XmlType (
  name = "cities",
  namespace = ""
)
@javax.xml.bind.annotation.XmlRootElement (
  name = "cities",
  namespace = ""
)
public class Cities extends fm.setlist.api.model.Result<fm.setlist.api.model.City> {

  private java.util.ArrayList<fm.setlist.api.model.City> _list;

  /**
   * result list of cities
   */
  @javax.xml.bind.annotation.XmlElement (
    name = "cities",
    namespace = ""
  )
  public java.util.ArrayList<fm.setlist.api.model.City> getList() {
    return this._list;
  }

  /**
   * result list of cities
   */
  public void setList(java.util.ArrayList<fm.setlist.api.model.City> _list) {
    this._list = _list;
  }

}
