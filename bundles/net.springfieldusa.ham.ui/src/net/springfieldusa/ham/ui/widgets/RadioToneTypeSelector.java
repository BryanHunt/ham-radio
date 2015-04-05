package net.springfieldusa.ham.ui.widgets;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.widgets.Composite;

public class RadioToneTypeSelector extends ComboViewer
{
  public RadioToneTypeSelector(Composite parent, int style)
  {
    super(parent, style);
    add("None");
    add("CTCSS");
    add("DTCS");
  }

  public String getSelectedToneType()
  {
    return (String) getStructuredSelection().getFirstElement();
  }
}
