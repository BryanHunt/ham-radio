package net.springfieldusa.ham.ui.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import net.springfieldusa.ham.radio.ProgrammedRadioChannel;
import net.springfieldusa.ham.radio.RadioRegistry;
import net.springfieldusa.ham.ui.widgets.RadioToneSelector;
import net.springfieldusa.ham.ui.widgets.RadioToneTypeSelector;

public class ChannelView
{
  private Composite parent;
  private Label channelNumber;
  private Text channelName;
  private Text receiveFrequency;
  private Text transmitFrequency;
  private RadioToneTypeSelector receiveToneType;
  private RadioToneTypeSelector transmitToneType;
  private RadioToneSelector receiveTone;
  private RadioToneSelector transmitTone;
  
  @PostConstruct
  public void createComposite(Composite parent, RadioRegistry radioRegistry)
  {
    this.parent = parent;
    parent.setLayout(new GridLayout(2, false));
    new Label(parent, SWT.NONE).setText("Channel:");
    channelNumber = new Label(parent, SWT.NONE);
    
    new Label(parent, SWT.NONE).setText("Name: ");
    channelName = new Text(parent, SWT.BORDER);
    channelName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

    new Label(parent, SWT.NONE).setText("Receive Freq:");
    receiveFrequency = new Text(parent, SWT.BORDER);
    receiveFrequency.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

    new Label(parent, SWT.NONE).setText("Transmit Freq:");
    transmitFrequency = new Text(parent, SWT.BORDER);
    transmitFrequency.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

    Group receiveToneGroup = new Group(parent, SWT.NONE);
    receiveToneGroup.setText("Receive Tone");
    receiveToneGroup.setLayout(new GridLayout(2, false));
    receiveToneGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 2, 1));
    
    new Label(receiveToneGroup, SWT.NONE).setText("Type:");
    receiveToneType = new RadioToneTypeSelector(receiveToneGroup, SWT.READ_ONLY);
    receiveToneType.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

    new Label(receiveToneGroup, SWT.NONE).setText("Tone:");
    receiveTone = new RadioToneSelector(receiveToneGroup, SWT.READ_ONLY, receiveToneType);
    receiveTone.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    
    Group transmitToneGroup = new Group(parent, SWT.NONE);
    transmitToneGroup.setText("Transmit Tone");
    transmitToneGroup.setLayout(new GridLayout(2, false));
    transmitToneGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 2, 1));
    
    new Label(transmitToneGroup, SWT.NONE).setText("Type:");
    transmitToneType = new RadioToneTypeSelector(transmitToneGroup, SWT.READ_ONLY);
    transmitToneType.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

    new Label(transmitToneGroup, SWT.NONE).setText("Tone:");
    transmitTone = new RadioToneSelector(transmitToneGroup, SWT.READ_ONLY, transmitToneType);
    transmitTone.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    
    new Label(parent, SWT.NONE).setText("Power:");
    
  }
  
  @Inject
  public void setChannel(@Named(IServiceConstants.ACTIVE_SELECTION) ProgrammedRadioChannel channel)
  {
    if(channel == null)
      return;
    
    channelNumber.setText(Integer.toString(channel.getChannelNumber()));
    channelName.setText(channel.getBaseChannel().getChannelName());
    receiveFrequency.setText(channel.getBaseChannel().getReceiveFrequency().getPreferredDisplay());
    transmitFrequency.setText(channel.getBaseChannel().getTransmitFrequency().getPreferredDisplay());
    
    int toneType = channel.getBaseChannel().getReceiveTone().getValue();
    
    if(toneType > 0 && toneType < 670)
    {
      receiveToneType.setSelection(new StructuredSelection("DTCS"));
      receiveTone.setSelection(new StructuredSelection(channel.getBaseChannel().getReceiveTone()));
    }
    else if(toneType > 104)
    {
      receiveToneType.setSelection(new StructuredSelection("CTCSS"));
      receiveTone.setSelection(new StructuredSelection(channel.getBaseChannel().getReceiveTone()));
    }
    else
      receiveToneType.setSelection(new StructuredSelection("None"));
      
    toneType = channel.getBaseChannel().getTransmitTone().getValue();
    
    if(toneType > 0 && toneType < 670)
    {
      transmitToneType.setSelection(new StructuredSelection("DTCS"));
      transmitTone.setSelection(new StructuredSelection(channel.getBaseChannel().getTransmitTone()));
    }
    else if(toneType > 104)
    {
      transmitToneType.setSelection(new StructuredSelection("CTCSS"));
      transmitTone.setSelection(new StructuredSelection(channel.getBaseChannel().getTransmitTone()));
    }
    else
      transmitToneType.setSelection(new StructuredSelection("None"));
      
    parent.layout();
  }
}