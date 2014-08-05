package org.pentaho.di.monitor.step;

import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.monitor.IKettleMonitoringEvent;
import org.pentaho.di.trans.step.StepMetaDataCombi;

import java.io.Serializable;

public class StepEvent implements IKettleMonitoringEvent {

  private static final long serialVersionUID = 3171013674678295934L;

  private static final String ID = "1.1.1.1.1.1.1.1"; // TODO replace with an actual oid

  public static enum EventType {BEFORE_INIT, AFTER_INIT, BEFORE_START, FINIHED}

  private Serializable id = ID;
  private EventType eventType;
  private String name;
  private String xmlContent;
  private boolean clustered;
  private boolean distributed;
  private boolean doesErrorHandling;
  private boolean usesThreadPriorityManagement;

  public StepEvent( EventType eventType ) {
    this.eventType = eventType;
  }

  @Override
  public Serializable getId() {
    return null;
  }

  public void setId( Serializable id ) {
    this.id = id;
  }

  public EventType getEventType() {
    return eventType;
  }

  public void setEventType( EventType eventType ) {
    this.eventType = eventType;
  }

  public String getName() {
    return name;
  }

  public void setName( String name ) {
    this.name = name;
  }

  public String getXmlContent() {
    return xmlContent;
  }

  public void setXmlContent( String xmlContent ) {
    this.xmlContent = xmlContent;
  }

  public boolean isClustered() {
    return clustered;
  }

  public void setClustered( boolean clustered ) {
    this.clustered = clustered;
  }

  public boolean isDistributed() {
    return distributed;
  }

  public void setDistributed( boolean distributed ) {
    this.distributed = distributed;
  }

  public boolean isDoesErrorHandling() {
    return doesErrorHandling;
  }

  public void setDoesErrorHandling( boolean doesErrorHandling ) {
    this.doesErrorHandling = doesErrorHandling;
  }

  public boolean isUsesThreadPriorityManagement() {
    return usesThreadPriorityManagement;
  }

  public void setUsesThreadPriorityManagement( boolean usesThreadPriorityManagement ) {
    this.usesThreadPriorityManagement = usesThreadPriorityManagement;
  }

  public StepEvent build( StepMetaDataCombi combi ) throws KettleException {

    if ( combi == null ) {
      return this;
    }

    setName( combi.stepname );

    if ( combi.stepMeta != null ) {

      setClustered( combi.stepMeta.isClustered() );
      setDistributed( combi.stepMeta.isDistributes() );
      setDoesErrorHandling( combi.stepMeta.isDoingErrorHandling() );
      setXmlContent( combi.stepMeta.getXML() );
    }

    if ( combi.step != null ) {

      setUsesThreadPriorityManagement( combi.step.isUsingThreadPriorityManagment() );
    }

    return this;
  }

}