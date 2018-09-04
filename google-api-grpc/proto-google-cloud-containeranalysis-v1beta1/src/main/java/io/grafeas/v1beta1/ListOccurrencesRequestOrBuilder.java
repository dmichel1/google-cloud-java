// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/devtools/containeranalysis/v1beta1/grafeas/grafeas.proto

package io.grafeas.v1beta1;

public interface ListOccurrencesRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:grafeas.v1beta1.ListOccurrencesRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * The name of the project to list occurrences for in the form of
   * `projects/[PROJECT_ID]`.
   * </pre>
   *
   * <code>string parent = 1;</code>
   */
  java.lang.String getParent();
  /**
   * <pre>
   * The name of the project to list occurrences for in the form of
   * `projects/[PROJECT_ID]`.
   * </pre>
   *
   * <code>string parent = 1;</code>
   */
  com.google.protobuf.ByteString
      getParentBytes();

  /**
   * <pre>
   * The filter expression.
   * </pre>
   *
   * <code>string filter = 2;</code>
   */
  java.lang.String getFilter();
  /**
   * <pre>
   * The filter expression.
   * </pre>
   *
   * <code>string filter = 2;</code>
   */
  com.google.protobuf.ByteString
      getFilterBytes();

  /**
   * <pre>
   * Number of occurrences to return in the list.
   * </pre>
   *
   * <code>int32 page_size = 3;</code>
   */
  int getPageSize();

  /**
   * <pre>
   * Token to provide to skip to a particular spot in the list.
   * </pre>
   *
   * <code>string page_token = 4;</code>
   */
  java.lang.String getPageToken();
  /**
   * <pre>
   * Token to provide to skip to a particular spot in the list.
   * </pre>
   *
   * <code>string page_token = 4;</code>
   */
  com.google.protobuf.ByteString
      getPageTokenBytes();
}