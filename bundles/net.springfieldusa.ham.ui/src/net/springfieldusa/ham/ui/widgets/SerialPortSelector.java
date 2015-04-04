package net.springfieldusa.ham.ui.widgets;

import java.io.IOException;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;

import net.springfieldusa.io.serial.SerialPortService;

public class SerialPortSelector extends ComboViewer
{
  private SerialPortService serialPortService;
  
  public SerialPortSelector(Composite parent, int style, SerialPortService serialPortService)
  {
    super(parent, style);
    this.serialPortService = serialPortService;
    setContentProvider(new ArrayContentProvider());
  }

  public String getSelectedPort()
  {
    return (String) ((IStructuredSelection) getSelection()).getFirstElement();
  }
  
  @Override
  public void refresh()
  {
    try
    {
      setInput(serialPortService.getAvailablePorts());
      Object firstElement = getElementAt(0);
      
      if(firstElement != null)
        setSelection(new StructuredSelection(firstElement));
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
