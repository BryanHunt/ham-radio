package net.springfieldusa.ham.ui.widgets;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;

import net.springfieldusa.ham.radio.RadioManufacturer;
import net.springfieldusa.ham.radio.RadioModel;

public class RadioModelSelector extends ComboViewer
{
  public RadioModelSelector(Composite parent, int style, RadioManufacturSelector manufacturer)
  {
    super(parent, style);
    setContentProvider(new ArrayContentProvider());
    setLabelProvider(new LabelProvider()
    {
      @Override
      public String getText(Object element)
      {
        RadioModel model = (RadioModel) element;
        return model.getName();
      }
    });

    manufacturer.addSelectionChangedListener(new ISelectionChangedListener()
    {
      @Override
      public void selectionChanged(SelectionChangedEvent event)
      {
        RadioManufacturer selectedManufacturer = (RadioManufacturer) manufacturer.getStructuredSelection().getFirstElement();
        setInput(selectedManufacturer.getModels());
        
        Object firstElement = getElementAt(0);
        
        if(firstElement != null)
          setSelection(new StructuredSelection(firstElement));
      }
    });
  }

  public String getSelectedPort()
  {
    return (String) getStructuredSelection().getFirstElement();
  }
}
