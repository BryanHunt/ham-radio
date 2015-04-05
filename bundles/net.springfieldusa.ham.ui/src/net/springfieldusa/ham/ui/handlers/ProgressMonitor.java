package net.springfieldusa.ham.ui.handlers;

import org.eclipse.core.runtime.IProgressMonitor;

import net.springfieldusa.ham.radio.TransferProgressMonitor;

public class ProgressMonitor implements TransferProgressMonitor
{
  private IProgressMonitor monitor;

  public ProgressMonitor(IProgressMonitor monitor)
  {
    this.monitor = monitor;
  }

  @Override
  public void beginTask(String name, int totalWork)
  {
    monitor.beginTask(name, totalWork);
  }

  @Override
  public void done()
  {
    monitor.done();
  }

  @Override
  public boolean isCanceled()
  {
    return monitor.isCanceled();
  }

  @Override
  public void setTaskName(String name)
  {
    monitor.setTaskName(name);
  }

  @Override
  public void subTask(String name)
  {
    monitor.subTask(name);
  }

  @Override
  public void worked(int work)
  {
    monitor.worked(1);
  }
}
