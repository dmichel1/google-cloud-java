// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/cloud/iot/v1/resources.proto

package com.google.cloud.iot.v1;

public interface MqttConfigOrBuilder
    extends
    // @@protoc_insertion_point(interface_extends:google.cloud.iot.v1.MqttConfig)
    com.google.protobuf.MessageOrBuilder {

  /**
   *
   *
   * <pre>
   * If enabled, allows connections using the MQTT protocol. Otherwise, MQTT
   * connections to this registry will fail.
   * </pre>
   *
   * <code>.google.cloud.iot.v1.MqttState mqtt_enabled_state = 1;</code>
   */
  int getMqttEnabledStateValue();
  /**
   *
   *
   * <pre>
   * If enabled, allows connections using the MQTT protocol. Otherwise, MQTT
   * connections to this registry will fail.
   * </pre>
   *
   * <code>.google.cloud.iot.v1.MqttState mqtt_enabled_state = 1;</code>
   */
  com.google.cloud.iot.v1.MqttState getMqttEnabledState();
}
