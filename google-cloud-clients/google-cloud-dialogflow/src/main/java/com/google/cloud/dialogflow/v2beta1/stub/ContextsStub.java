/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.cloud.dialogflow.v2beta1.stub;

import static com.google.cloud.dialogflow.v2beta1.ContextsClient.ListContextsPagedResponse;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.dialogflow.v2beta1.Context;
import com.google.cloud.dialogflow.v2beta1.CreateContextRequest;
import com.google.cloud.dialogflow.v2beta1.DeleteAllContextsRequest;
import com.google.cloud.dialogflow.v2beta1.DeleteContextRequest;
import com.google.cloud.dialogflow.v2beta1.GetContextRequest;
import com.google.cloud.dialogflow.v2beta1.ListContextsRequest;
import com.google.cloud.dialogflow.v2beta1.ListContextsResponse;
import com.google.cloud.dialogflow.v2beta1.UpdateContextRequest;
import com.google.protobuf.Empty;
import javax.annotation.Generated;

// AUTO-GENERATED DOCUMENTATION AND CLASS
/**
 * Base stub class for Dialogflow API.
 *
 * <p>This class is for advanced usage and reflects the underlying API directly.
 */
@Generated("by gapic-generator")
@BetaApi("A restructuring of stub classes is planned, so this may break in the future")
public abstract class ContextsStub implements BackgroundResource {

  public UnaryCallable<ListContextsRequest, ListContextsPagedResponse> listContextsPagedCallable() {
    throw new UnsupportedOperationException("Not implemented: listContextsPagedCallable()");
  }

  public UnaryCallable<ListContextsRequest, ListContextsResponse> listContextsCallable() {
    throw new UnsupportedOperationException("Not implemented: listContextsCallable()");
  }

  public UnaryCallable<GetContextRequest, Context> getContextCallable() {
    throw new UnsupportedOperationException("Not implemented: getContextCallable()");
  }

  public UnaryCallable<CreateContextRequest, Context> createContextCallable() {
    throw new UnsupportedOperationException("Not implemented: createContextCallable()");
  }

  public UnaryCallable<UpdateContextRequest, Context> updateContextCallable() {
    throw new UnsupportedOperationException("Not implemented: updateContextCallable()");
  }

  public UnaryCallable<DeleteContextRequest, Empty> deleteContextCallable() {
    throw new UnsupportedOperationException("Not implemented: deleteContextCallable()");
  }

  public UnaryCallable<DeleteAllContextsRequest, Empty> deleteAllContextsCallable() {
    throw new UnsupportedOperationException("Not implemented: deleteAllContextsCallable()");
  }

  @Override
  public abstract void close();
}
