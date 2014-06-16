/*
 * Maven tools for native builds
 *
 * Copyright 2014 by Andrew Ian William Griffin <griffin@beerdragon.co.uk>.
 * Released under the GNU General Public License.
 */

package uk.co.beerdragon.mvn.natives;

import java.io.IOException;
import java.util.Objects;

import org.apache.maven.plugin.Mojo;

import uk.co.beerdragon.misc.IOCallback.IOExceptionHandler;

/**
 * Implementation of {@link IOExceptionHandler} which logs to the Mojo's injected log
 */
/* package */class MojoLoggingErrorCallback implements IOExceptionHandler {

  private final Mojo _owner;

  public MojoLoggingErrorCallback (final Mojo owner) {
    _owner = Objects.requireNonNull (owner);
  }

  // IOExceptionHandler

  @Override
  public void exception (final IOException e) {
    _owner.getLog ().error (e);
  }

}