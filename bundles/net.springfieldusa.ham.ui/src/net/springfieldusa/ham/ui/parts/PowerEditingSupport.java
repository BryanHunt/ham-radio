package net.springfieldusa.ham.ui.parts;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

import net.springfieldusa.ham.radio.ProgrammedRadioChannel;

public class PowerEditingSupport extends EditingSupport
{
  private CellEditor editor;

  public PowerEditingSupport(TableViewer viewer)
  {
    super(viewer);
//    editor = new ComboBoxCellEditor((Composite) viewer.getTable(), new String[] { "5", "1" }, SWT.READ_ONLY);
    editor = new TextCellEditor(viewer.getTable());
  }

  @Override
  protected CellEditor getCellEditor(Object element)
  {
    return editor;
  }

  @Override
  protected boolean canEdit(Object element)
  {
    return true;
  }

  @Override
  protected Object getValue(Object element)
  {
    return ((ProgrammedRadioChannel) element).getPower().getValue();
  }

  @Override
  protected void setValue(Object element, Object value)
  {
    ((ProgrammedRadioChannel) element).getPower().setValue(Long.parseLong((String) value));
  }
}
