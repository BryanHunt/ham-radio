package net.springfieldusa.ham.ui.parts;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapCellLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import net.springfieldusa.ham.radio.Radio;
import net.springfieldusa.ham.radio.RadioPackage;
import net.springfieldusa.ham.units.UnitsPackage;

public class ChannelsView
{

  private TableViewer tableViewer;

  @Inject
  private MDirtyable dirty;
  
  @Inject
  private ESelectionService selectionService;

  @PostConstruct
  public void createComposite(Composite parent)
  {
    parent.setLayout(new GridLayout(1, false));

    // txtInput = new Text(parent, SWT.BORDER);
    // txtInput.setMessage("Enter text to mark part as dirty");
    // txtInput.addModifyListener(new ModifyListener() {
    // @Override
    // public void modifyText(ModifyEvent e) {
    // dirty.setDirty(true);
    // }
    // });
    // txtInput.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    tableViewer = new TableViewer(parent, SWT.FULL_SELECTION);
    tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
    tableViewer.getTable().setHeaderVisible(true);

    tableViewer.addSelectionChangedListener(new ISelectionChangedListener()
    {
      @Override
      public void selectionChanged(SelectionChangedEvent event)
      {
        IStructuredSelection selection = tableViewer.getStructuredSelection();
        selectionService.setSelection(selection.getFirstElement());
      }
    });
    
    ObservableListContentProvider contentProvider = new ObservableListContentProvider();

    IObservableMap map = null;
    FeaturePath path = null;

    // Channel

    map = EMFProperties.value(RadioPackage.Literals.PROGRAMMED_RADIO_CHANNEL__CHANNEL_NUMBER).observeDetail(contentProvider.getKnownElements());

    TableViewerColumn columnViewer = new TableViewerColumn(tableViewer, SWT.NONE);
    columnViewer.getColumn().setText("Channel");
    columnViewer.getColumn().setWidth(60);
    columnViewer.setLabelProvider(new ObservableMapCellLabelProvider(map));

    // Name
    
    path = FeaturePath.fromList(RadioPackage.Literals.PROGRAMMED_RADIO_CHANNEL__CHANNEL_INFO, RadioPackage.Literals.RADIO_CHANNEL__CHANNEL_NAME);
    map = EMFProperties.value(path).observeDetail(contentProvider.getKnownElements());

    columnViewer = new TableViewerColumn(tableViewer, SWT.NONE);
    columnViewer.getColumn().setText("Name");
    columnViewer.getColumn().setWidth(100);
    columnViewer.setLabelProvider(new ObservableMapCellLabelProvider(map));
    
    // Receive Frequency

    path = FeaturePath.fromList(RadioPackage.Literals.PROGRAMMED_RADIO_CHANNEL__CHANNEL_INFO, RadioPackage.Literals.RADIO_CHANNEL__RECEIVE_FREQUENCY,
        UnitsPackage.Literals.UNIT_VALUE__PREFERRED_DISPLAY);
    map = EMFProperties.value(path).observeDetail(contentProvider.getKnownElements());

    columnViewer = new TableViewerColumn(tableViewer, SWT.NONE);
    columnViewer.getColumn().setText("Receive Freq");
    columnViewer.getColumn().setWidth(100);
    columnViewer.setLabelProvider(new ObservableMapCellLabelProvider(map));

    // Transmit Frequency

    path = FeaturePath.fromList(RadioPackage.Literals.PROGRAMMED_RADIO_CHANNEL__CHANNEL_INFO, RadioPackage.Literals.RADIO_CHANNEL__TRANSMIT_FREQUENCY,
        UnitsPackage.Literals.UNIT_VALUE__PREFERRED_DISPLAY);
    map = EMFProperties.value(path).observeDetail(contentProvider.getKnownElements());

    columnViewer = new TableViewerColumn(tableViewer, SWT.NONE);
    columnViewer.getColumn().setText("Transmit Freq");
    columnViewer.getColumn().setWidth(100);
    columnViewer.setLabelProvider(new ObservableMapCellLabelProvider(map));

    // Receive Tone

    path = FeaturePath.fromList(RadioPackage.Literals.PROGRAMMED_RADIO_CHANNEL__CHANNEL_INFO, RadioPackage.Literals.RADIO_CHANNEL__RECEIVE_TONE);
    map = EMFProperties.value(path).observeDetail(contentProvider.getKnownElements());

    columnViewer = new TableViewerColumn(tableViewer, SWT.NONE);
    columnViewer.getColumn().setText("RX Tone");
    columnViewer.getColumn().setWidth(75);
    columnViewer.setLabelProvider(new ObservableMapCellLabelProvider(map));

    // Transmit Tone

    path = FeaturePath.fromList(RadioPackage.Literals.PROGRAMMED_RADIO_CHANNEL__CHANNEL_INFO, RadioPackage.Literals.RADIO_CHANNEL__TRANSMIT_TONE);
    map = EMFProperties.value(path).observeDetail(contentProvider.getKnownElements());

    columnViewer = new TableViewerColumn(tableViewer, SWT.NONE);
    columnViewer.getColumn().setText("TX Tone");
    columnViewer.getColumn().setWidth(75);
    columnViewer.setLabelProvider(new ObservableMapCellLabelProvider(map));

    // Power

    path = FeaturePath.fromList(RadioPackage.Literals.PROGRAMMED_RADIO_CHANNEL__POWER, UnitsPackage.Literals.UNIT_VALUE__PREFERRED_DISPLAY);
    map = EMFProperties.value(path).observeDetail(contentProvider.getKnownElements());

    columnViewer = new TableViewerColumn(tableViewer, SWT.NONE);
    columnViewer.getColumn().setText("POWER");
    columnViewer.getColumn().setWidth(50);
    columnViewer.setLabelProvider(new ObservableMapCellLabelProvider(map));
//    columnViewer.setEditingSupport(new PowerEditingSupport(tableViewer));

    tableViewer.setContentProvider(contentProvider);
  }

  @Focus
  public void setFocus()
  {
    tableViewer.getTable().setFocus();
  }

  @Persist
  public void save()
  {
    dirty.setDirty(false);
  }

  public void setRadio(Radio radio) throws IOException
  {
    if (radio != null)
    {
      tableViewer.setInput(EMFProperties.list(RadioPackage.Literals.RADIO__CHANNELS).observe(radio));
    }
  }
}