/*
 * Maven tools for native builds
 *
 * Copyright 2014 by Andrew Ian William Griffin <griffin@beerdragon.co.uk>.
 * Released under the GNU General Public License.
 */

package uk.co.beerdragon.misc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Wrapper for {@link Runtime#exec} to simplify code coverage reporting and avoid actually spawning
 * processes during tests.
 */
public class ProcessExecutor {

  private static class ExceptionFuture<T> implements Future<T> {

    private final Exception _exception;

    public ExceptionFuture (final Exception exception) {
      _exception = exception;
    }

    @Override
    public boolean cancel (final boolean mayInterruptIfRunning) {
      return false;
    }

    @Override
    public boolean isCancelled () {
      return false;
    }

    @Override
    public boolean isDone () {
      return true;
    }

    @Override
    public T get () throws InterruptedException, ExecutionException {
      throw new ExecutionException (_exception);
    }

    @Override
    public T get (final long timeout, final TimeUnit unit) throws InterruptedException,
        ExecutionException, TimeoutException {
      throw new ExecutionException (_exception);
    }
  }

  /**
   * Calls {@link Runtime#exec(String)} and returns a {@link Future} that can be used to wait for
   * the process to complete.
   * 
   * @param command
   *          the command to execute, not {@code null}
   * @return a future that can be used to wait for process completion, or receive any exceptions
   */
  public Future<Integer> exec (final String command) {
    try {
      final Process process = Runtime.getRuntime ().exec (command);
      return new FutureTask<Integer> (new Callable<Integer> () {

        @Override
        public Integer call () throws Exception {
          return process.waitFor ();
        }

      });
    } catch (final Exception e) {
      return new ExceptionFuture<Integer> (e);
    }
  }

}