package net.springfieldusa.ham.ui.dialogs;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import net.springfieldusa.ham.radio.RadioManufacturer;
import net.springfieldusa.ham.radio.RadioModel;
import net.springfieldusa.ham.radio.RadioType;

public class ImportFromRadioDialog extends Dialog
{
  private Collection<RadioManufacturer> radios;
  private RadioType selectedType;
  private String selectedPort;

  public ImportFromRadioDialog(Shell parentShell, Collection<RadioManufacturer> radios)
  {
    super(parentShell);
    this.radios = radios;
  }

  public URI getURI()
  {
    return URI.createURI(selectedType.getProgrammingScheme() + "://" + selectedPort);
  }
  
  @Override
  protected Control createDialogArea(Composite parent)
  {
    // TODO: Use data bindings
    
    Composite container = (Composite) super.createDialogArea(parent);
    GridLayout layout = new GridLayout(2, false);
    container.setLayout(layout);
    
    Label label = new Label(container, SWT.NONE);
    label.setText("Manufacturer:");

    ComboViewer manufacturer = new ComboViewer(container, SWT.READ_ONLY);
    manufacturer.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    manufacturer.setContentProvider(new ArrayContentProvider());
    manufacturer.setLabelProvider(new LabelProvider()
    {
      @Override
      public String getText(Object element)
      {
        RadioManufacturer manufacturer = (RadioManufacturer) element;
        return manufacturer.getName();
      }
    });
    
    label = new Label(container, SWT.NONE);
    label.setText("Model:");
    
    ComboViewer model = new ComboViewer(container, SWT.READ_ONLY);
    model.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    model.setContentProvider(new ArrayContentProvider());
    model.setLabelProvider(new LabelProvider()
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
        RadioManufacturer selectedManufacturer = (RadioManufacturer) ((IStructuredSelection)manufacturer.getSelection()).getFirstElement();
        model.setInput(selectedManufacturer.getModels());
        model.setSelection(new StructuredSelection(selectedManufacturer.getModels().get(0)));
      }
    });
    
    model.addSelectionChangedListener(new ISelectionChangedListener()
    {
      @Override
      public void selectionChanged(SelectionChangedEvent event)
      {
        RadioModel selectedModel = (RadioModel) ((IStructuredSelection) model.getSelection()).getFirstElement();
        selectedType = selectedModel.getType();
      }
    });
    
    manufacturer.setInput(radios);
    manufacturer.setSelection(new StructuredSelection(radios.iterator().next()));
    
    label = new Label(container, SWT.NONE);
    label.setText("Port:");
    
    String serialPort = "/dev/cu.usbserial";

    ComboViewer port = new ComboViewer(container, SWT.READ_ONLY);
    port.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    port.add(serialPort);
    
    port.addSelectionChangedListener(new ISelectionChangedListener()
    {
      @Override
      public void selectionChanged(SelectionChangedEvent event)
      {
        selectedPort = (String) ((IStructuredSelection) port.getSelection()).getFirstElement();
      }
    });
    
    port.setSelection(new StructuredSelection(serialPort));
    
    return container;
  }
}
