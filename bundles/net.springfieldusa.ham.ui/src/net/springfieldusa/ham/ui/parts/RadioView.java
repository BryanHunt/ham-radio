package net.springfieldusa.ham.ui.parts;

import javax.annotation.PostConstruct;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import net.springfieldusa.ham.radio.RadioManufacturer;
import net.springfieldusa.ham.radio.RadioRegistry;

public class RadioView
{
  private ListViewer listViewer;

  @PostConstruct
  public void createComposite(Composite parent, RadioRegistry radioRegistry)
  {
    parent.setLayout(new GridLayout(1, false));
    listViewer = new ListViewer(parent, SWT.FULL_SELECTION);
    listViewer.getList().setLayoutData(new GridData(GridData.FILL_BOTH));
    listViewer.setContentProvider(new ArrayContentProvider());
    listViewer.setLabelProvider(new LabelProvider()
    {
      @Override
      public String getText(Object element)
      {
        RadioManufacturer manufacturer = (RadioManufacturer) element;
        return manufacturer.getName();
      }
    });
    listViewer.setInput(radioRegistry.getRegisteredRadios());
  }

}