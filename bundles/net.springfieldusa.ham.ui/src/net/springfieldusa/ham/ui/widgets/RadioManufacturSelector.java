package net.springfieldusa.ham.ui.widgets;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;

import net.springfieldusa.ham.radio.RadioManufacturer;
import net.springfieldusa.ham.radio.RadioRegistry;

public class RadioManufacturSelector extends ComboViewer
{
  private RadioRegistry radioRegistry;
  
  public RadioManufacturSelector(Composite parent, int style, RadioRegistry radioRegistry)
  {
    super(parent, style);
    this.radioRegistry = radioRegistry;
    setContentProvider(new ArrayContentProvider());
    setLabelProvider(new LabelProvider()
    {
      @Override
      public String getText(Object element)
      {
        RadioManufacturer manufacturer = (RadioManufacturer) element;
        return manufacturer.getName();
      }
    });
  }

  public String getSelectedPort()
  {
    return (String) ((IStructuredSelection) getSelection()).getFirstElement();
  }
  
  @Override
  public void refresh()
  {
      setInput(radioRegistry.getRegisteredRadios());
      Object firstElement = getElementAt(0);
      
      if(firstElement != null)
        setSelection(new StructuredSelection(firstElement));
  }
}
