package net.springfieldusa.ham.ui.dialogs;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import net.springfieldusa.ham.radio.RadioModel;
import net.springfieldusa.ham.radio.RadioRegistry;
import net.springfieldusa.ham.radio.RadioType;
import net.springfieldusa.ham.ui.widgets.RadioManufacturSelector;
import net.springfieldusa.ham.ui.widgets.RadioModelSelector;
import net.springfieldusa.ham.ui.widgets.SerialPortSelector;
import net.springfieldusa.io.serial.SerialPortService;

public class ImportFromRadioDialog extends Dialog
{
  private RadioRegistry radioRegistry;
  private RadioType selectedType;
  private String selectedPort;
  private SerialPortService serialPortService;
  
  public ImportFromRadioDialog(Shell parentShell, RadioRegistry radioRegistry, SerialPortService serialPortService)
  {
    super(parentShell);
    this.radioRegistry = radioRegistry;
    this.serialPortService = serialPortService;
  }

  public URI getURI()
  {
    return URI.createURI(selectedType.getProgrammingScheme() + "://" + selectedPort);
  }
  
  @Override
  protected Control createDialogArea(Composite parent)
  {
    Composite container = (Composite) super.createDialogArea(parent);
    GridLayout layout = new GridLayout(2, false);
    container.setLayout(layout);
    
    Label label = new Label(container, SWT.NONE);
    label.setText("Manufacturer:");

    RadioManufacturSelector manufacturer = new RadioManufacturSelector(container, SWT.READ_ONLY, radioRegistry);
    manufacturer.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    
    label = new Label(container, SWT.NONE);
    label.setText("Model:");
    
    RadioModelSelector model = new RadioModelSelector(container, SWT.READ_ONLY, manufacturer);
    model.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    
    model.addSelectionChangedListener(new ISelectionChangedListener()
    {
      @Override
      public void selectionChanged(SelectionChangedEvent event)
      {
        RadioModel selectedModel = (RadioModel) ((IStructuredSelection) model.getSelection()).getFirstElement();
        selectedType = selectedModel.getType();
      }
    });

    label = new Label(container, SWT.NONE);
    label.setText("Port:");
    
    SerialPortSelector portSelector = new SerialPortSelector(container, SWT.READ_ONLY, serialPortService);
    portSelector.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

    portSelector.addSelectionChangedListener(new ISelectionChangedListener()
    {
      @Override
      public void selectionChanged(SelectionChangedEvent event)
      {
        selectedPort = (String) ((IStructuredSelection) portSelector.getSelection()).getFirstElement();
      }
    });
    
    manufacturer.refresh();
    portSelector.refresh();

    return container;
  }
}
