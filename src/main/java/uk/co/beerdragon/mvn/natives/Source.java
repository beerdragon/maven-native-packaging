/*
 * Maven tools for native builds
 *
 * Copyright 2014 by Andrew Ian William Griffin <griffin@beerdragon.co.uk>.
 * Released under the GNU General Public License.
 */

package uk.co.beerdragon.mvn.natives;

import java.util.Objects;

/**
 * Description of a source of files to be packaged as a native build result.
 */
public class Source {

  /**
   * Path to the files to be packaged, for example <code>target/bin</code>.
   */
  private String _path;

  /**
   * Pattern selecting files to be packaged, for example <code>*.so</code>.
   */
  private String _pattern;

  /**
   * Returns the path where the files to be packaged can be found, for example
   * <code>target/bin</code>.
   * 
   * @return the path
   */
  public String getPath () {
    return _path;
  }

  /**
   * Sets the path where the files to be packaged can be found, for example <code>target/bin</code>.
   * 
   * @param path
   *          the path
   */
  public void setPath (final String path) {
    _path = path;
  }

  /**
   * Returns the pattern selecting files from the {@code path} folder, for example <code>*.so</code>
   * .
   * 
   * @return the pattern
   */
  public String getPattern () {
    return _pattern;
  }

  /**
   * Sets the pattern for selecting files from the {@code path} folder, for example
   * <code>*.so</code>.
   * 
   * @param pattern
   *          the pattern
   */
  public void setPattern (final String pattern) {
    _pattern = pattern;
  }

  /**
   * Implements the {@link #equals} test. The parameter is not {@code this} but is of the same type.
   * 
   * @param o
   *          the object to test, never {@code null}
   * @return {@code true} if the object is equal at this class level, {@code false} otherwise
   */
  /* package */boolean equalsImpl (final Object o) {
    final Source other = (Source)o;
    return Objects.equals (getPath (), other.getPath ())
        && Objects.equals (getPattern (), other.getPattern ());
  }

  /**
   * Applies the visitor to this instance.
   * 
   * @param visitor
   *          the visitor to apply, not {@code null}
   */
  /* package */void accept (final SourceVisitor visitor) {
    visitor.visitSource (this);
  }

  /**
   * Appends detail from this instance to the buffer.
   * 
   * @param sb
   *          the buffer to update
   */
  /* package */void toString (final StringBuilder sb) {
    if (getPath () != null) {
      sb.append (", path:").append (getPath ());
    }
    if (getPattern () != null) {
      sb.append (", pattern:").append (getPattern ());
    }
  }

  // Object

  @Override
  public int hashCode () {
    return Objects.hash (getClass (), getPath (), getPattern ());
  }

  @Override
  public final boolean equals (final Object o) {
    if (o == this) return true;
    if (o == null) return false;
    if (o.getClass () != getClass ()) return false;
    return equalsImpl (o);
  }

  // Object

  @Override
  public final String toString () {
    final StringBuilder sb = new StringBuilder ();
    sb.append (getClass ().getSimpleName ());
    toString (sb);
    return sb.toString ();
  }

}