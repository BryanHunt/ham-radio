package net.springfieldusa.ham.ui.widgets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;

import net.springfieldusa.ham.radio.Tone;

public class RadioToneSelector extends ComboViewer
{
  private static Collection<Tone> cTones = new ArrayList<>();
  private static Collection<Tone> dTones = new ArrayList<>();
  
  static
  {
    for(int i = 1; i < 105; i++)
      dTones.add(Tone.values()[i]);

    for(int i = 105; i < Tone.values().length; i++)
      cTones.add(Tone.values()[i]);
  }
  
  public RadioToneSelector(Composite parent, int style, RadioToneTypeSelector radioToneTypeSelector)
  {
    super(parent, style);
    setContentProvider(new ArrayContentProvider());
    setLabelProvider(new LabelProvider()
    {
      @Override
      public String getText(Object element)
      {
        Tone tone = (Tone) element;
        return tone.getLiteral();
      }
    });

    radioToneTypeSelector.addSelectionChangedListener(new ISelectionChangedListener()
    {
      @Override
      public void selectionChanged(SelectionChangedEvent event)
      {
        setToneType((String) radioToneTypeSelector.getStructuredSelection().getFirstElement());
      }
    });
  }

  public String getSelectedToneType()
  {
    return (String) getStructuredSelection().getFirstElement();
  }  

  private void setToneType(String toneType)
  {
    switch(toneType)
    {
      case "None":
        setInput(Collections.emptyList());
        break;
      case "CTCSS":
        setInput(cTones);
        break;
      case "DTCS":
        setInput(dTones);
        break;
    }
    
    Object firstElement = getElementAt(0);
    
    if(firstElement != null)
      setSelection(new StructuredSelection(firstElement));
  }
}
