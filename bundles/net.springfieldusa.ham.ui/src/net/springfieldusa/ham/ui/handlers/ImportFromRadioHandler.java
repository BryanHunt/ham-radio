package net.springfieldusa.ham.ui.handlers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;
import org.eclipselabs.emodeling.ResourceSetFactory;

import net.springfieldusa.ham.radio.Radio;
import net.springfieldusa.ham.radio.RadioRegistry;
import net.springfieldusa.ham.radio.RadioService;
import net.springfieldusa.ham.radio.TransferProgressMonitor;
import net.springfieldusa.ham.ui.dialogs.ImportFromRadioDialog;
import net.springfieldusa.ham.ui.parts.ChannelsView;
import net.springfieldusa.io.serial.SerialPortService;

public class ImportFromRadioHandler
{
  @Execute
  public void execute(Shell currentShell, UISynchronize sync, ResourceSetFactory resourceSetFactory, RadioRegistry radioRegistry, SerialPortService serialPortService, EPartService partService) throws IOException
  {
    MPart part = partService.findPart("net.springfieldusa.ham.ui.channels");
    part.setLabel("UV-5RE");
    ChannelsView view = (ChannelsView) part.getObject();

    ImportFromRadioDialog dialog = new ImportFromRadioDialog(currentShell, radioRegistry, serialPortService);
    
    if(dialog.open() == Dialog.CANCEL)
      return;
    
    IRunnableWithProgress op = new IRunnableWithProgress()
    {
      @Override
      public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
      {
        try
        {
          ResourceSet resourceSet = resourceSetFactory.createResourceSet();
          resourceSet.getLoadOptions().put(TransferProgressMonitor.OPTION_PROGRESS_MONITOR, new ProgressMonitor(monitor));
          resourceSet.getLoadOptions().put(RadioService.OPTION_RADIO_TYPE, dialog.getRadioType());
          Resource radioResource = resourceSet.getResource(dialog.getURI(), true);

          sync.asyncExec(new Runnable()
          {
            @Override
            public void run()
            {
              try
              {
                view.setRadio((Radio) radioResource.getContents().get(0));
              }
              catch (IOException e)
              {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
          });
        }
        catch (Exception e)
        {
          throw new InvocationTargetException(e);
        }
      }
    };

    try
    {
      new ProgressMonitorDialog(currentShell).run(true, true, op);
    }
    catch (InvocationTargetException | InterruptedException e)
    {
      StringBuilder message = new StringBuilder();
      message.append(e.getCause().getMessage().substring(e.getCause().getMessage().indexOf(":") + 2));
      message.append("\n\n");
      message.append("Check that the radio is powered on and the cable is plugged in.");

      MessageDialog.openError(currentShell, "Failed to import", message.toString());
    }
  }
}