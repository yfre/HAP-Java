package io.github.hapjava.services.impl;

import io.github.hapjava.accessories.SecuritySystemAccessory;
import io.github.hapjava.accessories.optionalcharacteristic.AccessoryWithName;
import io.github.hapjava.accessories.optionalcharacteristic.AccessoryWithStatusFault;
import io.github.hapjava.characteristics.impl.common.NameCharacteristic;
import io.github.hapjava.characteristics.impl.common.StatusFaultCharacteristic;
import io.github.hapjava.characteristics.impl.common.StatusTamperedCharacteristic;
import io.github.hapjava.characteristics.impl.securitysystem.CurrentSecuritySystemStateCharacteristic;
import io.github.hapjava.characteristics.impl.securitysystem.SecuritySystemAlarmTypeCharacteristic;
import io.github.hapjava.characteristics.impl.securitysystem.TargetSecuritySystemStateCharacteristic;

/** This service describes a security system service. */
public class SecuritySystemService extends AbstractServiceImpl {

  public SecuritySystemService(
      CurrentSecuritySystemStateCharacteristic currentState,
      TargetSecuritySystemStateCharacteristic targetState) {
    super("0000007E-0000-1000-8000-0026BB765291");
    addCharacteristic(currentState);
    addCharacteristic(targetState);
  }

  public SecuritySystemService(SecuritySystemAccessory accessory) {
    this(
        new CurrentSecuritySystemStateCharacteristic(
            accessory::getCurrentSecuritySystemState,
            accessory::subscribeCurrentSecuritySystemState,
            accessory::unsubscribeCurrentSecuritySystemState),
        new TargetSecuritySystemStateCharacteristic(
            accessory::getTargetSecuritySystemState,
            accessory::setTargetSecuritySystemState,
            accessory::subscribeTargetSecuritySystemState,
            accessory::unsubscribeTargetSecuritySystemState));
    if (accessory instanceof AccessoryWithName) {
      addOptionalCharacteristic(new NameCharacteristic(((AccessoryWithName) accessory)::getName));
    }
    if (accessory instanceof AccessoryWithStatusFault) {
      addOptionalCharacteristic(
          new StatusFaultCharacteristic(
              ((AccessoryWithStatusFault) accessory)::getStatusFault,
              ((AccessoryWithStatusFault) accessory)::subscribeStatusFault,
              ((AccessoryWithStatusFault) accessory)::unsubscribeStatusFault));
    }
  }

  public void addOptionalCharacteristic(NameCharacteristic name) {
    addCharacteristic(name);
  }

  public void addOptionalCharacteristic(SecuritySystemAlarmTypeCharacteristic alermType) {
    addCharacteristic(alermType);
  }

  public void addOptionalCharacteristic(StatusFaultCharacteristic statusFault) {
    addCharacteristic(statusFault);
  }

  public void addOptionalCharacteristic(StatusTamperedCharacteristic statusTampered) {
    addCharacteristic(statusTampered);
  }
}